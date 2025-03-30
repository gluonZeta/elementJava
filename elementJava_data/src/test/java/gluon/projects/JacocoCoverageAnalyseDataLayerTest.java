package gluon.projects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class JacocoCoverageAnalyseDataLayerTest {

    private JacocoCoverageAnalyseDataLayer jacocoCoverageAnalyseDataLayer;

    @BeforeEach
    void setUp() {
        this.jacocoCoverageAnalyseDataLayer = new JacocoCoverageAnalyseDataLayer();
    }

    @Test
    void additionZ() {
        assertEquals(3.5F, this.jacocoCoverageAnalyseDataLayer.additionZ(2.1F, 1.4F));
    }
}