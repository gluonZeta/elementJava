package gluon.projects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleHelloService implements HelloService{

    Logger logger = LoggerFactory.getLogger(ConsoleHelloService.class);

    private final String prefix;

    private final String suffix;

    public ConsoleHelloService(String prefix, String suffix) {
        this.prefix = (prefix != null ? prefix : "Hello initial value => ");
        this.suffix = (suffix != null ? suffix : "!");
    }

    public ConsoleHelloService() {
        this(null, null);
    }

    @Override
    public void sayHello(String content) {
        String msg = String.format("%s%s%s", this.prefix, content, this.suffix);
        logger.info(msg);
    }
}
