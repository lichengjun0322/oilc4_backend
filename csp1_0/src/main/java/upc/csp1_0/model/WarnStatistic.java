package upc.csp1_0.model;

import org.springframework.stereotype.Component;

@Component
public class WarnStatistic {
    private String data;
    private int number;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "WarnStatistic{" +
                "data='" + data + '\'' +
                ", number=" + number +
                '}';
    }
}
