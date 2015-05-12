/* 
 */
package org.vap.core.model.micro;

import org.vap.core.model.micro.Pin;

/**
 *
 * @author Oleg Bantysh
 */
public class Argument extends Pin{
    
    private String type;
    private boolean isFixed;
    private String defaultvalue;
    private boolean hasOwnVisualization;
    private boolean isMainArg;
    
    /**
     *
     */
    public Argument(){};
    
    /**
     *
     * @param name
     * @param type
     * @param defaultValue
     * @param visualized
     */
    public Argument(String name, String type, String defaultValue, boolean visualized){
        this.name = name;
        this.type = type;
        this.isFixed = true;
        this.defaultvalue = defaultValue;
        this.hasOwnVisualization = visualized;
    }
    
    /**
     *
     * @param name
     * @param type
     */
    public Argument(String name, String type){
        this.name = name;
        this.type = type;
        this.isFixed = false;
        this.hasOwnVisualization = false;
        this.defaultvalue = null;
    }
    
    /**
     *
     * @return
     */
    public boolean isFixed(){
        return isFixed;
    }
    
    /**
     *
     * @param flag
     */
    public void setFixed(boolean flag){
        isFixed = flag;
    }
    
    /**
     *
     * @return
     */
    public String getType(){
        return type;
    }
    
    /**
     *
     * @param type
     */
    public void setType(String type){
        this.type = type;
    }
    
//    public String getDefaultValue() throws Exception{
//        if(!hasDefaultValue){
//            throw new Exception("Parameter hasn't default value");
//        }else
//            return this.defaultvalue;
//    }
    
    /**
     *
     * @return
     */
        
    public String getDefaultValue(){
        if(defaultvalue!=null)
            return this.defaultvalue;
        return "";
    }
    
//    public void setDefaultValue(String value) throws Exception{
//        if(!hasDefaultValue){
//            throw new Exception("Parameter hasn't default value");
//        }else
//             this.defaultvalue = value;
//    }

    /**
     *
     * @param value
     */
        public void setDefaultValue(String value){
        this.defaultvalue = value;
    }
    
    /**
     *
     * @return
     */
    public boolean hasOwnVisualization(){
        return this.hasOwnVisualization;
    }
    
    /**
     *
     * @param flag
     */
    public void setVisualizationFlag(boolean flag){
        this.hasOwnVisualization = flag;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEntry() {
        return true;
    }

    /**
     * @return the isMainArg
     */
    public boolean isIsMainArg() {
        return isMainArg;
    }

    /**
     * @param isMainArg the isMainArg to set
     */
    public void setIsMainArg(boolean isMainArg) {
        this.isMainArg = isMainArg;
    }
    
}
