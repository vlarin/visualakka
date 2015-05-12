/* 
 */
package org.vap.core.deployer;

/**
 *
 * @author Oleg Bantysh
 */
public interface AbstractDeployer {

    /**
     *
     * @param mainClass
     */
    public void run(String mainClass);

    /**
     *
     * @param path
     */
    public void compile(String path);

    /**
     *
     * @param path
     * @param mainClass
     */
    public void complileAndRun(String path, String mainClass);
}
