package org.orson.spring.v1.app.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.orson.spring.v1.app.bean.BeanDefinition;
import org.orson.spring.v1.app.bean.PropertyValue;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by orson on 2018/10/7.
 */
public class SimpleBeanFactory implements BeanFactory{

    /**
     * bean registry
     */
    Map<String, BeanDefinition> beanDefinitionRegistry = new HashMap<>();

    /**
     * stores the bean instances
     */
    Map<String, Object> beanRegistry = new HashMap<>();

    /**
     * xml resources
     */
    String[] resource;

    /**
     * empty constructor
     */
    public SimpleBeanFactory() {
    }

    /**
     * initialize with xml resources
     * @param resource
     */
    public SimpleBeanFactory(String...resource) {
        this.resource = resource;

        //init application context
        this.refresh();
    }

    public Object getBean(String name) {
        return beanRegistry.get(name);
    }

    public <T> T getBean(String name, Class<T> type) {

        Object bean = beanRegistry.get(name);

        if(type.isInstance(bean)) {
            return (T)bean;
        }

        //throw exception if bean not found
        throw new RuntimeException("no such bean found");
    }


    /**
     * init application context
     */
    public void refresh() {
        //locate resources
        String[] resources = this.getResource();

        //load bean definition
        for(String resource : resources) {
            this.loadBeanDefinition(resource);
        }

        //registry bean
        this.initializeBean();
    }

    /**
     * create bean
     */
    private void initializeBean() {

        Set<String> keySet = this.beanDefinitionRegistry.keySet();

        for(String key : keySet) {

            if(this.beanDefinitionRegistry.containsKey(key)) {

                BeanDefinition bd = this.beanDefinitionRegistry.get(key);

                String beanName = bd.getId();

                Class<?> type = bd.getTargetType();

                try {
                    if(this.beanRegistry.containsKey(beanName)) {
                        Object target = this.beanRegistry.get(beanName);

                        //apply properties
                        List<PropertyValue> pvList = bd.getPropertyValueList();
                        for(PropertyValue pv : pvList) {

                            String name = pv.getName();
                            String ref = pv.getRef();

                            Field field = type.getDeclaredField(name);
                            field.setAccessible(true);

                            //set target properties
                            field.set(target, this.beanRegistry.get(ref));
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * parse xml into BeanDefinition object
     * @param resource
     */
    private void loadBeanDefinition(String resource) {
        SAXReader reader = new SAXReader();

        try(InputStream is = this.getClass().getClassLoader().getResourceAsStream(resource)) {
            Document document = reader.read(is);
            Element root = document.getRootElement();

            //do load bean definition
            this.doLoadBeanDefinition(root);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }


    }

    private void doLoadBeanDefinition(Element root) {
        Iterator<Element> iterator = root.elementIterator();

        while(iterator.hasNext()) {
            //get child element
            Element child = iterator.next();

            if(child.getName().equals("bean")) {
                prseBeanDefinition(child);
            }

        }
    }

    private void prseBeanDefinition(Element child) {

        BeanDefinition definition = new BeanDefinition();


        String id = child.attributeValue("id");
        String clazz = child.attributeValue("class");


        definition.setId(id);
        try {
            //create an instance
            Class<?> targetCls = Class.forName(clazz);
            Object instance = targetCls.newInstance();
            //store bean instance
            this.beanRegistry.put(id, instance);

            definition.setTargetType(targetCls);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        //parse and set property values
        this.parsePropertyValues(child, definition);

        //save parsed bean definition
        this.beanDefinitionRegistry.put(id, definition);

    }

    private void parsePropertyValues(Element child, BeanDefinition definition) {

        List<Element> propertyElements = child.elements("property");

        List<PropertyValue> propertyValues = new ArrayList<>();

        //parser property elements
        for(Element property : propertyElements) {
            PropertyValue pv = new PropertyValue();
            String name = property.attributeValue("name");
            String ref = property.attributeValue("ref");

            pv.setName(name);
            pv.setRef(ref);

            propertyValues.add(pv);
        }

        definition.setPropertyValueList(propertyValues);

    }


    public Map<String, BeanDefinition> getBeanDefinitionRegistry() {
        return beanDefinitionRegistry;
    }

    public void setBeanDefinitionRegistry(Map<String, BeanDefinition> beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public Map<String, Object> getBeanRegistry() {
        return beanRegistry;
    }

    public void setBeanRegistry(Map<String, Object> beanRegistry) {
        this.beanRegistry = beanRegistry;
    }

    public String[] getResource() {
        return resource;
    }

    public void setResource(String[] resource) {
        this.resource = resource;
    }
}
