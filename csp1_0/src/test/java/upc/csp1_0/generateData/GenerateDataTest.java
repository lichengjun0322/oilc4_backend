package upc.csp1_0.generateData;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class GenerateDataTest {

    @Test
    public void generateLog() throws IOException {
        for(int i = 0;i < 200;i++) {
            GenerateData generateData = new GenerateData();
            generateData.generateLog();
        }
    }
}