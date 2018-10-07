package org.orson.spring.v1.app.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by orson on 2018/10/7.
 */
public class BeanDefinition {

    private String id;

    private Class<?> targetType;

    private List<PropertyValue> propertyValueList = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class<?> getTargetType() {
        return targetType;
    }

    public void setTargetType(Class<?> targetType) {
        this.targetType = targetType;
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    public void setPropertyValueList(List<PropertyValue> propertyValueList) {
        this.propertyValueList = propertyValueList;
    }
}
