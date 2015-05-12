/* 
 */
package org.vap.core.model.micro;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Oleg Bantysh
 */
public class Property {

    /**
     *
     */
    public String value;
    
    /**
     *
     */
    public Property(){};

    /**
     *
     * @param value
     */
    public Property(String value){
        this.value = value;
    }
    
    @Override
    public String toString(){
        return value;
    }
    
    /**
     *
     * @param properties
     * @return
     */
    public static ConcurrentHashMap<String, String> ToStringProperties(ConcurrentHashMap<String, Property> properties){
        ConcurrentHashMap<String, String> result = new ConcurrentHashMap();
        for(String key: properties.keySet()){
            result.put(key, properties.get(key).value);
        }
        return result;
    }
}
