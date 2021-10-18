package Controller;

import Repository.authenticationImpl;
import Repository.managerServiceImpl;
import Repository.reimburseServiceImpl;
import Service.authenticationService;
import Service.managerService;
import Service.reimburseService;
import io.javalin.http.Context;

public class AuthenticateControllerImpl implements AuthenticateController{
	
	
	private static authenticationService authService = new authenticationImpl();
	private static managerService manageService = new managerServiceImpl();
	private reimburseService reimbService = new reimburseServiceImpl();	
	
	public AuthenticateControllerImpl(authenticationService authService, managerService manageService, reimburseService reimbService) {
		this.authService = authService;
		this.manageService = manageService;
		this.reimbService = reimbService;
	}

	public AuthenticateControllerImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean employeeLogin(Context ctx) {
		// TODO Auto-generated method stub
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		boolean result = authService.employeeLogIn(username, password);
		return result;
	}

	@Override
	public boolean managerLogin(Context ctx) {
		// TODO Auto-generated method stub
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		boolean result = authService.managerLogIn(username, password);
		return result;
	}

	@Override
	public boolean resetPassword(Context ctx) {
		// TODO Auto-generated method stub
		String username = ctx.sessionAttribute("access"); 
		String password = ctx.formParam("password");
		boolean result = authService.resetPassword(username, password);//check if username works
		return result;
	}

}
