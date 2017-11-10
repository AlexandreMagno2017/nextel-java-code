package br.com.nextel.auth;

import io.dropwizard.auth.Authorizer;

/**
 * @author Magno
 *
 */
public class NextelAuthorizer implements Authorizer<NextelAuth>
{
    public boolean authorize(NextelAuth user, String role)
    {
        return user.getRoles() != null && user.getRoles().contains(role);
    }
}