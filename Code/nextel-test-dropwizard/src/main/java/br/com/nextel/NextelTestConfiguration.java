package br.com.nextel;

import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NextelTestConfiguration extends Configuration
{
    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Nextel";

    @NotNull
    private String login;
    @NotNull
    private String password;

    @JsonProperty
    public String getLogin()
    {
        return login;
    }

    @JsonProperty
    public String getPassword()
    {
        return password;
    }

    @JsonProperty
    public String getTemplate()
    {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template)
    {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName()
    {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name)
    {
        this.defaultName = name;
    }
}
