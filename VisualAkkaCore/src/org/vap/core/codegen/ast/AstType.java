/* 
 */
package org.vap.core.codegen.ast;

import java.util.ArrayList;

/**
 *
 * @author Serhii Biletskyi
 */
public class AstType {

    /**
     *
     */
    public String name;

    /**
     *
     */
    public String packageName;

    /**
     *
     */
    public ArrayList<AstAttribute> attributes=new ArrayList<AstAttribute>();

}
