/* 
 */
package org.vap.deployer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 *
 * @author Sergij
 */
public class AkkaRuntimeClass {

    public void operate(boolean _compile, boolean _run) {
        AkkaOperate op = new AkkaOperate(_compile, _run);
        op.run();
    }

    public void operate(boolean _compile, boolean _run, PrintStream outStream) {
        AkkaOperate op = new AkkaOperate(_compile, _run, outStream);
        Thread OpTh = new Thread(op);
        OpTh.start();
    }

}
