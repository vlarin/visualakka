/* 
 */
package org.vap.core.model.macro;

/**
 *
 * @author Oleg Bantysh
 */
public class State {

    private String name;

    /**
     *
     */
    public State() {
        name = null;
    }

    /**
     *
     * @param name
     */
    public State(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof State) {
            return ((State) obj).getName().equals(getName());
        }
        return false;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
