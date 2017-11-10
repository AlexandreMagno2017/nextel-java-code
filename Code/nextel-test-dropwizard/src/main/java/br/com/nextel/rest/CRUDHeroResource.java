package br.com.nextel.rest;

import io.dropwizard.auth.Auth;

import java.util.List;

import javax.annotation.security.PermitAll;
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
import br.com.nextel.jpa.SuperHeroService;
import br.com.nextel.model.SuperHero;

@Path("/superhero")
@Produces(MediaType.APPLICATION_JSON)
public class CRUDHeroResource
{
    private final String template;
    private final String defaultName;

    public CRUDHeroResource(String template, String defaultName)
    {
        this.template = template;
        this.defaultName = defaultName;
    }

    @POST
    @RolesAllowed({ "Admin" })
    @Path("/save")
    public Response save(@Auth NextelAuth auth, @QueryParam("name") String name, @QueryParam("alias") String alias)
    {
        SuperHeroService heroService = new SuperHeroService();
        SuperHero hero = heroService.save(name, alias);

        AuditService auditService = new AuditService();
        auditService.save(auth.getName(), "SAVE", name);

        return Response.ok(hero).build();
    }

    @PUT
    @RolesAllowed({ "Admin" })
    @Path("/update")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response update(@Auth NextelAuth auth, @QueryParam("name") String name, @QueryParam("alias") String alias)
    {
        SuperHeroService heroService = new SuperHeroService();
        SuperHero hero = heroService.update(name, alias);

        AuditService auditService = new AuditService();
        auditService.save(auth.getName(), "UPDATE", name);

        return Response.ok(hero).build();
    }

    @DELETE
    @RolesAllowed({ "Admin" })
    @Path("/delete")
    public Response delete(@Auth NextelAuth auth, @QueryParam("name") String name)
    {
        SuperHeroService heroService = new SuperHeroService();
        AuditService auditService = new AuditService();
        auditService.save(auth.getName(), "DELETE", name);
        heroService.delete(name);
        return Response.ok().build();
    }

    @GET
    @PermitAll
    @RolesAllowed({ "Standard" })
    @Path("/register")
    public Response get(@Auth NextelAuth auth, @QueryParam("name") String name)
    {
        SuperHeroService heroService = new SuperHeroService();
        SuperHero hero = heroService.get(name);
        return Response.ok(hero).build();
    }

    @GET
    @PermitAll
    @RolesAllowed({ "Standard" })
    @Path("/list")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response listSupeHeroes(@Auth NextelAuth auth)
    {
        SuperHeroService heroService = new SuperHeroService();
        List<SuperHero> list = heroService.listSuperHeroes();
        return Response.ok(list).build();
    }

}