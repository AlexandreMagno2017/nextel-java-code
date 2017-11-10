package br.com.nextel.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.nextel.model.SuperPower;

/**
 * @author Magno
 *
 */
public class SuperPowerService
{
    private JPAFactory factory;

    public SuperPowerService()
    {
        factory = new JPAFactory();
    }

    public SuperPower getSuperPower(String name)
    {
        SuperPower entity = null;
        TypedQuery<SuperPower> q = factory.getEM().createNamedQuery("SuperPower.findByName", SuperPower.class);
        q.setParameter("name", name);
        try
        {
            entity = q.getSingleResult();
            return entity;
        }
        catch (NoResultException e)
        {
            // TODO: handle exception
        }

        return null;
    }

    /**
     * Method responsible for save an super power.
     * 
     * @param name
     * @param description
     * @return
     */
    public SuperPower save(String name, String description)
    {
        SuperPower entity = new SuperPower(name, description);
        getTransaction().begin();
        factory.getEM().persist(entity);
        getTransaction().commit();
        return entity;
    }

    private EntityTransaction getTransaction()
    {
        return factory.getEM().getTransaction();
    }

    /**
     * Method responsible for update an super power.
     * 
     * @param name
     * @param description
     * @return
     */
    public SuperPower update(String name, String description)
    {
        SuperPower entity = null;
        TypedQuery<SuperPower> q = factory.getEM().createNamedQuery("SuperPower.findByName", SuperPower.class);
        q.setParameter("name", name);
        try
        {
            entity = q.getSingleResult();
            entity.setDescription(description);
            getTransaction().begin();
            factory.getEM().merge(entity);
            getTransaction().commit();
            return entity;
        }
        catch (NoResultException e)
        {
            // TODO: handle exception
        }

        return null;
    }

    /**
     * Method responsible for remove an super power by name.
     * 
     * @param name
     */
    public void delete(String name)
    {
        SuperPower entity = null;
        TypedQuery<SuperPower> q = factory.getEM().createNamedQuery("SuperPower.findByName", SuperPower.class);
        q.setParameter("name", name);
        try
        {
            entity = q.getSingleResult();

            getTransaction()
                            .begin();
            factory.getEM().remove(entity);
            getTransaction()
                            .commit();
        }
        catch (NoResultException e)
        {
            // TODO: handle exception
        }
    }

    /**
     * Method responsible for return list of super powers.
     * 
     * @return
     */
    public List<SuperPower> listSuperPowers()
    {
        List<SuperPower> list = new ArrayList<SuperPower>();
        TypedQuery<SuperPower> q = factory.getEM().createNamedQuery("SuperPower.listAll", SuperPower.class);
        try
        {
            list = q.getResultList();
        }
        catch (NoResultException e)
        {
            // TODO: handle exception
        }

        return list;
    }

}
