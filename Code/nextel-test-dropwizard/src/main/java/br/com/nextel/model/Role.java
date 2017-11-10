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
@Table(name = "ROLE")
@NamedQueries({
                @NamedQuery(name = "Role.findByName", query = "SELECT e FROM Role e WHERE e.name = :name"),
                @NamedQuery(name = "Role.listAll", query = "SELECT e FROM Role e ") })
public class Role implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 8512612805266447285L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;
    
    public Role()
    {
        // TODO Auto-generated constructor stub
    }

    public Role(String name)
    {
        setName(name);
    }


    /**
     * @return the userName
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            the userName to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

}
