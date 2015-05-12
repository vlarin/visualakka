/* 
 */
package org.vap.core.model.micro;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oleg Bantysh
 */
@XmlRootElement
public class Module {
    private ArrayList<Method> methods;
    private String name;
    private String pack;
    
    /**
     *
     */
    public Module(){};
    
    /**
     *
     * @param name
     * @param pack
     */
    public Module(String name, String pack){
        this.name = name;
        this.pack = pack;
        methods = new ArrayList();
    }
    
    /**
     *
     * @param name
     * @param pack
     * @param methods
     */
    public Module(String name, String pack, ArrayList<Method> methods){
        this.name = name;
        this.pack = pack;
        this.methods = methods;
    }
    
    /**
     *
     * @return
     */
    public String getName(){
        return name;
    }
    
    /**
     *
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     *
     * @return
     */
    public String getPackage(){
        return getPack();
    }
    
    /**
     *
     * @param pack
     */
    public void setPackage(String pack){
        this.setPack(pack);
    }
    
    /**
     *
     * @return
     */
    public boolean hasRepresentation(){
        return false;
    }
    
    /**
     *
     * @param param
     */
    public void addMethod(Method param){
        this.getMethods().add(param);
    }
    
    /**
     *
     * @param param
     */
    public void removeMethod(Method param){
        this.getMethods().remove(param);
    }
    
    /**
     *
     * @param name
     */
    public void removeMethod(String name){
        for(int i = 0; i<getMethods().size(); i++){
            Method param = getMethods().get(i);
            if(param.getName().contains(name)&&name.contains(param.getName())){
                getMethods().remove(i);
                return;
            }
        }
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Method> getMethods(){
        return methods;
    }
    
    /**
     *
     * @param name
     * @return
     */
    public Method getMethodByName(String name){
        for(int i = 0; i<getMethods().size(); i++){
            Method param = getMethods().get(i);
            if(param.getName().contains(name)&&name.contains(param.getName())){
                return param;
            }
        }
        return null;
    }
    
    @Override
    public String toString(){
        return getPack() + "." + getName();
    }

    /**
     * @param methods the methods to set
     */
    public void setMethods(ArrayList<Method> methods) {
        this.methods = methods;
    }

    /**
     * @return the pack
     */
    public String getPack() {
        return pack;
    }

    /**
     * @param pack the pack to set
     */
    public void setPack(String pack) {
        this.pack = pack;
    }
}
