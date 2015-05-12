/* 
 */
package org.vap.unitmananger;

import org.openide.modules.ModuleInstall;

/**
 *
 * @author Олег
 */
public class Installer extends ModuleInstall {

    /**
     *
     */
    @Override
    public void restored() {
        Manager m = new Manager();
        m.initManager();
    }

}
