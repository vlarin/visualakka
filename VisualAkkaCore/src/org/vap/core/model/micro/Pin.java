/* 
 */
package org.vap.core.model.micro;

/**
 *
 * @author Oleg Bantysh
 */
public abstract class Pin {

    /**
     *
     */
    protected String name;

    /**
     *
     * @return
     */
    public abstract boolean isEntry();
    
    /**
     *
     * @return
     */
    public String getName(){
        return name;
    }
    
    /**
     *
     * @return
     */
    public String getIdentificator() {        
        return name.replace(' ', '_');
    }
    
    /**
     *
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }
}
