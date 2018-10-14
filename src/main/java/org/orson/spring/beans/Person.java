package org.orson.spring.beans;

import java.util.List;

/**
 * Created by orson on 2018/10/14.
 */
public class Person {

    private String name;

    private Integer age;

    private List<Hoppy> hoppis;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Hoppy> getHoppis() {
        return hoppis;
    }

    public void setHoppis(List<Hoppy> hoppis) {
        this.hoppis = hoppis;
    }
}
