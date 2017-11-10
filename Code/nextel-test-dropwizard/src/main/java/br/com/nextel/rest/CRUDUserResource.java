package br.com.nextel.rest;

import io.dropwizard.auth.Auth;

import java.util.List;

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
import br.com.nextel.jpa.UserService;
import br.com.nextel.model.User;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class CRUDUserResource
{
    private final String template;
    private final String defaultName;

    public CRUDUserResource(String template, String defaultName)
    {
        this.template = template;
        this.defaultName = defaultName;
    }

    /**
     * Method responsible for save an user.
     * 
     * @param auth
     * @param name
     * @param password
     * @return
     */
    @POST
    @Path("/save")
    public Response save(@Auth NextelAuth auth, @QueryParam("name") String name, @QueryParam("password") String password)
    {
        UserService adminUSerService = new UserService();
        User user = adminUSerService.save(name, password);

        AuditService auditService = new AuditService();
        auditService.save(auth.getName(), "SAVE", name);

        return Response.ok(user).build();
    }

    /**
     * Method responsible for update an user.
     * 
     * @param auth
     * @param name
     * @param password
     * @return
     */
    @PUT
    @Path("/update")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response update(@Auth NextelAuth auth, @QueryParam("name") String name, @QueryParam("password") String password)
    {
        UserService adminUSerService = new UserService();
        User user = adminUSerService.update(name, password);

        AuditService auditService = new AuditService();
        auditService.save(auth.getName(), "UPDATE", name);

        return Response.ok(user).build();
    }

    /**
     * Method responsible for remove an user.
     * @param auth
     * @param name
     * @return
     */
    @DELETE
    @Path("/delete")
    public Response delete(@Auth NextelAuth auth, @QueryParam("name") String name)
    {
        UserService adminUSerService = new UserService();
        adminUSerService.delete(name);

        AuditService auditService = new AuditService();
        auditService.save(auth.getName(), "DELETE", name);

        return Response.ok().build();
    }

    /** Method responsible for get an user by name.
     * @param name
     * @return
     */
    @GET
    @Path("/user")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getUser(@QueryParam("name") String name)
    {
        UserService adminUSerService = new UserService();
        User user = adminUSerService.getUser(name);
        return Response.ok(user).build();
    }

    /**
     * Method responsible for return list of users.
     * @return
     */
    @GET
    @Path("/list")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response listUsers()
    {
        UserService adminUSerService = new UserService();
        List<User> list = adminUSerService.listUsers();
        return Response.ok(list).build();
    }

}