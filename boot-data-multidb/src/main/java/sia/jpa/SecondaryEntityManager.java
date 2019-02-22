package sia.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@EnableJpaRepositories(basePackages = "sia.jpa.rep2", entityManagerFactoryRef = "secondaryEMF")
public class SecondaryEntityManager {

	@Bean
	@ConfigurationProperties(prefix = "datasource.em2")
	public DataSource secondaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name="secondaryEMF")
	@PersistenceContext(unitName = "secondary")
	public LocalContainerEntityManagerFactoryBean emf2(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(secondaryDataSource())
				.persistenceUnit("secondary")
				.properties(jpaProperties())
				.packages("sia.jpa.db2")
				.build();
	}

	private Map<String, Object> jpaProperties() {
		HashMap<String, Object> props = new HashMap<>();
		props.put("hibernate.ejb.naming_strategy", new SpringNamingPolicy());
		return props;
	}

}
