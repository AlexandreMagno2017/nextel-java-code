package br.com.nextel.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "AUDIT_EVENT")
public class AuditEvent
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "entity")
    private String entity;

    @Column(name = "date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;

    @Column(name = "user_name")
    private String username;

    @Column(name = "action")
    private String action;

    public AuditEvent()
    {
        // TODO Auto-generated constructor stub
    }

    public AuditEvent(String entity, String username, String action)
    {
        setEntity(entity);
        setUsername(username);
        setAction(action);
        setDatetime(Calendar.getInstance().getTime());
    }

    /**
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * @return the entity
     */
    public String getEntity()
    {
        return entity;
    }

    /**
     * @param entity
     *            the entity to set
     */
    public void setEntity(String entity)
    {
        this.entity = entity;
    }

    /**
     * @return the datetime
     */
    public Date getDatetime()
    {
        return datetime;
    }

    /**
     * @param datetime
     *            the datetime to set
     */
    public void setDatetime(Date datetime)
    {
        this.datetime = datetime;
    }

    /**
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * @return the action
     */
    public String getAction()
    {
        return action;
    }

    /**
     * @param action
     *            the action to set
     */
    public void setAction(String action)
    {
        this.action = action;
    }


}
