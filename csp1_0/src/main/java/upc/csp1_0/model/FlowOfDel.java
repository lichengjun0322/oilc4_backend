package upc.csp1_0.model;

import org.springframework.stereotype.Component;

@Component
public class FlowOfDel {
    private String table_id;
    private String flow_id;
    private String node_id;

    public String getTable_id() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }

    public String getFlow_id() {
        return flow_id;
    }

    public void setFlow_id(String flow_id) {
        this.flow_id = flow_id;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    @Override
    public String toString() {
        return "FlowOfDel{" +
                "table_id='" + table_id + '\'' +
                ", flow_id='" + flow_id + '\'' +
                ", node_id='" + node_id + '\'' +
                '}';
    }
}
