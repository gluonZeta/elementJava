package gluon.projects;

import gluon.projects.cryptobinanceservice.CryptoSymbolService;
import gluon.projects.cryptobinanceservice.impl.CryptoSymbolServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        CryptoSymbolService cryptoSymbolService = new CryptoSymbolServiceImpl();
        List<String> symbols = cryptoSymbolService.getExistListSymbol();
        symbols.forEach((String symbol) -> logger.info("----- {}", symbol));
    }

}
