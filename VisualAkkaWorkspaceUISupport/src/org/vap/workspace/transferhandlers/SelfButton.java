/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vap.workspace.transferhandlers;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.TransferHandler;
import static org.vap.core.model.macro.ConcreticisedMethod.DATA_FLAVOR;

/**
 *
 * @author Oleg Bantysh
 */
public class SelfButton extends JButton implements Transferable, DragSourceListener, DragGestureListener {

    private DragSource source;

    private TransferHandler t;

    /**
     *
     */
    public SelfButton() {
    }

    /**
     *
     * @param icon
     */
    public SelfButton(Icon icon) {
        super(icon);
    }

    /**
     *
     * @param text
     */
    public SelfButton(String text) {
        super(text);
        t = new TransferHandler() {
            public Transferable createTransferable(JComponent c) {
                return new EntryButton(getText());
            }

        };
        setTransferHandler(t);
        source = new DragSource();
        source.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY, this);

    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        return new DataFlavor[]{DATA_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return true;
    }

    @Override
    public Object getTransferData(DataFlavor flavor) {
        return this;
    }

    @Override
    public void dragEnter(DragSourceDragEvent dsde) {
    }

    @Override
    public void dragOver(DragSourceDragEvent dsde) {
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {
    }

    @Override
    public void dragExit(DragSourceEvent dse) {
    }

    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) {
        repaint();
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        source.startDrag(dge, DragSource.DefaultMoveDrop, new EntryButton("Text"), this);
    }

}
