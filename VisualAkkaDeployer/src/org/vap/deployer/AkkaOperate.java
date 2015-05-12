/* 
 */
package org.vap.deployer;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

/**
 *
 * @author Sergij
 */
class AkkaOperate implements Runnable {

    protected String path = "E:\\Projects\\VisualFlowDevelopAkka\\testDeploy\\akka3\\akka-tutorial-first-java";

    protected String mainClassPath = "akka.tutorial.first.java.Pi";
    protected String mavenPath = "";

    protected OutputStream outStream = System.out;

    protected boolean _compile = false;
    protected boolean _run = false;

    public void setPath(String path) {
        this.path = path;
    }

    public void setMainClassPath(String mainClassPath) {
        this.mainClassPath = mainClassPath;
    }

    public AkkaOperate(boolean _compile, boolean _run) {
        this._compile = _compile;
        this._run = _run;
    }

    public AkkaOperate(boolean _compile, boolean _run, PrintStream outStream) {
        if (outStream != null) {
            this.outStream = outStream;
        }
        this._compile = _compile;
        this._run = _run;
    }

    public AkkaOperate(boolean _compile, boolean _run, String path, String mainClassPath) {
        this._compile = _compile;
        this._run = _run;
        this.path = path;
        this.mainClassPath = mainClassPath;

    }

    public AkkaOperate(boolean _compile, boolean _run, PrintStream outStream, String path, String mainClassPath) {
        if (outStream != null) {
            this.outStream = outStream;
        }
        this._compile = _compile;
        this._run = _run;
        this.path = path;
        this.mainClassPath = mainClassPath;
    }

    public void run() {
        try {

            this.mavenPath = System.getProperty("netbeans.home");
            this.mavenPath = mavenPath.replace("platform", "java\\maven\\bin\\mvn.bat");
//            Properties s=System.getProperties();
            String[] command
                    = {
                        "cmd",};
            Process p = Runtime.getRuntime().exec(command);
            InputOutput io = IOProvider.getDefault().getIO(mainClassPath, false);
            new Thread(new SyncPipe(p.getErrorStream(), io.getErr())).start();
            new Thread(new SyncPipe(p.getInputStream(), io.getOut())).start();
            PrintWriter stdin = new PrintWriter(p.getOutputStream());

            if (_compile) {
                this.compile(stdin);
            }
            if (_run) {
                this.run(stdin);
            }

            stdin.close();
            io.getOut().close();
            io.getErr().close();
            int returnCode = p.waitFor();
//            System.out.println("Return code = " + returnCode);
        } catch (Exception e) {
            System.out.println(String.format("%s",
                    e.getMessage()));
        }
    }

    protected void run(PrintWriter stdin) {
        try {
            stdin.println("cd " + this.path + " ");
            stdin.println(this.mavenPath + " exec:java -Dexec.mainClass=\"" + this.mainClassPath + "\" ");
        } catch (Exception e) {
            System.out.println(String.format("%s",
                    e.getMessage()));
        }
    }

    protected void compile(PrintWriter stdin) {
        try {
            stdin.println("cd " + this.path + " ");
            stdin.println(this.mavenPath + " compile ");
        } catch (Exception e) {
            System.out.println(String.format("%s",
                    e.getMessage()));
        }
    }
}
