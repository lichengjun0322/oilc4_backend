package upc.csp1_0.service;

import upc.csp1_0.model.FlowCache;

public interface FloService {
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
    public int addFlow(String flow_id,String node_id,String table_id,String priority,String flow_name,String source_ip,String out_port);

    public FlowCache find(String flow_id);


}
