/* 
 */
package org.vap.core.unitmanager;

import java.util.Set;
import org.vap.core.model.micro.Module;

/**
 *
 * @author Oleg Bantysh
 */
public interface UnitManager {

    /**
     *
     * @param moduleSignature
     * @return
     */
    public Module findModule(String moduleSignature);

    /**
     *
     */
    public void initManager();

    /**
     *
     * @param m
     */
    public void addModule(Module m);

    /**
     *
     * @return
     */
    public Set<String> getpackageList();

    /**
     *
     * @param packName
     * @return
     */
    public Set<Module> getPackageModules(String packName);

    /**
     *
     * @return
     */
    public Set<String>getModuleList();

    /**
     *
     * @param m
     */
    public void saveModule(Module m);
    
}
