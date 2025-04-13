package gluon.projects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.trace("Ceci est un log TRACE");
        logger.debug("Ceci est un log DEBUG");
        logger.info("Ceci est un log INFO");
        logger.warn("Ceci est un log WARN");
        logger.error("Ceci est un log ERROR");
        logger.fatal("Ceci est un log FATAL");

        JacocoCoverageAnalyseServiceLayer jacocoCoverageAnalyseServiceLayer = new JacocoCoverageAnalyseServiceLayer();
        logger.info("Résultat = " + jacocoCoverageAnalyseServiceLayer.soustraction(67,34));

    }

}
