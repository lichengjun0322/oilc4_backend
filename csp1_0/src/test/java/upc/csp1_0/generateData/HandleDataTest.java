package upc.csp1_0.generateData;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HandleDataTest {

    @Test
    public void handler() throws IOException {
        HandleData handleData = new HandleData();
        handleData.handler();
    }
}