package emma.store.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import emma.store.dao.*;

@Configuration
@ComponentScan("emma.store.*")
@EnableTransactionManagement
@PropertySource("classpath:hibernate.properties")
public class AppConfig{


	@Autowired
	private Environment environment;

	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
    	
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();                             
        return commonsMultipartResolver;
    }

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driver.class.name"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.user.name"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));

		System.out.println("## getDataSource: " + dataSource);
		return dataSource;
	}

	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) throws Exception 
	{

		Properties properties = new Properties();
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
		properties.put("hibernate.generate_statistics", environment.getProperty("hibernate.generate_statistics"));
		//properties.put("current_session_context_class", environment.getProperty("current_session_context_class"));


		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

		factoryBean.setPackagesToScan("emma.store.entity");
		factoryBean.setDataSource(dataSource);
		factoryBean.setHibernateProperties(properties);
		factoryBean.afterPropertiesSet();

		SessionFactory sf = factoryBean.getObject();
		System.out.println("## getSessionFactory: " + sf);
		return sf;

	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {

		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
	@Bean(name = "userDao")
	public UserDao getUserDao()  {
		return new UserDaoImpl();
	}

	@Bean(name="bookDao")
	public BookDao getBookDao() {
		return new BookDaoImpl();
	}
}

