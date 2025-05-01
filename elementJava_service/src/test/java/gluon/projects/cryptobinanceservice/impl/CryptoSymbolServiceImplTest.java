package gluon.projects.cryptobinanceservice.impl;

import gluon.projects.cryptobinanceservice.CryptoSymbolService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

class CryptoSymbolServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(CryptoSymbolServiceImplTest.class);

    CryptoSymbolService cryptoSymbolService;

    @BeforeEach
    void setUp() {
        this.cryptoSymbolService = new CryptoSymbolServiceImpl();
    }

    @Test
    void getNewListSymbol() {
        //List<String> symbols = this.cryptoSymbolService.getNewListSymbol();
        logger.trace("Ceci est un log TRACE");
        logger.debug("Ceci est un log DEBUG");
        logger.info("Ceci est un log INFO");
        logger.warn("Ceci est un log WARN");
        logger.error("Ceci est un log ERROR");

        /*
        symbols.stream().forEach(System.out::println);
        Assertions.assertNotNull(symbols);
        Assertions.assertTrue(symbols.size() > 100);

         */
    }

    @Test
    void getExistListSymbol() {
        Assertions.assertNotNull(this.cryptoSymbolService.getExistListSymbol());
    }
}