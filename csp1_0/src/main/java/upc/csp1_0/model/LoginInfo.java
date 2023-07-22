package upc.csp1_0.model;

import org.springframework.stereotype.Component;

@Component
/**
 * @description:登录用户
 */
public class LoginInfo {
    private String name;
    private String password;

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

    @Override
    public String toString() {
        System.out.print(name);
        System.out.print(password);
        return "LoginInfo{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';

    }
}
