package Controller;

import java.util.ArrayList;

import Models.Reimburse;
import Repository.authenticationImpl;
import Repository.managerServiceImpl;
import Repository.reimburseServiceImpl;
import Service.authenticationService;
import Service.managerService;
import Service.reimburseService;
import io.javalin.http.Context;

public class FinanceManagerControllerImpl implements FinanceManagerController{
	
	private static authenticationService authService = new authenticationImpl();
	private static managerService manageService = new managerServiceImpl();
	private reimburseService reimbService = new reimburseServiceImpl();	
	
	public FinanceManagerControllerImpl(authenticationService authService, managerService manageService, reimburseService reimbService) {
		this.authService = authService;
		this.manageService = manageService;
		this.reimbService = reimbService;
	}

	public FinanceManagerControllerImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean createAccount(Context ctx) {
		// TODO Auto-generated method stub
		//Tell Vishaun about merging the functions
		String firstname = ctx.formParam("first_name");
		String lastname = ctx.formParam("last_name");
		String title = ctx.formParam("title");
		String email = ctx.formParam("email");
		String username = ctx.formParam("username");
		String tempPass = ctx.formParam("tempPassword");
		boolean b1 = manageService.createCredentials(username, tempPass);
		boolean b2 = manageService.createAccount(firstname, lastname, title, email);//id should not be an argument -- tell Vishaun
		if (b1 == true && b2 ==true) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public ArrayList<Reimburse> selectAllItems(Context ctx) {
		// TODO Auto-generated method stub
		ArrayList<Reimburse> allItems = manageService.selectAllAccount();
		return allItems;
	}

	@Override
	public ArrayList<Reimburse> selectByCategory(Context ctx) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public boolean approveRequest(Context ctx) {
		// TODO Auto-generated method stub
		String stringid = ctx.formParam("reimbursement_id");
		int id = Integer.parseInt(stringid);
		String status = ctx.formParam("status");
		//PASS IN THE PRIMARY KEY FOR THE METHOD
		return reimbService.managerApproveReimbursement(id, status);//add parameters
		
	}

}
