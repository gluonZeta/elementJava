package gluon.projects;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static Properties getPropertiesByFileName(String fileName) {
        Properties properties = new Properties();
        try(InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new ElementProjectException(e);
        }
        return properties;
    }

}
