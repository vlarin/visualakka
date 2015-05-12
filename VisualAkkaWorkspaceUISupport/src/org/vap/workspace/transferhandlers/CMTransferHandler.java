/* 
 */
package org.vap.workspace.transferhandlers;

import javax.swing.TransferHandler;
import org.openide.util.Exceptions;
import org.vap.core.model.macro.ConcreticisedMethod;

/**
 *
 * @author Oleg Bantysh
 */
public class CMTransferHandler extends TransferHandler{
    
    @Override
      public boolean canImport(TransferSupport support) {
         return support.isDataFlavorSupported(ConcreticisedMethod.DATA_FLAVOR);
      }

      @Override
      public boolean importData(TransferSupport support) {
         try {
            ConcreticisedMethod a = (ConcreticisedMethod)support.getTransferable().
                    getTransferData(ConcreticisedMethod.DATA_FLAVOR);
            return true;
         } catch(Exception e) {
            Exceptions.printStackTrace(e);
            return false;
         }
      }
    
}
