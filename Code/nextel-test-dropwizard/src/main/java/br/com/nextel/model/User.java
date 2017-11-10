package br.com.nextel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "USER")
@NamedQueries({
                @NamedQuery(name = "User.findByName", query = "SELECT e FROM User e WHERE e.userName = :name"),
                @NamedQuery(name = "User.listAll", query = "SELECT e FROM User e ") })
public class User implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 8512612805266447285L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    public User()
    {
        // TODO Auto-generated constructor stub
    }

    public User(String name, String pwd)
    {
        setUserName(name);
        setPassword(pwd);
    }


    /**
     * @return the userName
     */
    public String getUserName()
    {
        return userName;
    }


    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }


    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }


    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }


    /**
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

}
