package gluon.projects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CoverageMainTest {

    @Test
    void multiplicationInt() {
        CoverageMain coverageMain = new CoverageMain();
        assertEquals(12, coverageMain.multiplicationInt(4,3));
    }
}