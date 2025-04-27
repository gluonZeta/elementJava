package gluon.projects.cryptobinanceservice.impl;

import gluon.projects.cryptobinanceservice.CryptoSymbolService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CryptoSymbolServiceImplTest {

    CryptoSymbolService cryptoSymbolService;

    @BeforeEach
    void setUp() {
        this.cryptoSymbolService = new CryptoSymbolServiceImpl();
    }

    @Test
    void getNewListSymbol() {
        Assertions.assertNotNull(this.cryptoSymbolService.getNewListSymbol());
    }

    @Test
    void getExistListSymbol() {
        Assertions.assertNotNull(this.cryptoSymbolService.getExistListSymbol());
    }
}