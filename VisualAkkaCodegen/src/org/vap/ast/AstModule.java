/* 
 */
package org.vap.ast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Stack;
import org.openide.util.lookup.ServiceProvider;
import org.vap.core.codegen.AbstractCodeParser;
import org.vap.core.codegen.ast.AstType;

/**
 *
 * @author Serhii Biletskyi
 */
@ServiceProvider(service = AbstractCodeParser.class)
public class AstModule implements AbstractCodeParser {

    private File file;
    ArrayList<AstType> types;

    @Override
    public void init(File file) {
        this.file = file;
        Stack<File> stack = new Stack<File>();
        String[] sa = this.file.list(
                new FilenameFilter() {
                    public boolean accept(File d, String n) {
                        return new File(d, n).isDirectory();
                    }
                }
        );
        for (String s : sa) {
            stack.push(new File(this.file, s));
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
                    types.addAll(atb.getTypes());
                }
            }
        }
    }

    @Override
    public ArrayList<String> getTypeNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (AstType a : types) {
            names.add(a.name);
        }
        return names;
    }

    @Override
    public ArrayList<AstType> getTypes() {
        return types;
    }
}
