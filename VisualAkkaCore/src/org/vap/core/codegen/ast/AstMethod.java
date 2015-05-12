/* 
 */
package org.vap.core.codegen.ast;

import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class AstMethod {

    /**
     *
     */
    public enum MethodModificator {

        /**
         *
         */
        PRIVATE,

        /**
         *
         */
        PROTECTED,

        /**
         *
         */
        PUBLIC

    }

    /**
     *
     */
    public String name;

    /**
     *
     */
    public String type;

    /**
     *
     */
    public String text;

    /**
     *
     */
    public MethodModificator modificator;

    /**
     *
     */
    public ArrayList<AstAttribute> attributes = new ArrayList<AstAttribute>();

}
