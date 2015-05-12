/* 
 */
package org.vap.workspace.widgets;

import org.netbeans.api.visual.widget.Widget;
import org.vap.core.model.micro.Method;
import org.vap.core.model.micro.Module;

/**
 *
 * @author Oleg Bantysh
 */
public interface CustomWidgetBuilder {
    public void build(Widget w, Module m, Method concreticisedMethod);
}
