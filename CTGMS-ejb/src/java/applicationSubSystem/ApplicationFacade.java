/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationSubSystem;

import java.util.Date;
import javax.ejb.Stateless;
import userSubsystem.Requester;
import userSubsystem.Supervisor;

/**
 *
 * @author user1
 */
@Stateless
public class ApplicationFacade implements ApplicationFacadeLocal {

    @Override
    public Conference findConference(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Conference createConference(Date startDate, Date endDate, String name, String website) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ExpenseEntry createEntry(ExpensePolicy expensePolicy, int amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ExpensePolicy findPolicy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GrantApplication createGrantApplication(String title, String status, Conference conference, ExpenseEntry[] expenses, String description, Requester requester) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getTotal(GrantApplication grantApplication) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SupervisorRecommendation createSupervisorRecommendation(boolean isApproved, boolean isSigned, String requestedChanges, Supervisor supervisor, GrantApplication application) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
