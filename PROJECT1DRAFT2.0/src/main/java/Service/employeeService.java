package Service;

import Models.Employee;
import Models.Reimburse;

import java.util.ArrayList;



public interface employeeService {
	
	
	public ArrayList <Employee> selectAccount(String first_name,String last_name);
	public boolean requestReimbursement(String username, String reimbursementType,int amount,String description);		
	public ArrayList<Reimburse> selectReimbursementStatus(String first_name, String last_name,int id);//don't exactly need this
	public boolean forgotPassword(String username, String password,int id);
	


}
