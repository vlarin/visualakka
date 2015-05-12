/* 
 */
package org.vap.core.model.macro;

import java.util.ArrayList;
import org.vap.core.model.micro.Argument;
import org.vap.core.model.micro.Method;
import org.vap.core.model.micro.Module;
import org.vap.core.model.micro.Result;

/**
 *
 * @author Oleg Bantysh
 */
//@XmlRootElement(name="workspace")
//@XmlRootElement
public class Workspace {

    private String refModuleName;
    private String refModulePackage;
    private ArrayList<VFLayer> layers;
    private VFLayer activeLayer;
    private ArrayList<State> states;

    /**
     *
     */
    public Workspace() {
        layers = new ArrayList();
        states = new ArrayList();
        states.add(new State("Default"));
    }

    ;
    
    /**
     *
     * @param name
     * @param pack
     * @param modRef
     */
    public Workspace(String name, String pack, Module modRef) {
        refModuleName = name;
        refModulePackage = pack;
        layers = new ArrayList();
        states = new ArrayList();
        states.add(new State("Default"));
    }

    /**
     *
     * @param name
     * @param pack
     * @param modRef
     * @param commLayer
     * @param layers
     */
    public Workspace(String name, String pack, Module modRef, VFLayer commLayer,
            ArrayList<VFLayer> layers) {
        refModuleName = name;
        refModulePackage = pack;
        this.layers = layers;
    }

    /**
     *
     * @param m
     */
    public void addConcreticisedMethod(ConcreticisedMethod m) {
        getActiveLayer().units.add(m);
    }

    /**
     *
     * @param e
     */
    public void addEntry(Entry e) {
        getActiveLayer().entries.add(e);
    }

    /**
     *
     * @param e
     */
    public void addExit(Exit e) {
        getActiveLayer().exits.add(e);
    }

    /**
     *
     * @param name
     * @return
     */
    public VFLayer findMethod(String name) {
        for (VFLayer l : getLayers()) {
            if (l.methodName.equals(name)) {
                return l;
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    public Module formModule() {
        Module mtr = new Module(getRefModuleName(), getRefModulePackage());
        //Common objects
        ArrayList<Argument> cargs = new ArrayList();
        ArrayList<Result> cress = new ArrayList();
        //Method related objects
        for (VFLayer l : getLayers()) {
            Method m = new Method();
            m.setName(l.methodName);
            for (Argument a : cargs) {
                m.addParameter(a);
            }
            for (Result a : cress) {
                m.addParameter(a);
            }
            for (Entry e : l.entries) {
                m.addParameter(e.getRefArg());
            }
            for (Exit e : l.exits) {
                m.addParameter(e.getRefRes());
            }
            mtr.addMethod(m);
        }
        return mtr;
    }

    /**
     *
     * @param mtr
     * @return
     * @throws Exception
     */
    public Module updateModule(Module mtr) throws Exception {
        if (!refModuleName.equals(mtr.getName()) || !refModulePackage.equals(mtr.getPackage())) {
            throw new Exception("module and workspace names mismatch");
        }
        mtr = new Module(getRefModuleName(), getRefModulePackage());
        //Common objects
        ArrayList<Argument> cargs = new ArrayList();
        ArrayList<Result> cress = new ArrayList();
        //Method related objects
        for (VFLayer l : getLayers()) {
            Method m = new Method(l.methodName, l.mainEntry.getRefArg());
            for (Argument a : cargs) {
                m.addParameter(a);
            }
            for (Result a : cress) {
                m.addParameter(a);
            }
            for (Entry e : l.entries) {
                m.addParameter(e.getRefArg());
            }
            for (Exit e : l.exits) {
                m.addParameter(e.getRefRes());
            }
            mtr.addMethod(m);
        }
        return mtr;
    }

    /**
     * @return the refModuleName
     */
    public String getRefModuleName() {
        return refModuleName;
    }

    /**
     * @param refModuleName the refModuleName to set
     */
    public void setRefModuleName(String refModuleName) {
        this.refModuleName = refModuleName;
    }

    /**
     * @return the refModulePackage
     */
    public String getRefModulePackage() {
        return refModulePackage;
    }

    /**
     * @param refModulePackage the refModulePackage to set
     */
    public void setRefModulePackage(String refModulePackage) {
        this.refModulePackage = refModulePackage;
    }

    /**
     * @return the layers
     */
    public ArrayList<VFLayer> getLayers() {
        return layers;
    }

    /**
     * @param layers the layers to set
     */
    public void setLayers(ArrayList<VFLayer> layers) {
        this.layers = layers;
    }

    /**
     *
     * @return
     */
    public VFLayer getActiveLayer() {
        return activeLayer;
    }

    /**
     *
     * @param name
     */
    public void setActiveLayer(String name) {
        for (VFLayer layer : this.layers) {
            if (name.equals(layer.methodName)) {
                activeLayer = layer;
            }
        }
    }

    /**
     *
     */
    public void setDefault(){
        activeLayer = layers.get(0);
    }
    
    /**
     *
     * @return
     */
    public String[] getMethodNames() {
        ArrayList<String> res = new ArrayList();
        for (VFLayer l : layers) {
            res.add(l.methodName);
        }
        return res.toArray(new String[res.size()]);
    }
    
    /**
     *
     * @param l
     */
    public void removeLayer(VFLayer l){
        layers.remove(l);
    }

    /**
     * @return the states
     */
    public ArrayList<State> getStates() {
        return states;
    }

    /**
     * @param states the states to set
     */
    public void setStates(ArrayList<State> states) {
        this.states = states;
    }
    
    /**
     *
     * @param s
     */
    public void addState(State s){
        this.states.add(s);
    }
    
    /**
     *
     * @param s
     */
    public void removeState(State s){
        this.states.remove(s);
    }

    /**
     *
     * @param s
     * @return
     */
    public State getState(String s){
        for(State st: states){
            if(s.equals(st.getName())){
                return st;
            }
        }
        return null;
    }
    
//    /**
//     *
//     */
//    public void fixObjectReferences(){
//        for(VFLayer layer : layers){
//            layer.fixObjectReference();
//        }
//    }

}
