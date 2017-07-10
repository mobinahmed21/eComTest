package com.mobin.config;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.MapUtils;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.google.common.collect.Maps;

@Configuration
@ComponentScan(basePackages = "com.mobin.dao")
@EnableJpaRepositories(
    basePackages = "com.mobin.dao.repositories",
    entityManagerFactoryRef = "entityManagerFactory"
)
@EnableAspectJAutoProxy
@EnableConfigurationProperties
public class SpringCongurationLocal {

	@Autowired
	  private Environment env;

	  @Bean
	  @Primary
	  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	    DataSourcePropertyConfig masterConfig = masterDatasourceConfig();
	    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	    em.setDataSource(dataSource(masterConfig));
	    em.setPackagesToScan(new String[]{"com.mobin.dao"});

	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    em.setJpaVendorAdapter(vendorAdapter);
	    //set up the hibernate properties
	    Map<String, String> hibernateMap = Maps.newHashMap();
	    hibernateMap.put("physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
	    hibernateMap.put("implicit_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
	    hibernateMap.put("show_sql", "true");
	    masterConfig.setHibernate(hibernateMap);
	    if (MapUtils.isNotEmpty(masterConfig.getHibernate())) {
	      Map<String, Object> properties = Maps.newHashMap();
	      masterConfig.getHibernate().forEach((key, value) -> properties.put("hibernate." + key, value));
	      em.setJpaPropertyMap(properties);
	    }

	    return em;
	  }
	  
	  @Bean
	  public PlatformTransactionManager transactionManager(){
		  JpaTransactionManager jtm = new JpaTransactionManager();
		  jtm.setEntityManagerFactory(entityManagerFactory().getObject());
		  return jtm;
	  }
	  
	  @Primary
	  @Bean
	  public DataSource dataSource(DataSourcePropertyConfig masterDataSourceConfig) {
		DataSource dataSource = createDataSource(masterDataSourceConfig);
	    return dataSource;
	  }

	  private DataSourcePropertyConfig masterDatasourceConfig() {  
	    return new DataSourcePropertyConfig(env);
	  }
	  
	  /**
	   *
	   * @param config
	   * @return
	   */
	  private DataSource createDataSource(DataSourcePropertyConfig config) {
	    DataSourceBuilder builder = DataSourceBuilder.create();
	    org.apache.tomcat.jdbc.pool.DataSource datasource =
	        (org.apache.tomcat.jdbc.pool.DataSource) builder.type(org.apache.tomcat.jdbc.pool.DataSource.class)
	            .driverClassName(config.getDriverClassName())
	            .url(config.getUrl())
	            .username(config.getUser())
	            .password(config.getPassword()).build();

	    datasource.setMaxActive(config.getPoolSize());
	    datasource.setMaxIdle(config.getPoolSize() / 2);
	    datasource.setMaxAge(config.getConnectionMaxAgeMinutes() * 60 * 1000);

	    datasource.setTestOnBorrow(config.isTestOnBorrow());
	    datasource.setValidationQuery(config.getValidationQuery());
	    datasource.setValidationInterval(config.getValidationIntervalSeconds()*1000);

	    return datasource;
	  }
}
