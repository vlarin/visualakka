/* 
 */
package org.vap.core.codegen;

import com.sun.source.tree.MethodTree;
import java.util.ArrayList;

/**
 *
 * @author Oleg Bantysh
 */
public interface AbstractCodeParser {

    /**
     *
     * @param projectPath -path to sources of project that should be parsed
     */
    public void init(String projectPath);

    /**
     *
     * @return
     */
    public ArrayList<String> getTypeNames();
    
    public ArrayList<MethodTree> getMethods();
    
    
}
