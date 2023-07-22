package upc.csp1_0.model;

import org.springframework.stereotype.Component;

@Component
public class netWork {
    private String sNode;
    private String dNode;

    public String getsNode() {
        return sNode;
    }

    public void setsNode(String sNode) {
        this.sNode = sNode;
    }

    public String getdNode() {
        return dNode;
    }

    public void setdNode(String dNode) {
        this.dNode = dNode;
    }

    @Override
    public String toString() {
        return "netWork{" +
                "sNode='" + sNode + '\'' +
                ", dNode='" + dNode + '\'' +
                '}';
    }
}
