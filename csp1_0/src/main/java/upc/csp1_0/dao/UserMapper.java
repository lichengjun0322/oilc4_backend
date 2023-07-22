package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Mapper;
import upc.csp1_0.model.UserBean;
import upc.csp1_0.model.UserRegist;

@Mapper
public interface UserMapper {
    /**
     * @description:获取用户注册信息
     */
    UserBean getInfo(String name,String password);
    /**
     * @description:注册用户
     */
    int addUser(UserRegist userRegist);
    /**
     * @description:记录初次的用户等级等信息
     */
    int addUserInfo(UserRegist userRegist);
    /**
     * @description:添加当前登录用户的信息
     */
    int addLoginer(String name);
    /**
     * @description:删除当前登录用户信息
     */
    int delLoginer();
    /**
     * @description:获取当前登录用户的信息
     */
    String getLoginer();
    /**
     * @description:获取当前登录用户的操作等级
     */
    int getUserGrade(String name);
}
