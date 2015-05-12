/* 
 */
package org.vap.filetypesupport;

import org.netbeans.spi.navigator.NavigatorLookupHint;

/**
 *
 * @author Oleg Bantysh
 */
public class VisualAkkaUnitNavigatorLookupHint implements NavigatorLookupHint {

    /**
     *
     * @return
     */
    @Override
    public String getContentType() {
        return "text/x-vau";
    }
    
}
