/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationSubSystem;

import java.util.Date;
import javax.ejb.Local;
import userSubsystem.Requester;
import userSubsystem.Supervisor;

/**
 *
 * @author user1
 */
@Local
public interface ApplicationFacadeLocal {
    public Conference findConference(String name);
    public Conference createConference(Date startDate, Date endDate, String name, String website);
    public ExpenseEntry createEntry(ExpensePolicy expensePolicy, int amount);
    public ExpensePolicy findPolicy();
    public GrantApplication createGrantApplication(String title, String status, Conference conference, ExpenseEntry[] expenses, String description, Requester requester);
    public int getTotal(GrantApplication grantApplication);
    public SupervisorRecommendation createSupervisorRecommendation(boolean isApproved, boolean isSigned, String requestedChanges, Supervisor supervisor, GrantApplication application);
}
