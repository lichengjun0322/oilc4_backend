package upc.csp1_0.model;

import org.springframework.stereotype.Component;

@Component
public class FlowOfInsert {
    private String node_id;
    private String table_id;
    private String flow_id;
    private String priority;
    private String flow_name;
    private String source_ip;
    private String output_port;

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getFlow_name() {
        return flow_name;
    }

    public void setFlow_name(String flow_name) {
        this.flow_name = flow_name;
    }

    public String getSource_ip() {
        return source_ip;
    }

    public void setSource_ip(String source_ip) {
        this.source_ip = source_ip;
    }

    public String getOutput_port() {
        return output_port;
    }

    public void setOutput_port(String output_port) {
        this.output_port = output_port;
    }

    @Override
    public String toString() {
        return "FlowOfInsert{" +
                "node_id='" + node_id + '\'' +
                ", table_id='" + table_id + '\'' +
                ", flow_id='" + flow_id + '\'' +
                ", priority='" + priority + '\'' +
                ", flow_name='" + flow_name + '\'' +
                ", source_ip='" + source_ip + '\'' +
                ", output_port='" + output_port + '\'' +
                '}';
    }
}
