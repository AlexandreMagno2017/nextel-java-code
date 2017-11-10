package br.com.nextel.rest;

import io.dropwizard.auth.Auth;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.nextel.auth.NextelAuth;
import br.com.nextel.jpa.AuditService;
import br.com.nextel.jpa.SuperPowerService;
import br.com.nextel.model.SuperPower;

@Path("/superpower")
@Produces(MediaType.APPLICATION_JSON)
public class CRUDSuperPowerResource
{
    private final String template;
    private final String defaultName;

    public CRUDSuperPowerResource(String template, String defaultName)
    {
        this.template = template;
        this.defaultName = defaultName;
    }

    @POST
    @Path("/save")
    public Response saveHero(@Auth NextelAuth auth, @QueryParam("name") String name, @QueryParam("description") String description)
    {
        SuperPowerService service = new SuperPowerService();
        SuperPower superPower = service.save(name, description);
        
        AuditService auditService = new AuditService();
        auditService.save(auth.getName(), "SAVE", name);
        
        return Response.ok(superPower).build();
    }

    @PUT
    @Path("/update")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response updateHero(@Auth NextelAuth auth, @QueryParam("name") String name, @QueryParam("description") String description)
    {
        SuperPowerService service = new SuperPowerService();
        SuperPower superPower = service.update(name, description);
        
        AuditService auditService = new AuditService();
        auditService.save(auth.getName(), "SAVE", name);

        return Response.ok(superPower).build();
    }

    @DELETE
    @Path("/delete")
    public Response delete(@Auth NextelAuth auth, @QueryParam("name") String name)
    {
        SuperPowerService service = new SuperPowerService();
        service.delete(name);
        
        AuditService auditService = new AuditService();
        auditService.save(auth.getName(), "SAVE", name);

        return Response.ok().build();
    }

    @GET
    @Path("/register")
    @RolesAllowed({ "Standard" })
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getSuperPower(@QueryParam("name") String name)
    {
        SuperPowerService service = new SuperPowerService();
        SuperPower superPower = service.getSuperPower(name);
        return Response.ok(superPower).build();
    }

    @GET
    @Path("/list")
    @RolesAllowed({ "Standard" })
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response listSupePowers()
    {
        SuperPowerService service = new SuperPowerService();
        List<SuperPower> list = service.listSuperPowers();
        return Response.ok(list).build();
    }

}