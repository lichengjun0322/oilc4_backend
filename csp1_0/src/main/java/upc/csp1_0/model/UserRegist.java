package upc.csp1_0.model;

import org.springframework.stereotype.Component;
import upc.csp1_0.serviceImpl.RSAEncryption;
@Component
/**
 * @description:将要进行注册的用户
 */
public class UserRegist {
    private int id;
    private String name;
    private String password;
    RSAEncryption rsaEncryption = new RSAEncryption();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        //this.id = id;
        this.id = rsaEncryption.enRSACode(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
