package upc.csp1_0.serviceImpl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
class IsLegalTest {
    @Autowired
    IsLegal isLegal;
    @Test
    void isLegalFlow() throws IOException {
        boolean flag = isLegal.isLegalFlow("135" ,"flow:135" ,"openflow:3" ,"3"
                ,"200" ,"10.0.0.2/32" ,"35" ,"DELETE");
        System.out.println(flag);
    }
}