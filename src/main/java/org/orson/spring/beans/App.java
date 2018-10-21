package org.orson.spring.beans;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * Created by orson on 2018/10/14.
 */
public class App {

    public static void main(String[] args) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);

        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

        for(PropertyDescriptor pd : pds) {
            String name = pd.getName();
            System.out.println(name);
        }
    }
}
