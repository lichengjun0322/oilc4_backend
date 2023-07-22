package upc.csp1_0.serviceImpl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class NetInfoTest {
    @Autowired
    NetInfo netInfo;

    @Test
    public void getSwitchNumber() {
        System.out.println(netInfo.getSwitchNumber());
    }

    @Test
    public void getLinks(){
        String[][] links = netInfo.getLinks();
        System.out.println(links);
    }

    @Test
    public void getSwitches(){
        System.out.println(netInfo.getSwitch());
    }
}
