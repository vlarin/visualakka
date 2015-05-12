/* 
 */
package org.vap.core.jaxbadapters;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map.Entry;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Oleg Bantysh
 */
public class ConcMapAdapter extends XmlAdapter<PropertyMapType,ConcurrentHashMap<String, String>> {
  
   @Override
   public PropertyMapType marshal(ConcurrentHashMap<String, String> arg0) throws Exception {
      PropertyMapType myMapType = new PropertyMapType();
      for(Entry<String, String> entry : arg0.entrySet()) {
         PropertyMapEntryType myMapEntryType = 
            new PropertyMapEntryType();
         myMapEntryType.key = entry.getKey();
         myMapEntryType.value = entry.getValue();
         myMapType.entry.add(myMapEntryType);
      }
      return myMapType;
   }
  
   @Override
   public ConcurrentHashMap<String, String> unmarshal(PropertyMapType arg0) throws Exception {
      ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap<String, String>();
      for(PropertyMapEntryType myEntryType : arg0.entry) {
         hashMap.put(myEntryType.key, myEntryType.value);
      }
      return hashMap;
   }
    
    
}


