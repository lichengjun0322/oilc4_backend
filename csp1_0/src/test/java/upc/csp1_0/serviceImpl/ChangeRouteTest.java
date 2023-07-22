package upc.csp1_0.serviceImpl;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChangeRouteTest {

   @Test
    public void reRoute() throws UnirestException {
        ChangeRoute changeRoute = new ChangeRoute();
        String res = changeRoute.reRoute("101","openflow:1","101",
                "400","fool","10.0.0.1/32","2");
        System.out.println(res);
    }
}