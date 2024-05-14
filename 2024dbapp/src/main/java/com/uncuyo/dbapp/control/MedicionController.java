
package com.uncuyo.dbapp.control;
import com.uncuyo.dbapp.dao.MedicionDAO;
import com.uncuyo.dbapp.dao.MedicionDAOImp;
import com.uncuyo.dbapp.model.Medicion;

/**
 *
 * @author tomas
 */
public class MedicionController {
    private MedicionDAO mediciondao;        

    public void insertarMedicion(Medicion medicion){               
        mediciondao.insertarMedicion(medicion);
    }
    
    public MedicionController() {
        mediciondao = new MedicionDAOImp();
    }
}
