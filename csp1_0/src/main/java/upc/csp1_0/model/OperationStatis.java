package upc.csp1_0.model;

import org.springframework.stereotype.Component;

@Component
public class OperationStatis {
    private String user;
    private String number;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "OperationStatis{" +
                "user='" + user + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
