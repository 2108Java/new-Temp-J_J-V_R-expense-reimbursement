package Controller;

import java.util.ArrayList;
import java.util.List;

import Models.Reimburse;
import io.javalin.http.Context;

public interface FinanceManagerController {
	
public boolean createAccount(Context ctx);
	
	public ArrayList<Reimburse> selectAllItems(Context ctx);
	
	public ArrayList<Reimburse> selectByCategory(Context ctx);
	
	public boolean approveRequest(Context ctx);

}
