package br.com.nextel;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.glassfish.jersey.server.ResourceConfig;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jersey2.InstrumentedResourceMethodApplicationListener;

public class NextelTestResourceConfig extends ResourceConfig
{
    private Set<Class<?>> classes = new HashSet<Class<?>>();

    public NextelTestResourceConfig()
    {
        initializeApplication();
    }

    private void initializeApplication()
    {
        registerListeners(); // Register listeners
    }

    private void registerListeners()
    {
       final MetricRegistry metricRegistry = new MetricRegistry(); register(new
       InstrumentedResourceMethodApplicationListener(metricRegistry));
       ConsoleReporter.forRegistry(metricRegistry)
       .convertRatesTo(TimeUnit.SECONDS)
       .convertDurationsTo(TimeUnit.MILLISECONDS) .build() .start(1,
       TimeUnit.MINUTES);
       //logger.info("Console reporter is enabled successfully!");
      
    }

}
