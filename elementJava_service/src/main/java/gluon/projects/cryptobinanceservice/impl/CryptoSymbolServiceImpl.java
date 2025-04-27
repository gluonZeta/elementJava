package gluon.projects.cryptobinanceservice.impl;

import gluon.projects.cryptobinanceservice.CryptoSymbolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CryptoSymbolServiceImpl implements CryptoSymbolService {

    private static Logger logger = LoggerFactory.getLogger(CryptoSymbolServiceImpl.class);

    @Override
    public List<String> getNewListSymbol() {
        logger.trace("Ceci est un log TRACE");
        logger.debug("Ceci est un log DEBUG");
        logger.info("Ceci est un log INFO");
        logger.warn("Ceci est un log WARN");
        logger.error("Ceci est un log ERROR");
        return List.of();
    }

    @Override
    public List<String> getExistListSymbol() {
        return List.of();
    }
}
