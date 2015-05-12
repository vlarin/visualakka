/* 
 */
package org.vap.core.filetype;

import java.io.IOException;
import org.netbeans.api.actions.Openable;
import org.netbeans.api.actions.Savable;

/**
 *
 * @author Oleg Bantysh
 */
public interface WorkspaceFile extends Openable {

    /**
     *
     */
    public void compile();
    
    /**
     *
     */
    public void makeDirty();

    /**
     *
     * @throws IOException
     */
    public void save() throws IOException ;

    /**
     *
     */
    public void compileAndRun();
}
