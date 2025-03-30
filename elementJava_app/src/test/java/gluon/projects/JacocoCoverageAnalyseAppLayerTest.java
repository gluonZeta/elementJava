package gluon.projects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class JacocoCoverageAnalyseAppLayerTest {

    private JacocoCoverageAnalyseAppLayer jacocoCoverageAnalyseAppLayer;

    @BeforeEach
    void setUp() {
        jacocoCoverageAnalyseAppLayer = new JacocoCoverageAnalyseAppLayer();
    }

    @Test
    void soustractionX() {
        assertEquals(5, this.jacocoCoverageAnalyseAppLayer.soustractionX(8,3));
    }

}