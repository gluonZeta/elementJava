package gluon.projects.cryptobinanceservice.impl;

import gluon.projects.Utils;
import gluon.projects.cryptobinanceservice.CryptoSymbolService;
import gluon.projects.exceptions.ElementProjectException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CryptoSymbolServiceImpl implements CryptoSymbolService {

    private static final Logger logger = LoggerFactory.getLogger(CryptoSymbolServiceImpl.class);

    private final String apiBinanceUrl;

    private final String listSymbolFile;

    public CryptoSymbolServiceImpl() {
        Properties properties = Utils.getPropertiesByFileName("application.properties");
        this.apiBinanceUrl = properties.getProperty("apibinanceurl");
        this.listSymbolFile = properties.getProperty("listsymbolfile");
    }

    private String urlCompletion(String symbol,int yearLimit) {
        String interval = "1M";
        long endTime = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(endTime));
        calendar.add(Calendar.YEAR, -yearLimit);
        long startTime = calendar.getTimeInMillis();

        return String.format("/klines?symbol=%s&interval=%s&startTime=%d&endTime=%d",
                symbol, interval, startTime, endTime);
    }

    private void writeSymbolInFile(String symbol) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(this.listSymbolFile, true);
        } catch (IOException e) {
            throw new ElementProjectException(e);
        }
        BufferedWriter buffer = new BufferedWriter(writer);
        try {
            buffer.write(symbol);
            buffer.newLine();
            buffer.close();
        } catch (IOException e) {
            throw new ElementProjectException(e);
        }
    }

    @Override
    public List<String> getNewListSymbol() {
        this.createFileContainListSymbol();

        List<String> symbolList = new ArrayList<>();
        float priceThreshold = 0.05f;
        int yearLimit = 1;
        int numberOfMonth = yearLimit * 12;

        String urlHistoricalData;
        String symbolHistoricalData;
        JSONArray symbolHistoricalDataArray;
        JSONArray historicalDataElement;
        float closePrice;

        for(String symbol: this.getBinanceApiSymbolList()) {
            urlHistoricalData = String.format("%s%s",
                    this.apiBinanceUrl,
                    this.urlCompletion(symbol, yearLimit)
            );
            symbolHistoricalData = Utils.sendRequestWithCompleteUrl(urlHistoricalData);
            symbolHistoricalDataArray = new JSONArray(symbolHistoricalData);

            if(symbolHistoricalDataArray.length() >= (numberOfMonth-1)) {
                historicalDataElement = (JSONArray) symbolHistoricalDataArray.get(symbolHistoricalDataArray.length()-1);
                closePrice = Float.parseFloat((String) historicalDataElement.get(4));

                if(closePrice > priceThreshold) {
                    symbolList.add(symbol);
                    this.writeSymbolInFile(symbol);
                }
            }
        }
        return symbolList;
    }

    @Override
    public List<String> getExistListSymbol() {
        List<String> symbolList = new ArrayList<>();
        String symbol;
        try(BufferedReader br = new BufferedReader(new FileReader(this.listSymbolFile))) {
            while ((symbol = br.readLine()) != null) {
                symbolList.add(symbol);
            }
        } catch (IOException e) {
            throw new ElementProjectException(e);
        }
        return symbolList;
    }

    private List<String> getBinanceApiSymbolList() {
        List<String> symbolList = new ArrayList<>();
        JSONObject symbolInfo;
        String symbol;
        boolean isMarginTradingAllowed = false;
        List<String> symbolExcludes = this.excludedSymbol();
        String urlExchangeInfo = String.format("%s%s", this.apiBinanceUrl,"/exchangeInfo");

        String exchangeInformationResponse = Utils.sendRequestWithCompleteUrl(urlExchangeInfo);
        JSONObject jsonObject = new JSONObject(exchangeInformationResponse);
        JSONArray symbols = (JSONArray) jsonObject.get("symbols");

        for(int i = 0; i < symbols.length(); i++) {
            symbolInfo = new JSONObject(symbols.get(i).toString());
            symbol = (String) symbolInfo.get("symbol");
            isMarginTradingAllowed = (boolean) symbolInfo.get("isMarginTradingAllowed");
            if(!symbolExcludes.contains(symbol)
                    && isMarginTradingAllowed
                    && filterStringSymbol(symbol)) {
                symbolList.add(symbol);
            }
        }

        return symbolList;
    }

    private boolean filterStringSymbol(String symbol) {
        boolean allow = false;
        if(symbol.endsWith("USDC")
                && !symbol.startsWith("USDC")
                && !symbol.contains("DOWN")
                && !symbol.contains("BULL")
                && !symbol.contains("BEAR")
                && symbol.length() >= 6
        ) {
            allow = true;
        }
        return allow;
    }

    private List<String> excludedSymbol() {
        List<String> symbolExclus = new ArrayList<>();
        symbolExclus.add("TUSDUSDC");
        symbolExclus.add("FDUSDUSDC");
        return symbolExclus;
    }

    private void createFileContainListSymbol() {
        File file = new File(this.listSymbolFile);
        if (file.exists()) {
            this.cleanUp(file.getPath());
        }

        try {
            if (file.createNewFile()) {
                logger.info("Fichier vide créé : {}", listSymbolFile);
            } else {
                logger.info("Le fichier n'a pas pu être créé.");
            }
        } catch (IOException e) {
            throw new ElementProjectException(e);
        }
    }

    public void cleanUp(String filePath) {
        try {
            Files.delete(Path.of(filePath));
        } catch (IOException e) {
            throw new ElementProjectException(e);
        }
    }
}
