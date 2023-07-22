package upc.csp1_0.service;

import org.apache.ibatis.annotations.Param;
import upc.csp1_0.model.FlowCache;
import upc.csp1_0.model.FlowOfDel;
import upc.csp1_0.model.FlowOfInsert;

import java.util.List;

public interface FlowService {

     int insertFlow(FlowOfInsert flowOfInsert);
     int addFlow(String flow_id,String node_id,String table_id,String priority,String flow_name,String source_ip,String out_port);

    int deleteFlow(FlowOfDel flowOfDel);

    boolean isExistInsert(FlowOfInsert flowOfInsert);

    boolean isConflictInsert(FlowOfInsert flowOfInsert);
    List<FlowCache> findAllFlow(@Param("start")int start, @Param("pageSize") int pageSize);
    public int getFlowCount();
}
