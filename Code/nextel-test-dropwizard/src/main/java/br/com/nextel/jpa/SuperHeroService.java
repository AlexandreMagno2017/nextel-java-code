package br.com.nextel.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.nextel.model.SuperHero;

/**
 * @author Magno
 *
 */
public class SuperHeroService
{
    private JPAFactory factory;

    public SuperHeroService()
    {
        factory = new JPAFactory();
    }

    /**
     * Method responsible for get an specific hero by name.
     * @param name
     * @return
     */
    public SuperHero get(String name)
    {
        SuperHero entity = null;
        TypedQuery<SuperHero> q = factory.getEM().createNamedQuery("SuperHero.findByName", SuperHero.class);
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
     * Method responsible for save a hero.
     * 
     * @param name
     * @param alias
     * @return
     */
    public SuperHero save(String name, String alias)
    {
        SuperHero entity = new SuperHero(name, alias);
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
     * Method responsible for update an user.
     * 
     * @param name
     * @param newAlias
     * @return
     */
    public SuperHero update(String name, String newAlias)
    {
        SuperHero entity = null;
        TypedQuery<SuperHero> q = factory.getEM().createNamedQuery("SuperHero.findByName", SuperHero.class);
        q.setParameter("name", name);
        try
        {
            entity = q.getSingleResult();
            entity.setAlias(newAlias);
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
     * Method responsible for remove an hero.
     * 
     * @param name
     */
    public void delete(String name)
    {
        SuperHero entity = null;
        TypedQuery<SuperHero> q = factory.getEM().createNamedQuery("SuperHero.findByName", SuperHero.class);
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
     * Method responsible for get list of superhero.
     * 
     * @return
     */
    public List<SuperHero> listSuperHeroes()
    {
        List<SuperHero> list = new ArrayList<SuperHero>();
        TypedQuery<SuperHero> q = factory.getEM().createNamedQuery("SuperHero.listAll", SuperHero.class);
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
