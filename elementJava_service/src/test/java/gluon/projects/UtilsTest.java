package gluon.projects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
class UtilsTest {

    @Test
    void getPropertiesByFileName() {
        Properties properties = Utils.getPropertiesByFileName("application.properties");
        Assertions.assertNotNull(properties);
    }
}