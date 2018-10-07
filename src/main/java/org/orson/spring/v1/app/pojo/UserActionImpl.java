package org.orson.spring.v1.app.pojo;

/**
 * Created by orson on 2018/10/7.
 */
public class UserActionImpl implements UserAction{

    UserService userService;

    @Override
    public void action(String name) {
        System.out.println("before...");
        userService.sayHello(name);
        System.out.println("before...");
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
