package com.example;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class DemoSpringApplication {

	public static void main(String[] args) {
		Resource resource=new ClassPathResource("applicationContext.xml");  
	    BeanFactory factory=new XmlBeanFactory(resource);  
	      
	    Student student=(Student)factory.getBean("studentbean");  
	    student.displayInfo();  
	    
	    User user=(User)factory.getBean("user");  
	    System.out.println("user :" + user.toString());
	    
	    OutputHelper oh = (OutputHelper)factory.getBean("OutputHelper");
	    oh.parse();
	    
	    
	}
	
}
