package upc.csp1_0.serviceImpl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class neo4jTest {


    @Autowired
    neo4jServiceImpl neo4jService;


    @Test
    public void Test1(){
        neo4jService.createFlowNode("test1","openflow:6","0","0","flow:1",
                "null","drop","CONTROLLER");
        neo4jService.createFlowNode("test2","openflow:6","0","2","flow:2",
                "port:3","2,1,CONTROLLER:65535","CONTROLLER");
        //neo4jService.deleteFlowNode("200","openflow:1");
    }

    @Test
    public void Test2(){
        neo4jService.createSwitchNode("openflow:1");
        neo4jService.createSwitchNode("openflow:2");
        neo4jService.createSwitchNode("openflow:3");
        neo4jService.createSwitchNode("openflow:4");
        neo4jService.createSwitchNode("openflow:5");
        neo4jService.createSwitchNode("openflow:6");
    }

    @Test
    public void Tset3(){
        neo4jService.addSwitchRelationship("openflow:5","openflow:2");
        neo4jService.addSwitchRelationship("openflow:6","openflow:2");
        neo4jService.addSwitchRelationship("openflow:3","openflow:1");
        neo4jService.addSwitchRelationship("openflow:4","openflow:1");
        neo4jService.addSwitchRelationship("openflow:1","openflow:2");
        //neo4jService.deleteSwitchRelationship("openflow:2","openflow:1");
    }

    @Test
    public void Test4(){
        neo4jService.addSwithcFlowRelationship("openflow:6","test1","openflow:6");
        neo4jService.addSwithcFlowRelationship("openflow:6","test2","openflow:6");
        /*neo4jService.deleteSwitchFlowRelationship("openflow:6","test1","openflow:6");
        neo4jService.deleteSwitchFlowRelationship("openflow:6","test2","openflow:6");*/
        //neo4jService.addSwithcFlowRelationship("openflow:3","4","openflow:3");
        //neo4jService.deleteSwitchFlowRelationship("openflow:1","200","openflow:1");
    }

    @Test
    public void Test5(){

        neo4jService.queryAll();
    }





}


