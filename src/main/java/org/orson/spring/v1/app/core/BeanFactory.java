package org.orson.spring.v1.app.core;

/**
 * Created by orson on 2018/10/7.
 */
public interface BeanFactory {

    /**
     * get bean by name
     * @param name
     * @return
     */
    public Object getBean(String name);

    /**
     * return the required type object bean.
     * @param name
     * @param type
     * @param <T>
     * @return
     */
    public <T> T getBean(String name, Class<T> type);
}
