/* 
 */
package org.vap.deployer;

import org.openide.util.lookup.ServiceProvider;
import org.vap.core.deployer.AbstractDeployer;

/**
 *
 * @author Sergij
 */
@ServiceProvider(service = AbstractDeployer.class)
public class AkkaMainDeployer implements AbstractDeployer {

    @Override
    public void run(String mainClass) {
        AkkaOperate op = new AkkaOperate(false, true);
        Thread OpTh = new Thread(op);
        OpTh.start();
    }

    @Override
    public void compile(String path) {
        AkkaOperate op = new AkkaOperate(true, false);
        Thread OpTh = new Thread(op);
        OpTh.start();
    }

    @Override
    public void complileAndRun(String path, String mainClass) {
        AkkaOperate op = new AkkaOperate(true, true, path, mainClass);
        Thread OpTh = new Thread(op);
        OpTh.start();
    }

}
