package gluon.projects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JacocoCoverageAnalyseServiceLayer {

    private Logger logger = LogManager.getLogger(JacocoCoverageAnalyseServiceLayer.class);

    public float soustraction(float a, float b) {
        logger.info("Calcul soustraction SERVICE LAYER");
        return a-b;
    }

    public float multiplication(float a, float b) {
        return a*b;
    }

}
