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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@EnableJpaRepositories(basePackages = "sia.jpa.rep1", entityManagerFactoryRef = "primaryEMF")
public class PrimaryEntityManager {

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource.em1")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name="primaryEMF")
	@Primary
	@PersistenceContext(unitName = "primary")
	public LocalContainerEntityManagerFactoryBean emf1(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(primaryDataSource())
				.persistenceUnit("primary")
				.properties(jpaProperties())
				.packages("sia.jpa.db1")
				.build();
	}

	private Map<String, Object> jpaProperties() {
		HashMap<String, Object> props = new HashMap<>();
		props.put("hibernate.ejb.naming_strategy", new SpringNamingPolicy());
		return props;
	}

}
