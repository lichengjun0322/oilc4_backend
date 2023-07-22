package upc.csp1_0.serviceImpl;

import org.junit.jupiter.api.Test;
import upc.csp1_0.model.FlowOfDel;

public class FlowServiceImplTest {

    FlowServiceImpl flowService = new FlowServiceImpl();

    FlowOfDel flowOfDel = new FlowOfDel();

    @Test
    void insertFlow() {
    }

    @Test
   public void deleteFlow() {
        flowOfDel.setFlow_id("100");
        flowOfDel.setTable_id("400");
        flowService.deleteFlow(flowOfDel);
    }
}