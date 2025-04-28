package gluon.projects;

import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UtilsTest {

    @Test
    void sendRequestWithCompleteUrl() {
        Properties properties = Utils.getPropertiesByFileName("application.properties");
        StringBuilder url = new StringBuilder(properties.getProperty("apibinanceurl"));
        url.append("/ping");
        String response = Utils.sendRequestWithCompleteUrl(url.toString());
        assertEquals("{}", response);
    }

    @Test
    void getPropertiesByFileName() {
        Properties properties = Utils.getPropertiesByFileName("application.properties");
        assertNotNull(properties);
        assertEquals("/home/andraina/Documents/java_projects/LIST_SYMBOL.txt", properties.getProperty("listsymbolfile"));
    }

}