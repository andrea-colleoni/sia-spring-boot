package corso.batch.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ConfigCommons {
	
	@Autowired
	private DataSource datasource;
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(datasource);
	}
	
	
//	@Bean
//	public PlatformTransactionManager transactionManager() {
//		DataSourceTransactionManager tm = new DataSourceTransactionManager();
//		tm.setDataSource(datasource);
//		return tm;
//	}

}
