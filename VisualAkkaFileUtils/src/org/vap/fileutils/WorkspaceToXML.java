/* 
 */
package org.vap.fileutils;

import java.io.IOException;
import java.io.OutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import org.openide.filesystems.FileAlreadyLockedException;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.vap.core.model.macro.ConcreticisedMethod;
import org.vap.core.model.macro.VFLayer;
import org.vap.core.model.macro.Workspace;
import org.vap.core.model.micro.Module;

/**
 *
 * @author Oleg Bantysh
 */
public class WorkspaceToXML {

    /**
     *
     * @param fotw
     * @param ws
     * @throws JAXBException
     */
    public static void parse(FileObject fotw, Workspace ws) throws JAXBException {
//        UpdateProperties(ws);
        JAXBContext jc = JAXBContext.newInstance(Workspace.class);
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
                    marshaller.marshal(new JAXBElement<Workspace>(new QName("uri", "local"),
                            Workspace.class, ws), to);
                } finally {
                    to.close();
                }
            } finally {
                lock.releaseLock();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //marshaller.marshal(ws, fotw.getOutputStream());

//        System.out.println("______________________________________");
//        Module m = ws.formModule();
//        JAXBContext jc1 = JAXBContext.newInstance(Module.class);
//        Marshaller marshaller1 = jc1.createMarshaller();
//        marshaller1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller1.marshal(m, System.out);
    }

//    private static void UpdateProperties(Workspace ws) {
//        for (VFLayer l : ws.getLayers()) {
//            for (ConcreticisedMethod m : l.units) {
//                m.UpdatePropertiesToSave();
//            }
//        }
//    }

}
