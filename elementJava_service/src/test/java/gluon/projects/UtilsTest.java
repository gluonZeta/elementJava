package gluon.projects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Properties;
class UtilsTest {

    @Test
    void getPropertiesByFileName() {
        Properties properties = Utils.getPropertiesByFileName("application.properties");
        Assertions.assertNotNull(properties);
    }
}