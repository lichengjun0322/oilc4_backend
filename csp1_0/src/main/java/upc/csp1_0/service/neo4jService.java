package upc.csp1_0.service;

import java.util.List;
import java.util.Map;

public interface neo4jService {
    public void createSwitchNode(String node_id);
    public void deleteSwitchNode(String node_id);
    public void createFlowNode(String flow_id,String node_id,String table_id,String priority,
                               String flow_name,String source_ip,String out_port,String type);
    public void deleteFlowNode(String flow_id,String node_id);
    public void addSwitchRelationship(String node_id1,String node_id2);
    public void deleteSwitchRelationship(String node_id1,String node_id2);
    public void addSwithcFlowRelationship(String node_id1,String Flow_id,String node_id2);
    public void deleteSwitchFlowRelationship(String node_id1,String flow_id,String node_id2);
    public Map<String,List> queryAll();
}
