/* 
 */
package org.vap.core.codeexecuter;

import org.openide.util.Lookup;
import org.vap.core.model.macro.Workspace;

/**
 *
 * @author Oleg Bantysh
 */
public class GeneratorInitializer {

    /**
     *
     * @param ws
     */
    public static void init(Workspace ws){
        AbstractGenerator ae = (AbstractGenerator) Lookup.getDefault().
                lookup(AbstractGenerator.class);
    }
}
