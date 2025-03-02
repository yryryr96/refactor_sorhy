package ssafy.sorhy.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ssafy.sorhy")
public class JPAConfig {

    @PersistenceContext
    private EntityManager em;

    @Bean
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(em);
    }
}
