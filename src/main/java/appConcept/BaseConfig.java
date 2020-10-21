package appConcept;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import appConcept.dao.Detail;
import appConcept.dao.Employee;
import appConcept.dao.Team;

@Configuration
@EnableWebMvc
@ComponentScan("appConcept,appConcept.controllers,appConcept.dao,appConcept.servlet,appConcept.services")
//@PropertySource("classpath:db.properties")
public class BaseConfig implements WebMvcConfigurer {

	//possible loading of DB properties from file @PropertySource
//	@Autowired
//	private Environment env;

	
	//ViewResolver for referencing .JSP pages in @Controller
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver view = new InternalResourceViewResolver();
		view.setPrefix("/WEB-INF/view/");
		view.setSuffix(".jsp");
		return view;
	}

	
	//SessionFactory
	@Bean
	public SessionFactory sessionFactory() {
		
		Properties prop = new Properties();
		prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/conceptapp");
		prop.setProperty("connection.driver_class", "com.mysql.jc.jdbc.Driver");
		prop.setProperty("hibernate.connection.username", "mvcuser");
		prop.setProperty("hibernate.connection.password", "mvcpass");
		prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
		prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
		prop.setProperty("hibernate.show_sql", "true");
		prop.setProperty("hibernate.current_session_context_class","thread");
		prop.setProperty("hibernate.format_sql", "false");
		prop.setProperty("hibernate.c3p0.min_size", "2");
		prop.setProperty("hibernate.c3p0.max_size", "2");
		prop.setProperty("hibernate.c3p0.acquire_increment", "20");
		prop.setProperty("hibernate.c3p0.timeout", "1800");
		
		SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().addProperties(prop)
				.addAnnotatedClass(Detail.class)
				.addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Team.class)
				.buildSessionFactory();
		System.out.println("sessionfactory initialized");
		System.out.println(sessionFactory.toString());
		return sessionFactory;
	}

	
	//handling of static resources - Bootstrap CSS
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/css/**").addResourceLocations("/css/");
	}

	
	
}
