/* 
 */
package org.vap.deployer;

import java.io.OutputStream;
import java.io.CharArrayWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.awt.TextArea;
import javax.swing.JTextArea;

/**
 *
 * @author Sergij
 */
public class AkkaOutputStream extends OutputStream {

    private JTextArea textArea;
    private int maxLines;
    private LinkedList lineLengths;
    private int curLength;
    private byte[] oneByte;

    public AkkaOutputStream(JTextArea area, int max) {
        textArea = area;
        maxLines = max;
        lineLengths = new LinkedList();
        curLength = 0;
        oneByte = new byte[1];
    }

    public synchronized void clear() {
        lineLengths = new LinkedList();
        curLength = 0;
        textArea.setText("");
    }

    public synchronized int getMaximumLines() {
        return maxLines;
    }

    public synchronized void setMaximumLines(int val) {
        maxLines = val;
    }

    @Override
    public void close() {
        if (textArea != null) {
            textArea = null;
            lineLengths = null;
            oneByte = null;
        }
    }

    @Override
    public void flush() {
    }

    @Override
    public void write(int val) {
        oneByte[0] = (byte) val;
        write(oneByte, 0, 1);
    }

    @Override
    public void write(byte[] ba) {
        write(ba, 0, ba.length);
    }

    @Override
    public synchronized void write(byte[] ba, int str, int len) {
        try {
            curLength += len;
            if (bytesEndWith(ba, str, len, LINE_SEP)) {
                lineLengths.addLast(new Integer(curLength));
                curLength = 0;
                if (lineLengths.size() > maxLines) {
                    textArea.replaceRange(null, 0, ((Integer) lineLengths.removeFirst()).intValue());
                }
            }
            for (int xa = 0; xa < 10; xa++) {
                try {
                    textArea.append(new String(ba, str, len));
                    break;
                } catch (Throwable thr) {                                                 // sometimes throws a java.lang.Error: Interrupted attempt to aquire write lock
                    if (xa == 9) {
                        thr.printStackTrace();
                    } else {
                        Thread.sleep(200);
                    }
                }
            }
        } catch (Throwable thr) {
            CharArrayWriter caw = new CharArrayWriter();
            thr.printStackTrace(new PrintWriter(caw, true));
            textArea.append(System.getProperty("line.separator", "\n"));
            textArea.append(caw.toString());
        }
    }

    private boolean bytesEndWith(byte[] ba, int str, int len, byte[] ew) {
        if (len < LINE_SEP.length) {
            return false;
        }
        for (int xa = 0, xb = (str + len - LINE_SEP.length); xa < LINE_SEP.length; xa++, xb++) {
            if (LINE_SEP[xa] != ba[xb]) {
                return false;
            }
        }
        return true;
    }
    static private byte[] LINE_SEP = System.getProperty("line.separator", "\n").getBytes();
}
