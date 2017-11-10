package br.com.nextel.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Magno
 * Class that manages the operations over database.
 */
public class JPAFactory
{
    public static final String DEFAULT_PU = "jpa-app";
    private final EntityManagerFactory factory;
    private final EntityManager em;

    public JPAFactory()
    {
        factory = Persistence.createEntityManagerFactory(DEFAULT_PU);
        em = factory.createEntityManager();
    }

    public JPAFactory(String pu)
    {
        factory = Persistence.createEntityManagerFactory(pu);
        em = factory.createEntityManager();

    }

    /**
     * @return the persistenceUnitName
     */
    public static String getPU()
    {
        return DEFAULT_PU;
    }

    /**
     * Return the entity manager factory.
     * @return the factory
     */
    public EntityManagerFactory getEMF()
    {
        return factory;
    }

    /**
     * Return the entity manager.
     * @return the em
     */
    public EntityManager getEM()
    {
        return em;
    }
}
