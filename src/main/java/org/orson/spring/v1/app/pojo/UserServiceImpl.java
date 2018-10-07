package org.orson.spring.v1.app.pojo;

/**
 * Created by orson on 2018/10/7.
 */
public class UserServiceImpl implements UserService{
    @Override
    public void sayHello(String name) {
        System.out.println(String.format("Hello, my name is %s", name));
    }
}
