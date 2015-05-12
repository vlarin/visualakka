/* 
 */
package org.vap.workspace.propertynodes;

import java.lang.reflect.InvocationTargetException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.vap.core.model.macro.Connection;
import org.vap.core.model.macro.Entry;
import org.vap.core.model.macro.Exit;
import org.vap.workspace.WorkspaceScene;

/**
 *
 * @author Oleg Bantysh
 */
public class exitNode extends AbstractNode {

    public Exit e;
    private WorkspaceScene ws;

    public exitNode(Exit e, WorkspaceScene ws) {
        super(Children.LEAF);
        this.e = e;
        this.setDisplayName("Exit");
        this.ws = ws;
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Sheet.Set set = Sheet.createPropertiesSet();
        set.put(new NameProperty());
        set.put(new TypeProperty());
        sheet.put(set);
        return sheet;
    }

    public class NameProperty extends PropertySupport.ReadWrite {

        public NameProperty() {
            super("Name", String.class, "Name", "Name");
        }

        @Override
        public Object getValue() throws IllegalAccessException, InvocationTargetException {
            return e.getRefRes().getName();
        }

        @Override
        public void setValue(Object t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            if (!e.getRefRes().getName().equals((String) t)) {
                for (Connection c : ws.ws.getActiveLayer().getConnections(e.getCmID())) {
                    ws.ws.getActiveLayer().connections.remove(c.toString());
                    Connection c1 = c;
                    c1.targetPinName = (String) t;
                    ws.ws.getActiveLayer().connections.put(c1.toString(), c1);
                }
                e.getRefRes().setName((String) t);
                ws.removeAll();
                ws.initWorkspace();
                ws.validate();
            }
            e.getRefRes().setName((String) t);
        }

    }
    
    public class TypeProperty extends PropertySupport.ReadWrite {

        public TypeProperty() {
            super("Type", String.class, "Type", "Type");
        }

        @Override
        public Object getValue() throws IllegalAccessException, InvocationTargetException {
            return e.getRefRes().getType();
        }

        @Override
        public void setValue(Object t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            if (!e.getRefRes().getType().equals((String) t)) {
                e.getRefRes().setType((String) t);
                ws.removeAll();
                ws.initWorkspace();
                ws.validate();
            }
            e.getRefRes().setType((String) t);
        }

    }

}
