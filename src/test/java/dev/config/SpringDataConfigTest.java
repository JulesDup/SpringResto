package dev.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Import({ DataSourceH2TestConfig.class, JpaConfig.class })
public class SpringDataConfigTest {

}


