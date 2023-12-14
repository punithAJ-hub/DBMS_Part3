import java.sql.Date;

public class Statistics {
	
	//client, list the total numbers of trees, total due amount, total paid amount,
	//and the date when the work is done for each tree. Only list the trees that have been cut
	
	
	private int client_id;
	private String first_name;
	private String last_name;
	private int total_trees;
	private double amount_due;
	private double amount_paid;
	private Date workDoneDate;
	
	
	
	public Statistics(int client_id, String first_name, String last_name, int total_trees, double amount_due, double amount_paid, Date workDoneDate) {
		super();
		this.client_id = client_id;
		this.total_trees = total_trees;
		this.amount_due = amount_due;
		this.amount_paid = amount_paid;
		this.workDoneDate = workDoneDate;
		this.first_name= first_name;
		this.last_name= last_name;
	}
	
	
	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public int getTotal_trees() {
		return total_trees;
	}
	public void setTotal_trees(int total_trees) {
		this.total_trees = total_trees;
	}
	public double getAmount_due() {
		return amount_due;
	}
	public void setAmount_due(double amount_due) {
		this.amount_due = amount_due;
	}
	public double getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(double amount_paid) {
		this.amount_paid = amount_paid;
	}
	public Date getWorkDoneDate() {
		return workDoneDate;
	}
	public void setWorkDoneDate(Date workDoneDate) {
		this.workDoneDate = workDoneDate;
	}
	
	
	

}
