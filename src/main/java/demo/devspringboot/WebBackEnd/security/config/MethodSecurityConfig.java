package demo.devspringboot.WebBackEnd.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true)
public class MethodSecurityConfig {
}
