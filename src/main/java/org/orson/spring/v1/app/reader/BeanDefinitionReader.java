package org.orson.spring.v1.app.reader;

import org.orson.spring.v1.app.core.BeanFactory;

/**
 * Created by orson on 2018/10/7.
 */
public interface BeanDefinitionReader {


    /**
     * parse bean definition and add into bean registry.
     * @param beanFactory
     */
    public void loadBeanDefinition(BeanFactory beanFactory);
}
