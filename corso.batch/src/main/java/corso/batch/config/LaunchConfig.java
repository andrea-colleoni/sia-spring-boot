package corso.batch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = { AppConfig.class, BatchConfig.class })
public class LaunchConfig {

}
