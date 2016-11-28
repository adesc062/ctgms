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
@Table(name="ExpensePolicies7972857")
public class ExpensePolicy implements Serializable {

    private int maxAmount;
     @Enumerated(EnumType.ORDINAL)
    private ExpenseTypeEnum expenseType;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    public ExpensePolicy(){
        //for jpu
    }

    public ExpensePolicy(int maxAmount, ExpenseTypeEnum expenseType) {
        this.setup( maxAmount, expenseType);
    }

    
    
    public void setup(int maxAmount, ExpenseTypeEnum expenseType) {
        this.maxAmount = maxAmount;
        this.expenseType = expenseType;
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
        if (!(object instanceof ExpensePolicy)) {
            return false;
        }
        ExpensePolicy other = (ExpensePolicy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "applicationSubSystem.ExpensePolicy[ id=" + id + " ]";
    }

    /**
     * @return the maxAmount
     */
    public int getMaxAmount() {
        return maxAmount;
    }

    /**
     * @param maxAmount the maxAmount to set
     */
    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    /**
     * @return the expenseType
     */
    public Enum getExpenseType() {
        return expenseType;
    }

    /**
     * @param expenseType the expenseType to set
     */
    public void setExpenseType(ExpenseTypeEnum expenseType) {
        this.expenseType = expenseType;
    }
    
}
