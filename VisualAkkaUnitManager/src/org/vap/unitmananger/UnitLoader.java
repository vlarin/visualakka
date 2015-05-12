/* 
 */
package org.vap.unitmananger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import org.openide.filesystems.FileAlreadyLockedException;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.vap.core.model.macro.Workspace;
import org.vap.core.model.micro.Module;

/**
 *
 * @author Oleg Bantysh
 */
public class UnitLoader {

    /**
     *
     * @param fo
     * @return
     */
    public static Module loadModule(FileObject fo) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Module.class);

            Unmarshaller unmarshaller = jc.createUnmarshaller();
            JAXBElement<Module> root = unmarshaller.unmarshal(new StreamSource(fo.getInputStream()), Module.class);
            return root.getValue();
        } catch (JAXBException ex) {
            Exceptions.printStackTrace(ex);
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

    /**
     *
     * @param fotw
     * @param m
     * @throws JAXBException
     */
    public static void uploadModule(FileObject fotw, Module m) throws JAXBException{
        JAXBContext jc = JAXBContext.newInstance(Module.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        try {
            FileLock lock;
            try {
                lock = fotw.lock();
            } catch (FileAlreadyLockedException e) {
                System.out.println("_____________LOCKED");
                return;
            }
            try {
                OutputStream to = fotw.getOutputStream(lock);
                try {
                    marshaller.marshal(new JAXBElement<Module>(new QName("uri", "local"),
                            Module.class, m), to);
                } finally {
                    to.close();
                }
            } finally {
                lock.releaseLock();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
