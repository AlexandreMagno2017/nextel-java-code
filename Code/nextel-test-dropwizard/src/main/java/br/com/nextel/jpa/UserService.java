package br.com.nextel.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.nextel.model.User;

/**
 * @author Magno
 * Class responsible for operations over users.
 */
public class UserService
{
    private JPAFactory factory;

    public UserService()
    {
        factory = new JPAFactory();
    }

    /**
     * Method responsible for get an specific user by name.
     * @param name
     * @return
     */
    public User getUser(String name)
    {
        User entity = null;
        TypedQuery<User> q = factory.getEM().createNamedQuery("User.findByName", User.class);
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
     * Method responsible for save an user.
     * 
     * @param name
     * @param password
     * @return
     */
    public User save(String name, String password)
    {
        User entity = new User(name, password);
        getTransaction().begin();
        factory.getEM().persist(entity);
        getTransaction().commit();
        return entity;
    }

    private EntityTransaction getTransaction()
    {
        return factory.getEM().getTransaction();
    }

    /** Method responsible for update an user.
     * @param name
     * @param password
     * @return
     */
    public User update(String name, String password)
    {
        User entity = null;
        TypedQuery<User> q = factory.getEM().createNamedQuery("User.findByName", User.class);
        q.setParameter("name", name);
        try
        {
            entity = q.getSingleResult();
            entity.setPassword(password);
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
     * Method responsible for remove an user.
     * 
     * @param name
     */
    public void delete(String name)
    {
        User entity = null;
        TypedQuery<User> q = factory.getEM().createNamedQuery("User.findByName", User.class);
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
     * Method responsible for return list of users.
     * @return
     */
    public List<User> listUsers()
    {
        List<User> list = new ArrayList<User>();
        TypedQuery<User> q = factory.getEM().createNamedQuery("User.listAll", User.class);
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
