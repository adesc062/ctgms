/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.ejb.Local;
import applicationSubSystem.GrantAppplication;

/**
 *
 * @author user1
 */
@Local
public interface ConferenceTravelGrantSystemLocal {
    
    public void createApplication(String title, String description, String status, String conference);
}
