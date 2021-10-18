package Controller;

import java.util.ArrayList;
import java.util.List;

import Models.Reimburse;
import io.javalin.http.Context;

public interface EmployeeController {
	
	public boolean submitRequest(Context ctx);
	
	public ArrayList<Reimburse> viewRequestsByStatus(Context ctx);
	
	public ArrayList<Reimburse> viewAllRequestsById(Context ctx);

}
