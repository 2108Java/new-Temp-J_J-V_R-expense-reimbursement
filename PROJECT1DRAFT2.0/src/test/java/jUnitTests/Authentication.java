package jUnitTests;

import Repository.employeeDataBaseDAO;
import Repository.employeeDataBaseDAOImpl;
import Repository.employeeServiceImpl;
import Repository.managerDataBaseDAO;
import Repository.managerDataBaseDAOImpl;
import Repository.managerServiceImpl;
import Service.employeeService;
import Service.managerService;
import Service.authenticationService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Repository.authenticationImpl;

public class Authentication {
	
	employeeDataBaseDAO empData= new employeeDataBaseDAOImpl();
	employeeService newService = new employeeServiceImpl(empData);
	managerDataBaseDAO newM= new managerDataBaseDAOImpl();
	managerService newManagerService = new managerServiceImpl(newM);
	authenticationService auth = new authenticationImpl(empData);
	authenticationService auth1 = new authenticationImpl(newM);
	
	
@Before
public void whyTest() {
	System.out.println("Testing authentication");
}

@Test	
public void authentication() {

	Assert.assertTrue(auth.employeeLogIn(" userName", "password"));
	
	Assert.assertFalse(auth1.managerLogIn("aName", "aPass"));
		
}
}
