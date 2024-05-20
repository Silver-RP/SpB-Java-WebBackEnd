package demo.devspringboot.WebBackEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;


@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaConfiguration {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
    static class AuditorAwareImpl implements AuditorAware<String> {
        @Override
        public Optional<String> getCurrentAuditor() {
            //get current username logged -> return;
            return Optional.ofNullable("Anonymus");
        }
    }
}
