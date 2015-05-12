/* 
 */
package org.vap.ast;

import org.vap.core.codegen.ast.AstMethod;
import org.vap.core.codegen.ast.AstType;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Serhii Biletskyi
 */
public class AstSingleton {

    protected ArrayList<AstType> types = new ArrayList<AstType>();
    protected ArrayList<AstMethod> methods = new ArrayList<AstMethod>();

    private static AstSingleton instance = new AstSingleton();

    private AstSingleton() {

    }

    public ArrayList<AstType> getTypes() {
        return types;
    }

    public ArrayList<AstMethod> getMethods() {
        return methods;
    }

    public void update(File file) {
        types = new ArrayList<AstType>();
        methods = new ArrayList<AstMethod>();
        Stack<File> stack = new Stack<File>();
        String[] sa = file.list(
                new FilenameFilter() {
                    public boolean accept(File d, String n) {
                        return new File(d, n).isDirectory();
                    }
                }
        );
        for (String s : sa) {
            stack.push(new File(file, s));
        }
        while (!stack.isEmpty()) {
            File dir = stack.pop();
            for (String s : dir.list()) {
                File f = new File(dir, s);
                if (f.isDirectory()) {
                    stack.push(f);
                } else if (s.endsWith(".java")) {
                    AstTreeBuilder atb = new AstTreeBuilder(f.getAbsolutePath());
                    atb.generateTypesFromUnits();
                    ArrayList<AstType> temp = atb.getTypes();
                    if (temp!=null) {
                        types.addAll(temp);
                    }
                }
            }
        }
        stack = new Stack<File>();
        for (String s : sa) {
            stack.push(new File(file, s));
        }
        while (!stack.isEmpty()) {
            File dir = stack.pop();
            for (String s : dir.list()) {
                File f = new File(dir, s);
                if (f.isDirectory()) {
                    stack.push(f);
                } else if (s.endsWith(".java")) {
                    AstTreeBuilder atb = new AstTreeBuilder(f.getAbsolutePath());
                    atb.setTypes(types);
                    atb.generateMethodsFromUnits();
                    ArrayList<AstMethod> temp = atb.getMethods();
                    if (!temp.isEmpty()) {
                        methods.addAll(temp);
                    }
                }
            }
        }

    }

    public ArrayList<String> getTypeNames() {
        ArrayList<String> names = new ArrayList<String>();
        if (types != null) {
            for (AstType a : types) {
                names.add(a.name);
            }
        }
        return names;
    }

    public static AstSingleton getInstance() {
        return instance;
    }

}
