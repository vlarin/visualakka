/* 
 */
package org.vap.deployer;

import java.io.InputStream;
import java.io.OutputStream;
import org.openide.windows.OutputWriter;

class SyncPipe implements Runnable {

    public SyncPipe(InputStream istrm, OutputWriter ostrm) {
        istrm_ = istrm;
        ostrm_ = ostrm;
    }

    public void run() {
        try {
            final byte[] buffer = new byte[1024];
            for (int length = 0; (length = istrm_.read(buffer)) != -1;) {
                if (buffer == null) {
                    throw new NullPointerException();
                } else if ((length < 0)
                        || ((length) > buffer.length) || ((length) < 0)) {
                    throw new IndexOutOfBoundsException();
                } else if (length == 0) {
                    return;
                }
                for (int i = 0; i < length; i++) {
                    ostrm_.write(buffer[ i]);
                }
//                ostrm_.write(buffer, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private final OutputWriter ostrm_;
    private final InputStream istrm_;
}
