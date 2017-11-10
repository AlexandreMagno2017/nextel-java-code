package br.com.nextel;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import br.com.nextel.auth.NextelAuth;
import br.com.nextel.auth.NextelAuthorizer;
import br.com.nextel.auth.NextelBasicAuthenticator;
import br.com.nextel.rest.CRUDHeroResource;
import br.com.nextel.rest.CRUDSuperPowerResource;
import br.com.nextel.rest.CRUDUserResource;

/*
 * To execute : java -jar nextel-test-dropwizard-0.0.1-SNAPSHOT.jar server
 * example.yml
 */
public class NextelTestApplication extends Application<NextelTestConfiguration>
{
    public static void main(String[] args) throws Exception
    {
        new NextelTestApplication().run(args);
    }

    @Override
    public String getName()
    {
        return "NextelTest";
    }

    @Override
    public void initialize(Bootstrap<NextelTestConfiguration> bootstrap)
    {
        // nothing to do yet
    }

    @Override
    public void run(NextelTestConfiguration configuration,
                    Environment environment)
    {
        final CRUDHeroResource resource = new CRUDHeroResource(
                        configuration.getTemplate(),
                        configuration.getDefaultName()
                        );
        environment.jersey().register(resource);

        final CRUDSuperPowerResource resource2 = new CRUDSuperPowerResource(
                        configuration.getTemplate(),
                        configuration.getDefaultName()
                        );
        environment.jersey().register(resource2);

        final CRUDUserResource resource3 = new CRUDUserResource(
                        configuration.getTemplate(),
                        configuration.getDefaultName()
                        );
        environment.jersey().register(resource3);

        // ****** Dropwizard security - custom classes ***********/
        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<NextelAuth>()
                        .setAuthenticator(new NextelBasicAuthenticator())
                        .setAuthorizer(new NextelAuthorizer())
                        .setRealm("BASIC-AUTH-REALM")
                        .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);

        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(NextelAuth.class));

        final TemplateHealthCheck healthCheck =
                        new TemplateHealthCheck(configuration.getTemplate());

        // Registers
        environment.healthChecks().register("template", healthCheck);
    }
}
