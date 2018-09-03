package br.org.ark.secureapi.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("ark")
public class SecurityApiProperties {

    public String authTokenHeaderName;

    public String authToken;
}
