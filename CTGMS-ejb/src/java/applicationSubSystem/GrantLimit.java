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
import userSubsystem.RequesterTypeEnum;

/**
 *
 * @author user1
 */
@Entity
public class GrantLimit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private RequesterTypeEnum requesterType;
    private int maxGrant;

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
        if (!(object instanceof GrantLimit)) {
            return false;
        }
        GrantLimit other = (GrantLimit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "applicationSubSystem.GrantLimit[ id=" + id + " ]";
    }

    /**
     * @return the requesterType
     */
    public RequesterTypeEnum getRequesterType() {
        return requesterType;
    }

    /**
     * @param requesterType the requesterType to set
     */
    public void setRequesterType(RequesterTypeEnum requesterType) {
        this.requesterType = requesterType;
    }

    /**
     * @return the maxGrant
     */
    public int getMaxGrant() {
        return maxGrant;
    }

    /**
     * @param maxGrant the maxGrant to set
     */
    public void setMaxGrant(int maxGrant) {
        this.maxGrant = maxGrant;
    }
    
}
