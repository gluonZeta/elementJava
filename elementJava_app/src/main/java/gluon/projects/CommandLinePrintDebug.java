package gluon.projects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLinePrintDebug implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(CommandLinePrintDebug.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("Command line runner PRINTTTT #####################");
    }
}
