/* 
 */
package org.vap.core.model.micro;

import org.vap.core.model.micro.Pin;
import org.vap.core.model.micro.Result;
import java.util.ArrayList;

/**
 *
 * @author Oleg Bantysh
 */
public class Method {
    private String name;
    private Argument mainArg;
    private ArrayList<Argument> arguments;
    private ArrayList<Result> results;
    
    /**
     *
     */
    public Method(){
        arguments = new ArrayList();
        results = new ArrayList();
    };
    
    /**
     *
     * @param name
     * @param ma
     */
    public Method(String name, Argument ma){
        this.name = name;
        this.mainArg = ma;
        arguments = new ArrayList();
        results = new ArrayList();
    }
    
    /**
     *
     * @param name
     * @param args
     * @param ress
     * @param ma
     */
    public Method(String name, ArrayList<Argument> args,ArrayList<Result> ress, Argument ma){
        this.name = name;
        this.arguments = args;
        this.results = ress;
        this.mainArg = ma;
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
     * @param param
     */
    public void addParameter(Pin param){
        if(param.isEntry()){
            this.getArguments().add((Argument)param);
        }else{
            this.getResults().add((Result)param);
        }
    }
    
    /**
     *
     * @param param
     */
    public void removeParameter(Pin param){
        if(param.isEntry()){
            this.getArguments().remove((Argument)param);
        }else{
            this.getResults().remove((Result)param);
        }
    }
    
    /**
     *
     * @param name
     * @param isArgument
     */
    public void removeParameter(String name, boolean isArgument){
        if(isArgument){
            for(int i = 0; i<getArguments().size(); i++){
                Argument param = getArguments().get(i);
                if(param.getName().contains(name)&&name.contains(param.getName())){
                    getArguments().remove(i);
                    return;
                }
            }
        }else{
            for(int i = 0; i<getResults().size(); i++){
                Result param = getResults().get(i);
                if(param.getName().contains(name)&&name.contains(param.getName())){
                    getResults().remove(i);
                    return;
                }
            }
        }
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Argument> getArguments(){
        return arguments;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Result> getResults(){
        return results;
    }
    
    /**
     *
     * @param name
     * @return
     */
    public Argument getArgumentByName(String name){
        for(int i = 0; i<getArguments().size(); i++){
            Argument param = getArguments().get(i);
            if(param.getName().contains(name)&&name.contains(param.getName())){
                return param;
            }
        }
        return null;
    }
    
    /**
     *
     * @param name
     * @return
     */
    public Result getResultByName(String name){
        for(int i = 0; i<getResults().size(); i++){
            Result param = getResults().get(i);
            if(param.getName().contains(name)&&name.contains(param.getName())){
                return param;
            }
        }
        return null;
    }
    
    /**
     *
     * @param name
     * @return
     */
    public Pin getParameterByName(String name){
        for(int i = 0; i<getArguments().size(); i++){
            Argument param = getArguments().get(i);
            if(param.getName().contains(name)&&name.contains(param.getName())){
                return param;
            }
        }
        for(int i = 0; i<getResults().size(); i++){
            Result param = getResults().get(i);
            if(param.getName().contains(name)&&name.contains(param.getName())){
                return param;
            }
        }
        return null;
    }
    
    /**
     *
     * @return
     */
    public boolean hasMainArg(){
        return getMainArg()==null;
    }
    
    /**
     *
     * @return
     */
    public Argument getMainArg(){
        return mainArg;
    }

    /**
     * @param mainArg the mainArg to set
     */
    public void setMainArg(Argument mainArg) {
        this.mainArg = mainArg;
    }

    /**
     * @param arguments the arguments to set
     */
    public void setArguments(ArrayList<Argument> arguments) {
        this.arguments = arguments;
    }

    /**
     * @param results the results to set
     */
    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    /**
     *
     * @return
     */
    public boolean hasMultiArgs() {
        return arguments.size() > 1;
    }
    
    
}
