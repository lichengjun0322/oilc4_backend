package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import upc.csp1_0.model.FlowCache;

@Repository
public interface FlowDao {

    /**
     * 插入流表
     * @param flow_id
     * @param node_id
     * @param table_id
     * @param priority
     * @param flow_name
     * @param source_ip
     * @param out_port
     * @return
     */
    public int addFlow(@Param("flow_id") String flow_id, @Param("node_id") String node_id, @Param("table_id") String table_id, @Param("priority") String priority, @Param("flow_name") String flow_name, @Param("source_ip") String source_ip, @Param("out_port") String out_port);

    public FlowCache find(String flow_id);
}
