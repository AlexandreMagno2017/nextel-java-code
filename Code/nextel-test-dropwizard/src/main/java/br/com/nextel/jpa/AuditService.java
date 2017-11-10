package br.com.nextel.jpa;

import javax.persistence.EntityTransaction;

import br.com.nextel.model.AuditEvent;

/**
 * @author Magno
 * Class responsible for save the event for audit.
 *
 */
public class AuditService
{
    private JPAFactory factory;

    public AuditService()
    {
        factory = new JPAFactory();
    }

    /**
     * @param username
     * @param action
     * @param entityName
     * @return Returns the event saved on database.
     */
    public AuditEvent save(String username, String action, String entityName)
    {
        AuditEvent entity = new AuditEvent(entityName, username, action);
        getTransaction().begin();
        factory.getEM().persist(entity);
        getTransaction().commit();
        return entity;
    }

    /**
     * @return Returns the transaction.
     */
    private EntityTransaction getTransaction()
    {
        return factory.getEM().getTransaction();
    }

}
