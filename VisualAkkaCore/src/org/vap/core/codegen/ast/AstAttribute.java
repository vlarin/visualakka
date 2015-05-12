/* 
 */
package org.vap.core.codegen.ast;

/**
 *
 * @author Serhii Biletskyi
 */
public class AstAttribute {

    /**
     *
     */
    public enum AtrModificator {

        /**
         *
         */
        NONE,

        /**
         *
         */
        PRIVATESET,

        /**
         *
         */
        PRIVATEGETSET,

        /**
         *
         */
        PRIVATEGET,

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
    public AtrModificator modificator;

}
