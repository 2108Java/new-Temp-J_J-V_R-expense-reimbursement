package Service;

import java.util.ArrayList;

import Models.Manager;
import Models.Reimburse;

public interface managerService {
	public ArrayList <Manager> selectAccount(String first_name,String last_name);//we don't have it
	public ArrayList <Reimburse> selectAllAccount();
	public ArrayList<Manager> selectReimbursementStatus(String first_name, String last_name);//we don't need this function
	public boolean createAccount(String first_name, String last_name,String title,String email);
	public boolean createCredentials(String username, String password);
	public boolean  createAccountReimbursement(int id, String last_name);
}
