package br.com.nextel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROTECTION_AREA")
public class ProtectionArea implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 6757507810416967737L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "longitude")
    private Float longitude;
    
    @Column(name = "lat")
    private Float lat;
    
    @Column(name = "radius")
    private Float radius;

    public ProtectionArea()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the longitude
     */
    public Float getLongitude()
    {
        return longitude;
    }

    /**
     * @param longitude
     *            the longitude to set
     */
    public void setLongitude(Float longitude)
    {
        this.longitude = longitude;
    }

    /**
     * @return the lat
     */
    public Float getLat()
    {
        return lat;
    }

    /**
     * @param lat
     *            the lat to set
     */
    public void setLat(Float lat)
    {
        this.lat = lat;
    }

    /**
     * @return the radius
     */
    public Float getRadius()
    {
        return radius;
    }

    /**
     * @param radius
     *            the radius to set
     */
    public void setRadius(Float radius)
    {
        this.radius = radius;
    }


}