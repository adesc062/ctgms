/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationSubSystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import javax.ejb.Local;
import userSubsystem.Requester;
import userSubsystem.RequesterTypeEnum;
import userSubsystem.Supervisor;

/**
 *
 * @author user1
 */
@Local
public interface ApplicationFacadeLocal {
    public Conference findConference(String name);
    public Conference createConference(Date startDate, Date endDate, String name, String website);
    public ExpenseEntry createEntry(ExpensePolicy expensePolicy, int amount,GrantApplication grantApp);
    public ExpensePolicy findPolicy(ExpenseTypeEnum expenseType);
    public SupervisorRecommendation createSupervisorRecommendation(boolean isApproved, boolean isSigned, String requestedChanges, Supervisor supervisor) ;
    public GrantApplication createGrantApplication(String title, Conference conference, LinkedList<ExpenseEntry> expenses, String description, Requester requester);
    public GrantLimit findGrantLimit(RequesterTypeEnum requesterType);
    public ArrayList<GrantApplication> getListOfGrantApplicationsNeedingSupervisorApproval(Supervisor supervisor);
}
