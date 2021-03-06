/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationSubSystem;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author user1
 */
@Entity
@Table(name="ExpenseEntries7972857")
public class ExpenseEntry implements Serializable {

    private ExpensePolicy policy;
    private GrantApplication grantApp;
    private double expenseAmount;
    @Enumerated(EnumType.ORDINAL)
    private ExpenseTypeEnum expenseType;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public ExpenseEntry(){
        //empty for jpu
    }
    
    public ExpenseEntry(GrantApplication grantApp, double expenseAmount, ExpenseTypeEnum expenseType) {
        this.setup(expenseAmount, grantApp, expenseType);
        grantApp.addToTotal(this);  
    }
 
    public void setup(double expenseAmount, GrantApplication grantApp, ExpenseTypeEnum expenseType) {
        this.expenseAmount = expenseAmount;
        this.grantApp=grantApp;
        this.setExpenseType(expenseType);
    }

    public Long getId() {
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
        if (!(object instanceof ExpenseEntry)) {
            return false;
        }
        ExpenseEntry other = (ExpenseEntry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "applicationSubSystem.ExpenseEntry[ id=" + id + " ]";
    }

    /**
     * @return the policy
     */
    public ExpensePolicy getPolicy() {
        return policy;
    }

    /**
     * @param policy the policy to set
     */
    public void setPolicy(ExpensePolicy policy) {
        this.policy = policy;
    }

    /**
     * @return the expenseAmount
     */
    public double getExpenseAmount() {
        return expenseAmount;
    }

    /**
     * @param expenseAmount the expenseAmount to set
     */
    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    /**
     * @return the expenseType
     */
    public ExpenseTypeEnum getExpenseType() {
        return expenseType;
    }

    /**
     * @param expenseType the expenseType to set
     */
    public void setExpenseType(ExpenseTypeEnum expenseType) {
        this.expenseType = expenseType;
    }
    
}
