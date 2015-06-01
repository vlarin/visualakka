/* 
 */
package org.vap.workspace.propertynodes;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyEditor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import org.openide.explorer.propertysheet.InplaceEditor;
import org.openide.explorer.propertysheet.PropertyEnv;
import org.openide.explorer.propertysheet.PropertyModel;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.vap.core.codegen.AbstractCodeParser;
import org.vap.core.model.macro.Connection;
import org.vap.core.model.macro.Entry;
import org.vap.workspace.WorkspaceScene;

/**
 *
 * @author Oleg Bantysh
 */
public class entryNode extends AbstractNode {

    private final static String STRING_STROKE = "java.lang.String";
    private final static String INT_STROKE = "java.lang.Integer";
    private final static String FLOAT_STROKE = "java.lang.Float";
    private final static String DOUBLE_STROKE = "java.lang.Double";
    private final static String BOOLEAN_STROKE = "java.lang.Boolean";

    public Entry e;
    WorkspaceScene ws;

    ArrayList<String> availableTypes = new ArrayList<String>();

    public entryNode(Entry e, WorkspaceScene ws) {
        super(Children.LEAF);
        this.e = e;
        this.setDisplayName("Entry");
        this.ws = ws;
        if (!availableTypes.contains(STRING_STROKE)) {
            availableTypes.add(STRING_STROKE);
        }
        if (!availableTypes.contains(INT_STROKE)) {
            availableTypes.add(INT_STROKE);
        }
        if (!availableTypes.contains(FLOAT_STROKE)) {
            availableTypes.add(FLOAT_STROKE);
        }
        if (!availableTypes.contains(DOUBLE_STROKE)) {
            availableTypes.add(DOUBLE_STROKE);
        }
        if (!availableTypes.contains(BOOLEAN_STROKE)) {
            availableTypes.add(BOOLEAN_STROKE);
        }

    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Sheet.Set set = Sheet.createPropertiesSet();
        set.put(new NameProperty());
        //set.put(new TypeProperty());
        Property type = new PropertySupport.ReadWrite<String>("Тype", String.class, "Тype", "Тype") {

            @Override
            public String getValue() throws IllegalAccessException, InvocationTargetException {
                return e.getRefArg().getType();
            }

            @Override
            public void setValue(String val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//                if(availableTypes.contains(val)){
//                    if (!e.getRefArg().getType().equals(val)) {
//                    e.getRefArg().setType(val);
//                    ws.load();
//                }
//                e.getRefArg().setType(val);
//                }else{                   
//                }
                e.getRefArg().setType(val);
                ws.load();
            }

            @Override
            public Object getValue(String attributeName) {
                if (attributeName.equals("inplaceEditor")) {
                    try{
                        AbstractCodeParser gen = (AbstractCodeParser) Lookup.getDefault().lookup(AbstractCodeParser.class);
                        availableTypes = gen.getTypeNames();
                    }catch(Exception e){
                        Exceptions.printStackTrace(e);
                    }                  
                    if (!availableTypes.contains(STRING_STROKE)) {
                        availableTypes.add(STRING_STROKE);
                    }
                    if (!availableTypes.contains(INT_STROKE)) {
                        availableTypes.add(INT_STROKE);
                    }
                    if (!availableTypes.contains(FLOAT_STROKE)) {
                        availableTypes.add(FLOAT_STROKE);
                    }
                    if (!availableTypes.contains(DOUBLE_STROKE)) {
                        availableTypes.add(DOUBLE_STROKE);
                    }
                    if (!availableTypes.contains(BOOLEAN_STROKE)) {
                        availableTypes.add(BOOLEAN_STROKE);
                    }
                    ComboBoxInplaceEditor comboEditor = new ComboBoxInplaceEditor();
                    DefaultComboBoxModel model = new DefaultComboBoxModel(availableTypes.toArray());
                    model.setSelectedItem(e.getRefArg().getType());
                    comboEditor.setModel(model);
                    comboEditor.addItemListener(new ItemListener() {

                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            try {
                                setValue((String) e.getItem());
                            } catch (IllegalAccessException ex) {
                                Exceptions.printStackTrace(ex);
                            } catch (IllegalArgumentException ex) {
                                Exceptions.printStackTrace(ex);
                            } catch (InvocationTargetException ex) {
                                Exceptions.printStackTrace(ex);
                            }
                        }
                    });
                    return comboEditor;
                }
                return null;
            }
        };
        set.put(type);
        set.put(new IsMainArgProperty());
        if (!e.getRefArg().isIsMainArg()) {
            set.put(new IsFixedProperty());
            set.put(new DefaultValueProperty());
        }
        
