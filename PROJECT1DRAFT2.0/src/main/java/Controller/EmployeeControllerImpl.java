package Controller;

import java.util.ArrayList;

import Models.Reimburse;
import Repository.authenticationImpl;
import Repository.employeeServiceImpl;
import Repository.reimburseServiceImpl;
import Service.authenticationService;
import Service.employeeService;
import Service.reimburseService;
import io.javalin.http.Context;

public class EmployeeControllerImpl implements EmployeeController{
	
	private static authenticationService authService = new authenticationImpl();
	private static employeeService empService = new employeeServiceImpl();
	private reimburseService reimbService = new reimburseServiceImpl();	
	
	public EmployeeControllerImpl(authenticationService authService, employeeService empService, reimburseService reimbService) {
		this.authService = authService;
		this.empService = empService;
		this.reimbService = reimbService;
	}

	public EmployeeControllerImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean submitRequest(Context ctx) {
		// TODO Auto-generated method stub
		String reimbType = ctx.formParam("reimbursement_Type");
		String amountString = ctx.formParam("amount_Reimbursed");
		int amount = Integer.parseInt(amountString);
		String description = ctx.formParam("Description");
		String username = ctx.sessionAttribute("access"); 
		boolean result = empService.requestReimbursement(username, reimbType, amount, description);//check if ne
		//if(result == true) {
			//ctx.res.setStatus(200);
			return result;
		//}else {
			//ctx.res.setStatus(404);//resource not found
			//return result;
		//}
		
		
	}

	@Override
	public ArrayList<Reimburse> viewRequestsByStatus(Context ctx) {
		// TODO Auto-generated method stub
		String status = ctx.formParam("");
		ArrayList<Reimburse> itemsByStatus = empService.selectReimbursementStatus();//don't exactly need this
		return itemsByStatus;
	}

	@Override
	public ArrayList<Reimburse> viewAllRequestsById(Context ctx) {
		// TODO Auto-generated method stub
		String username = ctx.sessionAttribute("access"); 
		ArrayList<Reimburse> allItems = empService.selectReimbursementsList(username);
		return allItems;
	}

}
