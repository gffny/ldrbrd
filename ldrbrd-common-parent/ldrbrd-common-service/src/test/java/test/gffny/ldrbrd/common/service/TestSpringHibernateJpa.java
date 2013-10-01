/**
 * 
 */
package test.gffny.ldrbrd.common.service;

import java.util.List;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gffny.ldrbrd.common.model.UserProfile;
import com.gffny.ldrbrd.common.service.impl.PersonService;

public class TestSpringHibernateJpa {

	public static void main(String[] args) {
		System.out.println("************** BEGINNING PROGRAM **************");

		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/spring-config.xml");
		//ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-service.xml");
		PersonService personService = (PersonService) context.getBean("personService");

		UserProfile person = new UserProfile(false);

		personService.addPerson(person);
		System.out.println("Person : " + person + " added successfully");

		List<UserProfile> persons = personService.fetchAllPersons();
		System.out.println("The list of all persons = " + persons);

		System.out.println("************** ENDING PROGRAM *****************");
	}
}