/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationSubSystem;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import userSubsystem.Requester;
import userSubsystem.RequesterTypeEnum;
import userSubsystem.Supervisor;

/**
 *
 * @author user1
 */
@Stateless
public class ApplicationFacade implements ApplicationFacadeLocal {

    @PersistenceContext(unitName = "CTGMS-ejbPU")
    private EntityManager em;

    /**
     * Takes the name of a conference and returns a reference to it.
     * 
     * @param name
     * @return 
     */
    @Override
    public Conference findConference(String name) {
        try {
            Query query = em.createQuery(
                    "SELECT c FROM Conference c"
                    + " WHERE c.name = :name");
            query.setParameter("name", name);
            List resultList = query.getResultList();
            return (Conference) resultList.get(0);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Conference createConference(Date startDate, Date endDate, String name, String website) {
        Conference newConference= new Conference(startDate, endDate, name, website);
        em.persist(newConference);
        return newConference;
    }

    @Override
    public ExpenseEntry createEntry(ExpensePolicy expensePolicy, int amount,GrantApplication grantApp) {
     ExpenseEntry expenseEntry= new ExpenseEntry(expensePolicy,grantApp, amount);
       em.persist(expenseEntry);
       return expenseEntry;
    }

    @Override
    public ExpensePolicy findPolicy(ExpenseTypeEnum expenseType) {
        try {
            Query query = em.createQuery(
                    "SELECT eP FROM ExpensePolicy eP"
                    + " WHERE eP.expenseType = :expenseType");
            query.setParameter("expenseType", expenseType);
            List resultList = query.getResultList();
            return (ExpensePolicy) resultList.get(0);
        } catch (Exception e) {
        }
        return null;
     }

    @Override
    public GrantApplication createGrantApplication(String title, Conference conference, LinkedList<ExpenseEntry> expenses, String description, Requester requester) {
    GrantApplication grantApp= new GrantApplication(title, conference, description,requester) ;
    em.persist(grantApp);
    return grantApp;
    }


    @Override
    public SupervisorRecommendation createSupervisorRecommendation(boolean isApproved, boolean isSigned, String requestedChanges, Supervisor supervisor) {
        SupervisorRecommendation superRec= new SupervisorRecommendation(isApproved,isSigned,requestedChanges, supervisor);
        em.persist(superRec);
        return superRec;
    }
    
    
    
     public GrantLimit findGrantLimit(RequesterTypeEnum requesterType){
         try {
            Query query = em.createQuery(
                    "SELECT gL FROM GrantLimit gL"
                    + " WHERE gL.requesterType = :requesterType");
            query.setParameter("requesterType", requesterType);
            List resultList = query.getResultList();
            return (GrantLimit) resultList.get(0);
        } catch (Exception e) {
        }
        return null;
     }

    public void persist(Object object) {
        em.persist(object);
    }

}
