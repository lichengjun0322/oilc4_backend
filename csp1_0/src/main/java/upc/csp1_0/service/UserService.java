package upc.csp1_0.service;

import upc.csp1_0.model.UserBean;
import upc.csp1_0.model.UserRegist;

public interface UserService {
    /**
     * @description:登录
     */
    UserBean loginIn(String name,String password);
    /**
     * @description:注册后添加登录信息
     */
     void addUser(UserRegist userRegist);
    /**
     * @description:添加注册信息
     */
     void addUserInfo(UserRegist userRegist);
    /**
     * @description:添加当前登录用户的信息
     */
     void addLoginer(String name);
    /**
     * @description:删除用户退出后的登录信息
     */
     void delLoginer();
    /**
     * @description:获得当前登录用户的信息
     */
     String getLoginer();
}
