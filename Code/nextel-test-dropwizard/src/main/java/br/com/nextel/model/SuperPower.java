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
@Table(name = "SUPER_POWER")
@NamedQueries( {
@NamedQuery(name ="SuperPower.findByName", query = "SELECT h FROM SuperPower h WHERE h.name = :name"),
@NamedQuery(name ="SuperPower.listAll", query = "SELECT h FROM SuperPower h ") })
public class SuperPower implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 4592503210475875428L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "superhero_id")
    private Long superhero_id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
    
    public SuperPower()
    {
        // TODO Auto-generated constructor stub
    }

    public SuperPower(String name, String description)
    {
        setName(name);
        setDescription(description);
    }

    /**
     * @return the id
     */
    public Long getId()
    {
        return id;
    }


    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }


}
