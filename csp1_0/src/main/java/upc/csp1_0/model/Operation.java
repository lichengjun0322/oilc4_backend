package upc.csp1_0.model;

import org.springframework.stereotype.Component;

@Component
public class Operation {
    private String user;
    private String data;
    private String operation;
    private String status;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "user='" + user + '\'' +
                ", data='" + data + '\'' +
                ", operation='" + operation + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
