/* 
 */
package org.vap.core.jaxbadapters;

import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Oleg Bantysh
 */
public class PropertyMapEntryType {
 
    /**
     *
     */
    @XmlAttribute
   public String key; 
 
    /**
     *
     */
    @XmlValue
   public String value;
 
}
