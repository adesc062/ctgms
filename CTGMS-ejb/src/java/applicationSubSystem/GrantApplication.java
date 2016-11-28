/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationSubSystem;

import java.io.Serializable;
import javax.persistence.Entity;
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
 private String status; //should be enum/set value
 private Conference conference;
 private ExpenseEntry[] expenses;
 private String description;
 private ApplicationStatusEnum applicationStatus;
 private Requester requester;
// private Expense 
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue()
    private Long id;

    public void setup(String title, String status, Conference conference, ExpenseEntry[] expenses, String description, ApplicationStatusEnum applicationStatus, Requester requester) {
        this.title = title;
        this.status = status;
        this.conference = conference;
        this.expenses = expenses;
        this.description = description;
        this.applicationStatus = applicationStatus;
        this.requester= requester;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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
    public ExpenseEntry[] getExpenses() {
        return expenses;
    }

    /**
     * @param expenses the expenses to set
     */
    public void setExpenses(ExpenseEntry[] expenses) {
        this.expenses = expenses;
    }

    /**
     * @return the applicationStatus
     */
    public ApplicationStatusEnum getApplicationStatus() {
        return applicationStatus;
    }

    /**
     * @param applicationStatus the applicationStatus to set
     */
    public void setApplicationStatus(ApplicationStatusEnum applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
    
}
