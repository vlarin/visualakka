/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vap.objwindsupport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.openide.windows.TopComponent;

/**
 *
 * @author Oleg Bantysh
 */
public class ObjectTopComponent extends TopComponent {

    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.TYPE, ElementType.METHOD})
    public static @interface OpenActionForObjectRegistration {

        String displayName();

        String preferredID() default "";
    }
}