        sheet.put(set);
        return sheet;
    }

    public class NameProperty extends PropertySupport.ReadWrite {

        public NameProperty() {
            super("Name", String.class, "Name", "Name");
        }

        @Override
        public Object getValue() throws IllegalAccessException, InvocationTargetException {
            return e.getRefArg().getName();
        }

        @Override
        public void setValue(Object t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

            if (e.getRefArg().isIsMainArg() && !e.getRefArg().getName().equals((String) t)) {
                ws.ws.getActiveLayer().methodName = (String) t;
                //Figure this
                JComboBox combo = (JComboBox) ws.getToolbar().getComponent(2);
                combo.addItem((String) t);
                combo.removeItem(e.getRefArg().getName());
                combo.setSelectedItem((String) t);

            }
            for (Connection c : ws.ws.getActiveLayer().getConnections(e.getCmID())) {
                ws.ws.getActiveLayer().connections.remove(c.toString());
                Connection c1 = c;
                c1.sourcePinName = (String) t;
                ws.ws.getActiveLayer().connections.put(c1.toString(), c1);
            }
            e.getRefArg().setName((String) t);
            ws.removeAll();
            ws.initWorkspace();
            ws.validate();
        }

    }

    public class TypeProperty extends PropertySupport.ReadWrite {

        public TypeProperty() {
            super("Type", String.class, "Type", "Type");
        }

        @Override
        public Object getValue() throws IllegalAccessException, InvocationTargetException {
            return e.getRefArg().getType();
        }

        @Override
        public void setValue(Object t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            if (!e.getRefArg().getType().equals((String) t)) {
                e.getRefArg().setType((String) t);
                ws.removeAll();
                ws.initWorkspace();
                ws.validate();
            }
            e.getRefArg().setType((String) t);
        }

    }

    public class IsMainArgProperty extends PropertySupport.ReadOnly {

        public IsMainArgProperty() {
            super("IsMainArg", boolean.class, "IsMainArg", "IsMainArg");
        }

        @Override
        public Object getValue() throws IllegalAccessException, InvocationTargetException {
            return e.getRefArg().isIsMainArg();
        }

    }

    public class IsFixedProperty extends PropertySupport.ReadWrite {

        public IsFixedProperty() {
            super("IsFixed", boolean.class, "IsFixed", "IsFixed");
        }

        @Override
        public Object getValue() throws IllegalAccessException, InvocationTargetException {
            return e.getRefArg().isFixed();
        }

        @Override
        public void setValue(Object t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            e.getRefArg().setFixed((Boolean) t);
        }

    }

    public class DefaultValueProperty extends PropertySupport.ReadWrite {

        public DefaultValueProperty() {
            super("DefaultValue", String.class, "DefaultValue", "DefaultValue");
        }

        @Override
        public Object getValue() throws IllegalAccessException, InvocationTargetException {
            return e.getRefArg().getDefaultValue();
        }

        @Override
        public void setValue(Object t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            e.getRefArg().setDefaultValue((String) t);
        }

    }

    //-----------------------------------------------
    public class ComboBoxInplaceEditor extends JComboBox implements InplaceEditor {

        private PropertyEditor propEditor = null;

        public ComboBoxInplaceEditor() {
            this.setModel(new DefaultComboBoxModel());
        }

        @Override
        public void connect(PropertyEditor propertyEditor, PropertyEnv env) {
            propEditor = propertyEditor;
            reset();
        }

        @Override
        public JComponent getComponent() {
            return this;
        }

        @Override
        public void clear() {
            //avoid memory leaks:
            propEditor = null;
            model = null;
        }

        @Override
        public Object getValue() {
            return this.getSelectedItem();
        }

        @Override
        public void setValue(Object object) {
            this.setSelectedItem(object);
        }

        @Override
        public boolean supportsTextEntry() {
            return true;
        }

        @Override
        public void reset() {
            Object obj = (Object) propEditor.getValue();
            if (obj != null) {
                this.setValue(obj);
            }
        }

        @Override
        public KeyStroke[] getKeyStrokes() {
            return new KeyStroke[0];
        }

        @Override
        public PropertyEditor getPropertyEditor() {
            return propEditor;
        }

        @Override
        public PropertyModel getPropertyModel() {
            return model;
        }
        private PropertyModel model;

        @Override
        public void setPropertyModel(PropertyModel propertyModel) {
            this.model = propertyModel;
        }

        @Override
        public boolean isKnownComponent(Component component) {
            return component == this || this.isAncestorOf(component);
        }

        @Override
        public void addActionListener(ActionListener actionListener) {
            //do nothing - not needed for this component
        }

        @Override
        public void removeActionListener(ActionListener actionListener) {
            //do nothing - not needed for this component
        }
    }

}
