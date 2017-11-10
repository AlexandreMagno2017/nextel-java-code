package br.com.nextel.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SUPER_HERO")

@NamedQueries( {
@NamedQuery(name ="SuperHero.findByName", query = "SELECT h FROM SuperHero h WHERE h.name = :name"),
@NamedQuery(name ="SuperHero.listAll", query = "SELECT h FROM SuperHero h ") })
public class SuperHero
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "alias")
    private String alias;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "superhero_id")
    private ProtectionArea protecttionArea;

    @OneToMany(cascade = CascadeType.ALL,
               orphanRemoval = true)
    @JoinColumn(name = "superhero_id")
    List<SuperPower> powers;

    public SuperHero()
    {
        // TODO Auto-generated constructor stub
    }

    public SuperHero(String name, String alias)
    {
        setName(name);
        setAlias(alias);
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
     * @return the alias
     */
    public String getAlias()
    {
        return alias;
    }

    /**
     * @param alias
     *            the alias to set
     */
    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    /**
     * @return the protecttionArea
     */
    public ProtectionArea getProtecttionArea()
    {
        return protecttionArea;
    }

    /**
     * @param protecttionArea
     *            the protecttionArea to set
     */
    public void setProtecttionArea(ProtectionArea protecttionArea)
    {
        this.protecttionArea = protecttionArea;
    }

    /**
     * @return the powers
     */
    public List<SuperPower> getPowers()
    {
        return powers;
    }

    /**
     * @param powers
     *            the powers to set
     */
    public void setPowers(List<SuperPower> powers)
    {
        this.powers = powers;
    }
}
