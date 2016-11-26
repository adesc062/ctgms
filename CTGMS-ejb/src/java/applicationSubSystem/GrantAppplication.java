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

/**
 *
 * @author user1
 */
@Entity
@Table(name="Application7972857")
public class GrantAppplication implements Serializable {
private String title;
//ignoredType
 private String status;//should be enum/set value
 private String conference;
 private String description;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue()
    private Long id;

 public GrantAppplication(){
     
 }
    public GrantAppplication(String title, String status, String conference, String description) {
        
        this.title = title;
        this.status = status;
        this.conference = conference;
        this.description = description;
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
        if (!(object instanceof GrantAppplication)) {
            return false;
        }
        GrantAppplication other = (GrantAppplication) object;
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
    public String getConference() {
        return conference;
    }

    /**
     * @param conference the conference to set
     */
    public void setConference(String conference) {
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
    
}
