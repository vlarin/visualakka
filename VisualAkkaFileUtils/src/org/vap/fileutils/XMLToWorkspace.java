/* 
 */
package org.vap.fileutils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.vap.core.model.macro.Workspace;

/**
 *
 * @author Oleg Bantysh
 */
public class XMLToWorkspace {

    /**
     *
     * @param fo
     * @return
     */
    public static Workspace parse(FileObject fo) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Workspace.class);
            InputStream is = fo.getInputStream();
            try {
                Unmarshaller unmarshaller = jc.createUnmarshaller();
                JAXBElement<Workspace> root = unmarshaller.unmarshal(new StreamSource(is), Workspace.class);
                return root.getValue();
            } finally {
                try {
                    is.close();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
            
        } catch (JAXBException ex) {
            Exceptions.printStackTrace(ex);
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (Exception ex){
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

}
