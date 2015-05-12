/* 
 */
package org.vap.core.codegen;

import java.io.File;
import java.util.ArrayList;
import org.vap.core.codegen.ast.AstType;

/**
 *
 * @author Serhii Biletskyi
 */
public interface AbstractCodeParser {

    /**
     *
     * @param file
     */
    public void init(File file);

    /**
     *
     * @return
     */
    public ArrayList<String> getTypeNames();

    /**
     *
     * @return
     */
    public ArrayList<AstType> getTypes();
}
