package gluon.projects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.trace("Ceci est un log TRACE");
        logger.debug("Ceci est un log DEBUG");
        logger.info("Ceci est un log INFO");
        logger.warn("Ceci est un log WARN");
        logger.error("Ceci est un log ERROR");
    }

}
