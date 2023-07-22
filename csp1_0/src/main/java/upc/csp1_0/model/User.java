package upc.csp1_0.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    private Integer id;
    private Integer code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
