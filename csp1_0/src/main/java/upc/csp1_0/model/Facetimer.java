package upc.csp1_0.model;

import org.springframework.stereotype.Component;

@Component
public class Facetimer {
    private Integer data;
    private Integer value;
    private Integer id;

    public Integer getData() {
        return data;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
