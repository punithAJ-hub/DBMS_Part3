import java.sql.Date;

public class Bill {
	
	protected int billId;
	protected int orderId;
	protected double totalPrice;
	protected String status;
	protected String note;
	protected Date generated_on ;
	
	
	public int getOrderId() {
		return orderId;
	}



	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}



	public Date getGenerated_on() {
		return generated_on;
	}



	public void setGenerated_on(Date generated_on) {
		this.generated_on = generated_on;
	}



	public Date getPaid_on() {
		return paid_on;
	}



	public void setPaid_on(Date paid_on) {
		this.paid_on = paid_on;
	}
	protected Date paid_on;
	
	public Bill(int billId, int orderId, double totalPrice, String status, String note, Date generated_on , Date paid_on) {
		super();
		this.billId = billId;
		this.orderId = orderId;
		this.totalPrice = totalPrice;
		this.status = status;
		this.note = note;
		this.generated_on=generated_on;
		this.paid_on=paid_on;
	}
	
	
	
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public int getorderId() {
		return orderId;
	}
	public void setorderId(int orderId) {
		this.orderId = orderId;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
	
	

}
