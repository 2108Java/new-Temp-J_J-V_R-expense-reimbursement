package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import Models.Authentication;
import Models.Employee;
import Util.connectionFactory;
public class employeeDataBaseDAOImpl implements employeeDataBaseDAO {
	
	Logger logger = Logger.getLogger(employeeDataBaseDAOImpl.class);
	connectionFactory conFact = new connectionFactory();


	public boolean selectUser(String username, String password) {
		ArrayList<Authentication> array = new ArrayList<Authentication>();
		boolean success = false;
		try {
			Connection connection = conFact.getConnection();
			String sql = "select *\r\n"
					+ "from employeecredentials\r\n"
					+ "where username = ?\r\n"
					+ "and \"password\"  = ?;";
			PreparedStatement ps =connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				array.add(new Authentication(rs.getString("username"),
						rs.getString("password")));
			}
			success =true;
			
			logger.info("Employee is authenticated");
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return success;
	}


	public ArrayList <Employee> selectAccount(String first_name, String last_name) {
		ArrayList <Employee> array = new ArrayList <Employee>();
		try {
			Connection connection = conFact.getConnection();
			String sql = "select *\r\n"
					+ "from employee e \r\n"
					+ "where first_name  =? and last_name  = ?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1,first_name);
			ps.setString(2, last_name);
		
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				array.add(new Employee(rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("title"),
						rs.getInt("id")));
				logger.info("Employee Selected specific account");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return array;
	}

	
	public boolean requestReimbursement( int id, String reimbursementType,double amount,String description) {
		boolean success  = false;

		try {
			Connection connection = conFact.getConnection();
			String sql = "update reimbursement \r\n"
					+ "set reimbursement_type = ?, amount_reimbursed =?,description=?,request_date_time = current_timestamp \r\n"
					+ "where reimbursement_id = ?;";
			PreparedStatement ps = connection.prepareStatement(sql);
	
		
			ps.setString(1,reimbursementType);
			ps.setDouble(2, amount);
			ps.setString(3,description);
			ps.setInt(4, id);
			ps.execute();
			success = true;
			logger.info("Employee requested a reimbursement");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return success;
	}

	

	public ArrayList<Employee> selectReimbursementStatus(String first_name,String last_name, int id) {
	
		ArrayList<Employee> array = new ArrayList <Employee>();
		try {
			Connection connection = conFact.getConnection();
			String sql = "Select reimbursement_type, approved From reimbursement where R_id =?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				array.add(new Employee(rs.getString("request_Reimbursement"),
						rs.getString("reimbursement_Type"),
						rs.getString("Approved"),
						rs.getInt("R_id")));
				logger.info("Employee viewed reimbursement status");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return array;
	}



	public boolean createEmployeeCredentials(String username, String password) {
		boolean success = false;

		try {
			Connection connection = conFact.getConnection();
			String sql= "Insert into employeeCredentials(username,password) values(?,?);";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.execute();
			 success = true;
			 logger.warn("Employee credentials were created");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}


	public boolean forgotPassword(String username,String password,int id) {
		boolean success = false;

		try {
			Connection connection = conFact.getConnection();
			String sql= "Update employeeCredentials Set username=?, password=? where credential_id =?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setInt(3, id);
			ps.execute();
			 success = true;
			 logger.warn("Employee credentials were updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}






}
