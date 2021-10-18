package Controller;

import Models.Employee;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class RequestHandler {
	
	static EmployeeController employeeController = new EmployeeControllerImpl();
	static FinanceManagerController financeManController = new FinanceManagerControllerImpl();//re-factor this class
	static AuthenticateController authenticateController = new AuthenticateControllerImpl();		
	
	
	Javalin app = Javalin.create().start(8000);//create javalin instance in MainDriver
	
	Employee employee = new Employee();
	
	public static boolean checkAccess(Context ctx) {
		if(ctx.sessionAttribute("access") != null //checking if session exists
				&& (Boolean) ctx.sessionAttribute("access") == true) {
			return true;
		}else {
			return false;
		}
	}
	
	public static void setupEndPoints(Javalin app) {
	
	//Employee functions
	//Submit request
	app.post("/employeeRequest", ctx -> {
		if(checkAccess(ctx)) {
			ctx.json(employeeController.submitRequest(ctx));
		}
		
	});
	
	//View all requests
	app.get("/reimbursements/employee", ctx -> {
		if(checkAccess(ctx)) {
			ctx.json(employeeController.viewAllRequestsById(ctx));
		}
	});
	
	//Login -- we need only one method
	
	app.get("/", 			
			ctx -> ctx.req.getRequestDispatcher("/EmployeeType.html").forward(ctx.req, ctx.res));
	
	app.put("/loginFinanceManager", 			
			ctx -> ctx.req.getRequestDispatcher("/reimbursements/financeManager").forward(ctx.req, ctx.res));
	
		//reset password -- implement that in the controller layer as well
	app.put("/resetPassword", ctx -> {
		if(checkAccess(ctx)) {
			ctx.json(authenticateController.resetPassword(ctx));
		}
		
	});
	
	//Finance Manager functions
	//Create employee account -- add this as a function in the webpage
	app.post("/createAccount", ctx -> {
		if(checkAccess(ctx)) {
			ctx.json(financeManController.createAccount(ctx));
		}
		
	});
	
	//Select all items
	app.get("/reimbursements/financeManager", ctx -> {
		if(checkAccess(ctx)) {
			ctx.json(financeManController.selectAllItems(ctx));
		}
	});
	
	//Set status
	app.put("/createAccount", ctx -> {
		if(checkAccess(ctx)) {
			ctx.json(financeManController.approveRequest(ctx));
		}
		
	});
	
	app.put("/loginEmployee", 			
			ctx -> ctx.req.getRequestDispatcher("/employeeRequest").forward(ctx.req, ctx.res));
	
			

}

}
