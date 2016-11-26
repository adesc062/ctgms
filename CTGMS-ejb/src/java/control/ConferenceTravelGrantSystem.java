/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import applicationSubSystem.GrantApplication;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author user1
 */
@Stateful
public class ConferenceTravelGrantSystem implements ConferenceTravelGrantSystemLocal {

    @PersistenceContext(unitName = "CTGMS-ejbPU")
    private EntityManager em;

    @Override
    public void createApplication(String title, String description, String status, String conference) {}
    /*GrantApplication app= new GrantApplication();
    GrantApplication
   app.setup(title, status, conference, 11, description,ApplicationStatusEnum );
        
        try {
            em.persist(app);
        } catch (Exception e) {
            //return false ;
        }
       // return true;    
    }*/

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
/*
    public void persist(Object object) {
        em.persist(object);
    }*/
    
}
