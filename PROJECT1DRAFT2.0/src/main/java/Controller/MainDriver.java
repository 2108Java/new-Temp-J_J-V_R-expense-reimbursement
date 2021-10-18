package Controller;

import org.apache.log4j.Logger;

import Repository.employeeDataBaseDAOImpl;
import io.javalin.Javalin;
import jdk.internal.org.jline.utils.Log;

public class MainDriver {
public static void main(String[] args) {
	Logger logger = Logger.getLogger(MainDriver.class);
	logger.info("So it begins");
	
	 //PropertiesParser.getProperties(); 
	   
	   
	   Javalin app = Javalin.create(config -> config.addStaticFiles(
				staticFiles ->
				{
					staticFiles.directory = "/public";
				}
				)).start(8000);
	   
	   //We've got connection to our html files 
	   //We need to connect our Java functionality 
	   
	   
	   //Servlets are what handle Http REquests and Responses directly. 
	   //Javalin abstracts away the process of creating and connecting our servlets. 
	   //Setting up a connection endpoints 
	   
	   RequestHandler.setupEndPoints(app);

}
}
