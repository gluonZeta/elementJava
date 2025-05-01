package gluon.projects;

import gluon.projects.cryptobinanceservice.BinanceWebsocketService;
import gluon.projects.cryptobinanceservice.CryptoSymbolService;
import gluon.projects.cryptobinanceservice.impl.BinanceWebsocketServiceImpl;
import gluon.projects.cryptobinanceservice.impl.CryptoSymbolServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        cryptoAnalysis();
    }

    public static void cryptoAnalysis() {
        CryptoSymbolService cryptoSymbolService = new CryptoSymbolServiceImpl();
        List<String> symbols = cryptoSymbolService.getExistListSymbol();
        List<BinanceWebsocketService> binanceWebsocketServices = new ArrayList<>();
        for(String symbol: symbols) {
            binanceWebsocketServices.add(new BinanceWebsocketServiceImpl(symbol));
            binanceWebsocketServices.get(binanceWebsocketServices.size()-1).launchExchangeData();
        }
    }

}
