package upc.csp1_0.serviceImpl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class neo4jServiceImplTest {
    neo4jServiceImpl neo4jService = new neo4jServiceImpl();

    @Test
    public void addSwitchRelationship() {
        neo4jService.addSwitchRelationship("openflow:4","openflow:5");
    }
}