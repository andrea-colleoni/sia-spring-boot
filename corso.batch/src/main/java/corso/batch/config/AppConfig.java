package corso.batch.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@Import(value= { ConfigCommons.class })
public class AppConfig {
	
	@Value("/dest-schema.sql")
	private Resource destSchemaScript;
	
	@Bean
	public DataSource datasource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/sia-batch?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		ds.setUsername("root");
		ds.setPassword("");
		return ds;
	}
	
	@Bean
	public DataSourceInitializer dataSourceInitializer() {
		ResourceDatabasePopulator pop = new ResourceDatabasePopulator();
		pop.addScript(destSchemaScript);
		
		DataSourceInitializer dsi = new DataSourceInitializer();
		dsi.setDataSource(datasource());
		dsi.setDatabasePopulator(pop);
		
		return dsi;
	}
}
