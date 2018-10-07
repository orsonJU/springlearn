package org.orson.spring.v1.app;

import org.orson.spring.v1.app.core.SimpleBeanFactory;
import org.orson.spring.v1.app.pojo.UserAction;

/**
 * Created by orson on 2018/10/7.
 */
public class App {

    public static void main(String[] args) {

        SimpleBeanFactory beanFactory = new SimpleBeanFactory("application.xml");

        UserAction userAction = beanFactory.getBean("userAction", UserAction.class);

        userAction.action("Marting");
    }
}
