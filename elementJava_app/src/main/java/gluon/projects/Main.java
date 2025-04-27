package gluon.projects;

import gluon.projects.cryptobinanceservice.CryptoSymbolService;
import gluon.projects.cryptobinanceservice.impl.CryptoSymbolServiceImpl;

public class Main {

    public static void main(String[] args) {

        CryptoSymbolService cryptoSymbolService = new CryptoSymbolServiceImpl();
        cryptoSymbolService.getNewListSymbol();

    }

}
