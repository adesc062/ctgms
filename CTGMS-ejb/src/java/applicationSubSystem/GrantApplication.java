/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationSubSystem;

import java.io.Serializable;
import java.util.LinkedList;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import userSubsystem.Requester;

/**
 *
 * @author ralle057
 */

 

@Entity
@Table(name="GrantApplications7972857")
public class GrantApplication implements Serializable {
private String title;
//ignoredType
 private Conference conference;
 private LinkedList<ExpenseEntry> expenses;
 private String description;
 private int total;
 
 @Enumerated(EnumType.ORDINAL)
 private ApplicationStatusEnum applicationStatus;
 private Requester requester;
 private SupervisorRecommendation supervisorRecommendation;
// private Expense 
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue()
    private Long id;

    public GrantApplication(){
        //for jpu
    }
    
    public GrantApplication(String title, Conference conference, String description, Requester requester) {
        this.setup(title, conference, description,requester);
    }
    
    

    public void setup(String title, Conference conference, String description, Requester requester) {
        this.title = title;
        this.conference = conference;
       // this.expenses = expenses; //this will be done through setter. See sequence diagram
        this.description = description;
        this.setRequester(requester);
        this.applicationStatus = applicationStatus.INCOMPLETE;
        //this.requester= requester;done later in sequence
        this.setExpenses(new LinkedList<ExpenseEntry>());
        this.setTotal(0);
    }


   
    

    public Long getId() {
           //"TEST";
        return id;
     
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrantApplication)) {
            return false;
        }
        GrantApplication other = (GrantApplication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Application[ id=" + id + " ]";
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

   
    /**
     * @return the conference
     */
    public Conference getConference() {
        return conference;
    }

    /**
     * @param conference the conference to set
     */
    public void setConference(Conference conference) {
        this.conference = conference;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the expenses
     */
    

    /**
     * @return the applicationStatus
     */
    public ApplicationStatusEnum getApplicationStatus() {
        return applicationStatus;
    }

    /**
     * @param applicationStatus the applicationStatus to set
     */
    public void setStatus(ApplicationStatusEnum applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
    
    //used Dependancy injection
    public void addToTotal(ExpenseEntry expenseEntry){
        getExpenses().add(expenseEntry);
        this.setTotal(this.getTotal() + expenseEntry.getExpenseAmount());
    }

    /**
     * @return the expenses
     */
    public LinkedList<ExpenseEntry> getExpenses() {
        return expenses;
    }

    /**
     * @param expenses the expenses to set
     */
    public void setExpenses(LinkedList<ExpenseEntry> expenses) {
        this.expenses = expenses;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the requester
     */
    public Requester getRequester() {
        return requester;
    }

    /**
     * @param requester the requester to set
     */
    public void setRequester(Requester requester) {
        this.requester = requester;
    }
    
    public void addSupervisorRecommendation(SupervisorRecommendation superRec){
        this.supervisorRecommendation=superRec;
    }
    
}
