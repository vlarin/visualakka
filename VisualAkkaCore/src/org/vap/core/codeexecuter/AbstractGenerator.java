/* 
 */
package org.vap.core.codeexecuter;

import org.vap.core.model.macro.Workspace;

/**
 *
 * @author Vladislav Larin
 */
public interface AbstractGenerator {
    
    /**
     *
     * @param workspace
     * @param rootFolder
     */
    void javaGen(Workspace workspace, String rootFolder);
}
