/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userSubsystem;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author adesc062
 */
@Entity
@Table(name="Users7972857")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String loginId;
    private String surname;
    private String givenNames;
    private String email;
    @Lob
    private byte[] hashedPassword;
    @Lob
    private byte[] salt;
    
    public User() {}

    public User(String loginId, String surname, String givenNames, String email, byte[] hashedPassword, byte[] salt) {
        this.loginId = loginId;
        this.surname = surname;
        this.givenNames = givenNames;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getLoginId() != null ? getLoginId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.getLoginId() == null && other.getLoginId() != null) || (this.getLoginId() != null && !this.loginId.equals(other.loginId))) {
            return false;
        }
        return true;
    }

    /**
     * @return the loginId
     */
    public String getLoginId() {
        return loginId;
    }
    
    /**
     * @return the hashedPassword
     */
    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    /**
     * @param hashedPassword the password to set
     */
    public void setHashedPassword(byte[] hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    /**
     * @return the salt
     */
    public byte[] getSalt() {
        return salt;
    }

    /**
     * @param salt the salt to set
     */
    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @return the givenNames
     */
    public String getGivenNames() {
        return givenNames;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @param givenNames the givenNames to set
     */
    public void setGivenNames(String givenNames) {
        this.givenNames = givenNames;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
