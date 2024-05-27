
package com.uncuyo.dbapp.control;

import com.uncuyo.dbapp.dao.RoleDAO;

/**
 *
 * @author tomas
 */
public class RoleController {
    private RoleDAO roledao = new RoleDAO();

    public RoleController() {
    }
    
    public boolean login(String name, String password) {
        return roledao.login(name, password);
    }
}
