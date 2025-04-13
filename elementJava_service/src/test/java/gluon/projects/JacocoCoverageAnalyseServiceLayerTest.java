package gluon.projects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class JacocoCoverageAnalyseServiceLayerTest {

    private JacocoCoverageAnalyseServiceLayer jacocoCoverageAnalyseServiceLayer;

    @BeforeEach
    void setUp() {
        this.jacocoCoverageAnalyseServiceLayer = new JacocoCoverageAnalyseServiceLayer();
    }

    @Test
    void soustraction() {
        assertEquals(4.3F, this.jacocoCoverageAnalyseServiceLayer.soustraction(5.4F,1.1F));
    }

}