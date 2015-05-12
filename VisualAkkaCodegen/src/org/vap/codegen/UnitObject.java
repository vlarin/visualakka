/* 
 */
package org.vap.codegen;

import java.io.Serializable;
import java.util.ArrayList;
import org.vap.core.model.macro.VFLayer;
import org.vap.core.model.macro.Workspace;

/**
 *
 * @author Vladislav Larin
 */
public class UnitObject implements Serializable {
    public String moduleName;
    public String modulePackage;   
    
    public ArrayList<VFLayer> layers;
    
    public UnitObject(Workspace source) {
        
        moduleName = source.getRefModuleName();
        modulePackage = source.getRefModulePackage();
        
        layers = new ArrayList<VFLayer>();
        
        layers.addAll(source.getLayers());
    }
}
