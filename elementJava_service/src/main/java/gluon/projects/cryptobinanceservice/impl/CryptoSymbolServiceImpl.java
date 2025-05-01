package gluon.projects.cryptobinanceservice.impl;

import gluon.projects.Utils;
import gluon.projects.cryptobinanceservice.CryptoSymbolService;
import gluon.projects.exceptions.ElementProjectException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CryptoSymbolServiceImpl implements CryptoSymbolService {

    private static Logger logger = LoggerFactory.getLogger(CryptoSymbolServiceImpl.class);

    private final String apiBinanceUrl;

    private final String listSymbolFile;

    public CryptoSymbolServiceImpl() {
        Properties properties = Utils.getPropertiesByFileName("application.properties");
        this.apiBinanceUrl = properties.getProperty("apibinanceurl");
        this.listSymbolFile = properties.getProperty("listsymbolfile");
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

    @Override
    public List<String> getNewListSymbol() {
        this.createFileContainListSymbol();
        List<String> symbolList = new ArrayList<>();
        String interval = "1M";

        int yearLimit = 2;
        int numberOfMonth = yearLimit * 12;
        float priceThreshold = 0.05f;

        long endTime = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(endTime));
        calendar.add(Calendar.YEAR, -yearLimit);
        long startTime = calendar.getTimeInMillis();

        String url;
        String symbolHistorique;
        JSONArray symbolHistoriqueArray;
        for(String symbol: this.getBinanceApiSymbolList()) {
            url = String.format("%s/klines?symbol=%s&interval=%s&startTime=%d&endTime=%d",
                    this.apiBinanceUrl, symbol, interval, startTime, endTime);
            symbolHistorique = Utils.sendRequestWithCompleteUrl(url);
            symbolHistoriqueArray = new JSONArray(symbolHistorique);

            JSONArray dataElement;
            float allowedPrice;

            if(symbolHistoriqueArray.length() == numberOfMonth) {

                dataElement = (JSONArray) symbolHistoriqueArray.get(symbolHistoriqueArray.length()-1);
                allowedPrice = Float.parseFloat((String) dataElement.get(4));

                if(allowedPrice > priceThreshold) {
                    symbolList.add(symbol);
                    logger.info("{} -- {}", symbol, allowedPrice);

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

            }
        }

        return symbolList;
    }

    @Override
    public List<String> getExistListSymbol() {
        return List.of();
    }

    private List<String> getBinanceApiSymbolList() {
        List<String> symbolList = new ArrayList<>();
        JSONObject symbolInfo;
        String symbol;
        boolean isMarginTradingAllowed = false;
        List<String> symbolExclus = this.excludedSymbol();
        String urlExchangeInfo = String.format("%s%s", this.apiBinanceUrl,"/exchangeInfo");

        String exchangeInformations = Utils.sendRequestWithCompleteUrl(urlExchangeInfo);
        JSONObject jsonObject = new JSONObject(exchangeInformations);
        JSONArray symbols = (JSONArray) jsonObject.get("symbols");

        for(int i = 0; i < symbols.length(); i++) {
            symbolInfo = new JSONObject(symbols.get(i).toString());
            symbol = (String) symbolInfo.get("symbol");
            isMarginTradingAllowed = (boolean) symbolInfo.get("isMarginTradingAllowed");
            if(
                    !symbolExclus.contains(symbol)
                            && isMarginTradingAllowed
                            && filterStringSymbol(symbol)
            ) {
                symbolList.add(symbol);
            }
        }

        return symbolList;
    }

    private boolean filterStringSymbol(String symbol) {
        boolean allow = false;
        if(
                symbol.endsWith("USDT")
                        && !symbol.startsWith("USDT")
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
        //symbolExclus.add("BTCUSDT");
        //symbolExclus.add("ETHUSDT");
        //symbolExclus.add("BNBUSDT");
        symbolExclus.add("USDCUSDT");
        //symbolExclus.add("SOLUSDT");
        symbolExclus.add("TUSDUSDT");
        symbolExclus.add("FDUSDUSDT");
        //symbolExclus.add("XRPUSDT");
        //symbolExclus.add("RUNEUSDT");
        //symbolExclus.add("DOGEUSDT");
        return symbolExclus;
    }
}
