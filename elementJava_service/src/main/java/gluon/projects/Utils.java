package gluon.projects;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    public static Properties getPropertiesByFileName(String propertiesFileName) {
        Properties properties = new Properties();
        try(InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream(propertiesFileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return properties;
    }

}
