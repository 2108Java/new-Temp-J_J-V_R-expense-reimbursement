package Repository;

import Service.reimburseService;

public class reimburseServiceImpl implements  reimburseService {

	managerDataBaseDAO data;
public  reimburseServiceImpl(managerDataBaseDAO data) {
	this.data= data;
}
	public reimburseServiceImpl() {
	// TODO Auto-generated constructor stub
}
	public boolean managerApproveReimbursement( int id,  String approve) {
		return data.approveReimbursement(id,approve);
	}
	

}
