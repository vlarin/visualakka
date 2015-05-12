/* 
 */
package org.vap.core.model.micro;

/**
 *
 * @author Oleg Bantysh
 */
public class Result extends Pin{
    
    private String type = "String";
    
    /**
     *
     */
    public Result(){};
    
    /**
     *
     * @param name
     * @param type
     */
    public Result(String name, String type){
        this.name = name;
        this.type = type;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEntry() {
        return false;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
}
