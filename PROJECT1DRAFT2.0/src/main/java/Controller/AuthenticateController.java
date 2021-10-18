package Controller;

import io.javalin.http.Context;

public interface AuthenticateController {
	
	public boolean employeeLogin(Context ctx);
	
	public boolean managerLogin(Context ctx);
	
	public boolean resetPassword(Context ctx);

}
