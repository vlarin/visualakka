/* 
 */
package org.vap.workspace.palettesupport;

import java.awt.datatransfer.Transferable;
import java.io.IOException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;
import org.vap.core.model.macro.ConcreticisedMethod;
import org.vap.core.model.macro.State;
import org.vap.core.model.macro.StateSetter;

/**
 *
 * @author Oleg Bantysh
 */
public class StateNode extends AbstractNode{
    private State st = null;
    
    /**
     *
     * @param state
     */
    public StateNode(State state){
        super(Children.LEAF, Lookups.singleton(state));
        this.st = state;
        this.setDisplayName(st.getName());
    }
    
    /**
     *
     * @return
     * @throws IOException
     */
    @Override
    public Transferable drag() throws IOException {
        return StateSetter.formSTS(st);
    }
    
}
