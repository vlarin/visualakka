/* 
 */
package org.vap.unitmananger;

import com.google.common.collect.HashMultimap;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import javax.xml.bind.JAXBException;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;
import org.vap.core.model.micro.Module;
import org.vap.core.unitmanager.UnitManager;

/**
 *
 * @author Oleg Bantysh
 */
@ServiceProvider (service = UnitManager.class)
public class Manager implements UnitManager{
    private static HashMap<String, Module> loadedModules = new HashMap();
    private static HashMultimap<String, Module> packageGroups = HashMultimap.create();

    /**
     *
     * @param moduleSignature
     * @return
     */
    @Override
    public Module findModule(String moduleSignature){
        return loadedModules.get(moduleSignature);
    }

    /**
     *
     */
    @Override
    public void initManager() {
//        File f = new File(DEFAULT_FOLDER);
//        
//        if (!f.exists()) {
//            f.mkdir();
//        }
//        
//        File[] fs = f.listFiles();
//        for(File mf: Arrays.asList(fs)){
//            Module m = UnitLoader.loadModule(FileUtil.toFileObject(mf));
//            addModule(m);
//        }
//        formPackages();
        addModule(new Module( "UserCodeBlocks", "System"));
    }

    /**
     *
     * @param m
     */
    @Override
    public void addModule(Module m) {
        loadedModules.put(m.toString(), m);
    }
    
    private void formPackages(){
        for(Module m: loadedModules.values()){
            packageGroups.put(m.getPack(), m);
        }
    }
    
    /**
     *
     * @param packName
     * @return
     */
    @Override
    public Set<Module> getPackageModules(String packName){
        return packageGroups.get(packName);
    }
    
    /**
     *
     * @return
     */
    @Override
    public Set<String> getpackageList(){
        return packageGroups.keySet();
    }
    
    /**
     *
     * @return
     */
    @Override
    public Set<String>getModuleList(){
        return loadedModules.keySet();
    }

    /**
     *
     * @param m
     */
    @Override
    public void saveModule(Module m) {
//        File f = new File(DEFAULT_FOLDER);
//        File[] fs = f.listFiles();
//        for(File mf: Arrays.asList(fs)){
//            if(mf.getName().equals(m.getPack()+"."+m.getName())){
//                try {
//                    UnitLoader.uploadModule(FileUtil.toFileObject(mf), m);
//                } catch (JAXBException ex) {
//                    Exceptions.printStackTrace(ex);
//                }
//                return;
//            }
//        }
//        File mf = new File(f.getAbsolutePath(), m.getPack()+"."+m.getName()+".vam");
//        try {
//            mf.createNewFile();
//        } catch (IOException ex) {
//            Exceptions.printStackTrace(ex);
//        }
//        try {
//            UnitLoader.uploadModule(FileUtil.toFileObject(mf), m);
//        } catch (JAXBException ex) {
//            Exceptions.printStackTrace(ex);
//        }
    }
    
}
