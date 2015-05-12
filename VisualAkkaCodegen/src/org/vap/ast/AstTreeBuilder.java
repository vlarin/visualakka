/* 
 */
package org.vap.ast;

import org.vap.core.codegen.ast.AstMethod;
import org.vap.core.codegen.ast.AstType;
import org.vap.core.codegen.ast.AstAttribute;
import java.io.File;
import java.util.ArrayList;
import org.eclipse.jdt.internal.compiler.ast.AbstractMethodDeclaration;
import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.eclipse.jdt.internal.compiler.ast.Argument;
import org.vap.jexast.AstExtractor;
import org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;

/**
 *
 * @author Serhii Biletskyi
 */
public class AstTreeBuilder {

    public AstTreeBuilder(String filePath) {
        types = new ArrayList<AstType>();
        methods = new ArrayList<AstMethod>();
        this.setFilePath(filePath);
        try {
            boolean flag = false;
            if (this.generateTree()) {
                flag = true;
            }
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean successBuild = false;

    protected String filePath;

    protected CompilationUnitDeclaration[] units;

    protected ArrayList<AstType> types;

    protected ArrayList<AstMethod> methods;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public CompilationUnitDeclaration[] getUnits() {
        return this.units;
    }

    public void setTypes(ArrayList<AstType> types) {
        this.types = types;
    }

    public ArrayList<AstType> getTypes() {
        if (types != null) {
            return types;
        } else {
            return new ArrayList<AstType>();
        }
    }

    public ArrayList<AstMethod> getMethods() {
        if (methods != null) {
            return methods;
        } else {
            return new ArrayList<AstMethod>();
        }
    }

    public boolean checkTypeInAllowed(String searchType) {
        if (searchType.contains("void")) {
            return true;
        }
        if (searchType.contains("Integer")) {
            return true;
        }
        if (searchType.contains("Float")) {
            return true;
        }
        if (searchType.contains("Double")) {
            return true;
        }
        if (searchType.contains("Boolean")) {
            return true;
        }
        if (searchType.contains("String")) {
            return true;
        }
        if (this.types != null && !this.types.isEmpty()) {
            for (AstType type : this.types) {
                if (searchType.contains(type.name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void generateTypesFromUnits() {

        ArrayList<AstType> astTypes = new ArrayList<AstType>();
        if (successBuild) {
            for (CompilationUnitDeclaration unit : this.units) {
                String packegeName = "";
                for (char[] pac : unit.currentPackage.tokens) {
                    if (!packegeName.isEmpty()) {
                        packegeName += ".";
                    }
                    packegeName += String.valueOf(pac);
                }
                if (unit.types != null) {
                    for (TypeDeclaration type : unit.types) {
                        AstType astType = new AstType();
                        astType.name = String.valueOf(type.name);
                        astType.packageName = packegeName;

                        astType.attributes = new ArrayList<AstAttribute>();
                        if (type.fields != null) {
                            for (FieldDeclaration field : type.fields) {
                                boolean add = true;
                                AstAttribute atr = new AstAttribute();
                                atr.name = String.valueOf(field.name);
                                atr.type = String.valueOf(field.type);
                                if (field.modifiers == 1) {
                                    atr.modificator = AstAttribute.AtrModificator.PUBLIC;
                                }
                                if (field.modifiers == 2 || field.modifiers == 4) {
                                    boolean get = false;
                                    boolean set = false;
                                    if (type.methods != null) {
                                        for (AbstractMethodDeclaration method : type.methods) {
                                            if (String.valueOf(method.selector).contains("get" + atr.name.substring(0, 1).toUpperCase() + atr.name.substring(1))) {
                                                get = true;
                                            }
                                            if (String.valueOf(method.selector).contains("set" + atr.name.substring(0, 1).toUpperCase() + atr.name.substring(1))) {
                                                set = true;
                                            }
                                        }
                                    }
                                    if (get && set) {
                                        atr.modificator = AstAttribute.AtrModificator.PRIVATEGETSET;
                                    } else {
                                        if (get && !set) {
                                            atr.modificator = AstAttribute.AtrModificator.PRIVATEGET;
                                        } else if (!get && set) {
                                            atr.modificator = AstAttribute.AtrModificator.PRIVATESET;
                                        } else {
                                            add = false;
                                        }
                                    }
                                }
                                if (add) {
                                    astType.attributes.add(atr);
                                }
                            }
                        }
                        if (type.annotations != null && type.annotations.length > 0) {
                            for (Annotation annotation : type.annotations) {
                                if (String.valueOf(annotation.type).contains("ObjectType")) {
                                    astTypes.add(astType);
                                }
                            }

                        }
                    }

                }
            }
        }
        types.addAll(astTypes);
    }

    public void generateMethodsFromUnits() {
        ArrayList<AstMethod> astMethods = new ArrayList<AstMethod>();
        if (successBuild) {
            for (CompilationUnitDeclaration unit : this.units) {
                if (unit.types != null) {
                    for (TypeDeclaration type : unit.types) {
                        if (type.methods != null) {
                            for (AbstractMethodDeclaration method : type.methods) {
                                if (!method.scope.isStatic) {
                                    continue;
                                }
                                AstMethod astMethod = new AstMethod();
                                if (method.binding.returnType.debugName().contains(".")) {
                                    String[] temp_name = String.valueOf(method.binding.returnType.debugName()).split("[.]+");
                                    if (temp_name.length > 0) {
                                        astMethod.type = temp_name[temp_name.length - 1];
                                    }
                                } else {
                                    astMethod.type = method.binding.returnType.debugName();
                                }
                                if (checkTypeInAllowed(astMethod.type)) {
                                    astMethod.name = String.valueOf(method.selector);
                                    if (method.modifiers == 1 || method.modifiers == 9) {
                                        astMethod.modificator = AstMethod.MethodModificator.PUBLIC;
                                    } else if (method.modifiers == 4 || method.modifiers == 12) {
                                        astMethod.modificator = AstMethod.MethodModificator.PROTECTED;
                                    } else if (method.modifiers == 2 || method.modifiers == 10) {
                                        astMethod.modificator = AstMethod.MethodModificator.PRIVATE;
                                    }
                                    boolean allAllowed = true;
                                    if (method.arguments != null) {
                                        for (Argument argument : method.arguments) {
                                            AstAttribute atr = new AstAttribute();
                                            atr.name = String.valueOf(argument.name);
                                            atr.type = String.valueOf(argument.type);
                                            if (!checkTypeInAllowed(atr.type)) {
                                                allAllowed = false;
                                                break;
                                            }
                                            atr.modificator = AstAttribute.AtrModificator.NONE;
                                            astMethod.attributes.add(atr);
                                        }
                                    }
                                    if (allAllowed) {

//public 1   protected 4  private 2
                                        if (method.annotations != null && method.annotations.length > 0) {
                                            for (Annotation annotation : method.annotations) {
                                                if (String.valueOf(annotation.type).contains("StaticMethod")) {
                                                    astMethods.add(astMethod);
                                                }
                                            }

                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        methods.addAll(astMethods);
    }

    public Boolean generateTree() throws Throwable {
        this.units = AstExtractor.createAST(this.filePath);
        this.successBuild = true;
        return true;
    }

}
