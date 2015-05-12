/* 
 */
package org.vap.core.model.macro;

/**
 *
 * @author Oleg Bantysh
 */
public class Connection {
    
    /**
     *
     */
    public String sourceCMID;

    /**
     *
     */
    public String sourcePinName;

    /**
     *
     */
    public String targetCMID;

    /**
     *
     */
    public String targetPinName;

    /**
     *
     */
    public State relatedState = null;

    /**
     *
     */
    public String extractedFieldName = null;
    
    @Override
    public String toString(){
        return sourceCMID + ";" + sourcePinName + ";" + targetCMID + ";" + targetPinName;
    }
    
    /**
     *
     * @return
     */
    public String getSourceSignature(){
        return sourceCMID + ";" + sourcePinName + ";1";
    }
    
    /**
     *
     * @return
     */
    public String getSourcePinID(){
        return sourcePinName.replace(' ', '_');
    }
    
    /**
     *
     * @return
     */
    public String getTargetSignature(){
        return targetCMID + ";" + targetPinName + ";0";
    }
    
    /**
     *
     * @return
     */
    public String getTargetPinID(){
        return targetPinName.replace(' ', '_');
    }
    
}
