/* 
 */
package org.vap.core.model.macro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.vap.core.model.macro.ConcreticisedMethod.CMType;

/**
 *
 * @author Oleg Bantysh
 */
public class VFLayer {

    //---------------TODO:PRIVACY!!!!!!-----------------------

    /**
     *
     */
        public String methodName;

    /**
     *
     */
    public ArrayList<ConcreticisedMethod> units = new ArrayList();

    /**
     *
     */
    public Entry mainEntry;

    /**
     *
     */
    public ArrayList<Entry> entries = new ArrayList();

    /**
     *
     */
    public ArrayList<Exit> exits = new ArrayList();

    /**
     *
     */
    public ArrayList<StateSetter> stSetters = new ArrayList();

    /**
     *
     */
    public HashMap<String, Connection> connections = new HashMap();

    ;
    //--------------------------------------------------------
    
    /**
     *
     * @param id
     * @return
     */
        
    public ConcreticisedMethod getUnitByID(String id) {
        for (ConcreticisedMethod m : units) {
            if (m.getCmID().contains(id) && id.contains(m.getCmID())) {
                return m;
            }
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public StateSetter getStSetByID(String id) {
        for (StateSetter m : stSetters) {
            if (m.getCmID().contains(id) && id.contains(m.getCmID())) {
                return m;
            }
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public Entry getEntryByName(String id) {
        for (Entry m : entries) {
            if (m.getCmID().contains(id) && id.contains(m.getCmID())) {
                return m;
            }
        }
        return null;
    }

    /**
     *
     * @param name
     * @return
     */
    public Exit getExitByName(String name) {
        for (Exit m : exits) {
            if (m.getCmID().contains(name) && name.contains(m.getCmID())) {
                return m;
            }
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public WorkspaceObject getObjectById(String id) {
        WorkspaceObject obj = getUnitByID(id);
        if (obj != null) {
            return obj;
        }
        obj = getEntryByName(id);
        if (obj != null) {
            return obj;
        }
        obj = getStSetByID(id);

        if (obj != null) {
            return obj;
        }
        
        return getExitByName(id);
    }

    /**
     *
     * @return
     */
    public Collection<Connection> getConnections() {
        return connections.values();
    }

    /**
     *
     * @param s
     * @return
     */
    public Connection getConnection(String s) {
        return connections.get(s);
    }

    /**
     *
     * @param CMID
     * @return
     */
    public Collection<Connection> getConnections(String CMID) {
        ArrayList<Connection> result = new ArrayList();
        for (Connection c : connections.values()) {
            if (c.sourceCMID.equals(CMID) || c.targetCMID.equals(CMID)) {
                result.add(c);
            }
        }
        return result;
    }

    /**
     *
     * @param c
     */
    public void addConnection(Connection c) {
        connections.put(c.toString(), c);
    }

    /**
     *
     * @param id
     */
    public void removeConnection(String id) {
        connections.remove(id);
    }

    /**
     *
     * @param id
     */
    public void removeUnitWithConnections(String id) {
        Entry en = getEntryByName(id);
        Exit ex = getExitByName(id);
        ConcreticisedMethod m = getUnitByID(id);
        StateSetter st = getStSetByID(id);
        if (m != null) {
            units.remove(m);
        } else if (ex != null) {
            exits.remove(ex);
        } else if (en != null) {
            entries.remove(en);
        } else if(st != null){
            stSetters.remove(st);
        }
        String[] listToDelete = connections.keySet().toArray(new String[connections.keySet().size()]);
        for (String s : listToDelete) {
            String[] indexes = s.split(";");
            if (indexes[0].equals(id) || indexes[2].equals(id)) {
                connections.remove(s);
            }
        }

    }
    
//    /**
//     *
//     */
//    public void fixObjectReference(){
//        for(int i =0; i < units.size(); i++){
//            if(units.get(i).type!=CMType.ConcreticisedMethod){
//                units.set(i, UserCodeBlock.toUCB(units.get(i)));
//            }
//        }
//    }

}
