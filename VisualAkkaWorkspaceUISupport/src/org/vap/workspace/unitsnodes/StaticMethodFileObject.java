/* 
 */
package org.vap.workspace.unitsnodes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import org.openide.filesystems.FileChangeListener;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileStateInvalidException;
import org.openide.filesystems.FileSystem;

/**
 *
 * @author Oleg Bantysh
 */
public class StaticMethodFileObject extends FileObject {

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getExt() {
        return "";
    }

    @Override
    public void rename(FileLock fl, String string, String string1) throws IOException {
    }

    @Override
    public FileSystem getFileSystem() throws FileStateInvalidException {
        return null;
    }

    @Override
    public FileObject getParent() {
        return null;
    }

    @Override
    public boolean isFolder() {
        return false;
    }

    @Override
    public Date lastModified() {
        return null;
    }

    @Override
    public boolean isRoot() {
        return false;
    }

    @Override
    public boolean isData() {
        return true;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void delete(FileLock fl) throws IOException {
    }

    @Override
    public Object getAttribute(String string) {
        return null;
    }

    @Override
    public void setAttribute(String string, Object o) throws IOException {
    }

    @Override
    public Enumeration<String> getAttributes() {
        return null;
    }

    @Override
    public void addFileChangeListener(FileChangeListener fl) {
    }

    @Override
    public void removeFileChangeListener(FileChangeListener fl) {
    }

    @Override
    public long getSize() {
        return 0;
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        return null;
    }

    @Override
    public OutputStream getOutputStream(FileLock fl) throws IOException {
        return null;
    }

    @Override
    public FileLock lock() throws IOException {
        return null;
    }

    @Override
    public void setImportant(boolean bln) {
    }

    @Override
    public FileObject[] getChildren() {
        return null;
    }

    @Override
    public FileObject getFileObject(String string, String string1) {
        return null;
    }

    @Override
    public FileObject createFolder(String string) throws IOException {
        return null;
    }

    @Override
    public FileObject createData(String string, String string1) throws IOException {
        return null;
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }

}
