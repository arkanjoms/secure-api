package br.org.ark.secureapi.security.config;

import br.org.ark.secureapi.security.filter.ApiKeyAuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.util.StringUtils;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityApiConfig extends WebSecurityConfigurerAdapter {

    private SecurityApiProperties securityApiProperties;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
                antMatcher("/api/**").
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().addFilter(getApiKeyAuthFilter()).authorizeRequests().anyRequest().authenticated();
    }

    private ApiKeyAuthFilter getApiKeyAuthFilter() {
        ApiKeyAuthFilter filter = new ApiKeyAuthFilter(securityApiProperties.getAuthTokenHeaderName());
        filter.setAuthenticationManager(authentication -> {
            String principal = (String) authentication.getPrincipal();
            if (!StringUtils.isEmpty(securityApiProperties.getAuthToken()) && !securityApiProperties.getAuthToken().equals(principal)) {
                throw new BadCredentialsException("Invalid Token.");
            }
            authentication.setAuthenticated(true);
            return authentication;
        });
        return filter;
    }
}
