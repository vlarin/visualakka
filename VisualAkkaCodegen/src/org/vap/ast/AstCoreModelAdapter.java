/* 
 */
package org.vap.ast;

import org.vap.core.codegen.ast.AstAttribute;
import org.vap.core.codegen.ast.AstMethod;
import org.vap.core.model.micro.Argument;
import org.vap.core.model.micro.Method;
import org.vap.core.model.micro.Result;

/**
 *
 * @author Oleg Bantysh
 */
public class AstCoreModelAdapter {

    public static Method toModelMethod(AstMethod source) {
        Method m = new Method();
        m.setName(source.name);
        Result r = new Result();
        r.setName("Result");
        if(source.type.equals("void")){
            r.setType("String");
        }else{
            r.setType(source.type);
        }      
        m.addParameter(r);
        for (AstAttribute attr : source.attributes) {
                Argument a = new Argument();
                a.setName(attr.name);
                a.setType(attr.type);
                m.addParameter(a);
        }
        if (m.getArguments().isEmpty()) {
            Argument a = new Argument();
            a.setName(source.name);
            a.setType("String");
            m.addParameter(a);
        }
        m.getArguments().get(0).setIsMainArg(true);
        return m;
    }
}
