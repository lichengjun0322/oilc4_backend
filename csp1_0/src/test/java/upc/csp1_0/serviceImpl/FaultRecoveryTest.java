package upc.csp1_0.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import upc.csp1_0.dao.OperationMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FaultRecoveryTest {
    @Autowired
    FaultRecovery faultRecovery;
    @Autowired
    OperationMapper operationMapper;

    @Test
    public void getLinksTest() throws UnirestException, JsonProcessingException {
        String[][] links = faultRecovery.getLinks();
        for(int i = 0;i < links.length;i++){
            System.out.println(links[i][0] + "---" + links[i][1]);
        }
        System.out.println(links.length);
    }

    @Test
    public void findFaultTest() throws UnirestException, JsonProcessingException {
        String[][] result = faultRecovery.findFault();
        for (String[] temp:result
        ) {
            System.out.println(temp[0] + "---" + temp[1]);
        }
    }
    @Test
    public void initialNet(){
       String[][] list = faultRecovery.getInitialNet();
        for (String[] temp:list
             ) {
            System.out.println(temp[0] + "---" + temp[1]);
        }
    }


}