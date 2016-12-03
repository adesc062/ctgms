/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationSubSystem;

import java.util.ArrayList;
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
    public Conference createConference(String name, String website, Date startDate, Date endDate) {
        Conference conference = new Conference(name, website, startDate, endDate);
        em.persist(conference);
        return conference;
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
    public GrantApplication createGrantApplication(String title, Conference conference, String description, Requester requester,
            double registrationAmount, double transportationAmount, double accomodationAmount, double mealsAmount) {
        GrantApplication grantApp = new GrantApplication(title, conference, description, requester);
        ExpenseEntry registrationExpenseEntry = new ExpenseEntry(grantApp, registrationAmount, ExpenseTypeEnum.Registration);
        ExpenseEntry transporationExpenseEntry = new ExpenseEntry(grantApp, transportationAmount, ExpenseTypeEnum.Transportation);
        ExpenseEntry accomodationExpenseEntry = new ExpenseEntry(grantApp, accomodationAmount, ExpenseTypeEnum.Accomodation);
        ExpenseEntry mealsExpenseEntry = new ExpenseEntry(grantApp, mealsAmount, ExpenseTypeEnum.Meals);
        em.persist(grantApp);
        //em.persist(registrationExpenseEntry);
        //em.persist(transporationExpenseEntry);
        //em.persist(accomodationExpenseEntry);
        //em.persist(mealsExpenseEntry);
        return grantApp;
    }


    @Override
    public GrantLimit findGrantLimit(RequesterTypeEnum requesterType) {
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

    @Override
    public ArrayList<GrantApplication> getListOfGrantApplicationsNeedingSupervisorApproval(Supervisor supervisor) {
        try {

            //Query query = em.createQuery(
            //        "SELECT gA FROM GrantApplication gA"
            //        + " JOIN Requester r ON gA.requester = r"
            //       + " WHERE r.supervisor = :supervisor");
            Query query = em.createQuery(
                    "SELECT gA FROM GrantApplication gA");
            //query.setParameter("supervisor", supervisor);
            List resultList = query.getResultList();
            // ArrayList<Requester> = supervisor.getR
            ArrayList<GrantApplication> grantApplications = new ArrayList<>();
            grantApplications.addAll(resultList);
            return grantApplications;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
     public void setStatus(GrantApplication grantApp, ApplicationStatusEnum status){
         grantApp.setStatus(status);
     }
     
     
     @Override
    public Requester getRequester(GrantApplication grantApp){
      return grantApp.getRequester();
    }
    
    @Override
    public SupervisorRecommendation createSupervisorRecommendation(Supervisor supervisor,String comments){
        SupervisorRecommendation superRec = new SupervisorRecommendation(comments,supervisor);
        return superRec;
    }
    
    @Override
    public void addSupervisorRecommendation(GrantApplication grantApp,SupervisorRecommendation superRec){
        grantApp.addSupervisorRecommendation(superRec);
    }

    
    public void persist(Object object) {
        em.persist(object);
    }

}
