package upc.csp1_0.serviceImpl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import upc.csp1_0.model.UserBean;
import upc.csp1_0.model.UserRegist;
import upc.csp1_0.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    public void addUser() {
        UserBean userBean = new UserBean();
        UserRegist userRegist = new UserRegist();
        userBean.setId(4);
        userBean.setName("d");
        userBean.setPassword("d");
        userService.addUser(userRegist);
    }
}