/* 
 */
package org.vap.filetypesupport;

import java.io.IOException;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import org.netbeans.core.spi.multiview.CloseOperationState;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.MultiViewElementCallback;
import org.openide.awt.UndoRedo;
import org.openide.util.Lookup;

/**
 *
 * @author Oleg Bantysh
 */
public final class VisualAkkaUnitSourceEditor extends JPanel implements MultiViewElement {

    private VisualAkkaUnitDataObject obj;
    private JToolBar toolbar = new JToolBar();
    private JEditorPane editor = new JEditorPane();
    private transient MultiViewElementCallback callback;

    /**
     *
     * @param lkp
     */
    public VisualAkkaUnitSourceEditor(Lookup lkp) {
        obj = lkp.lookup(VisualAkkaUnitDataObject.class);
        assert obj != null;
        try{
            editor.setText(obj.getPrimaryFile().asText());
        }catch(Exception e){
            e.printStackTrace();
        }
        this.add(editor);
    }

    @Override
    public String getName() {
        return "VisualAkkaUnitVisualElement";
    }
    
    /**
     *
     * @return
     */
    @Override
    public JComponent getVisualRepresentation() {
        return this;
    }

    /**
     *
     * @return
     */
    @Override
    public JComponent getToolbarRepresentation() {
        return toolbar;
    }

    /**
     *
     * @return
     */
    @Override
    public Action[] getActions() {
        return new Action[0];
    }

    /**
     *
     * @return
     */
    @Override
    public Lookup getLookup() {
        return obj.getLookup();
    }

    /**
     *
     */
    @Override
    public void componentOpened() {
    }

    /**
     *
     */
    @Override
    public void componentClosed() {
    }

    /**
     *
     */
    @Override
    public void componentShowing() {
    }

    /**
     *
     */
    @Override
    public void componentHidden() {
    }

    /**
     *
     */
    @Override
    public void componentActivated() {
    }

    /**
     *
     */
    @Override
    public void componentDeactivated() {
    }

    /**
     *
     * @return
     */
    @Override
    public UndoRedo getUndoRedo() {
        return UndoRedo.NONE;
    }

    /**
     *
     * @param callback
     */
    @Override
    public void setMultiViewCallback(MultiViewElementCallback callback) {
        this.callback = callback;
    }

    /**
     *
     * @return
     */
    @Override
    public CloseOperationState canCloseElement() {
        return CloseOperationState.STATE_OK;
    }

}
