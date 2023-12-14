import java.sql.Date;

public class Orders {
	
	protected int orderId;
	protected int clientId;
	protected int quoteId;
	protected Date startDate;
	protected Date endDate;
	protected double totalPrice;
	

	
public Orders(int orderId, int clientId, int quoteId, Date startDate, Date endDate, double totalPrice) {
		this(clientId,quoteId,startDate,endDate,totalPrice);
		this.orderId = orderId;
		
	}
	
	
	
public Orders(int clientId, int quoteId, Date startDate, Date endDate, double totalPrice) {

	this.clientId = clientId;
	this.quoteId = quoteId;
	this.startDate = startDate;
	this.endDate = endDate;
	this.totalPrice = totalPrice;
}
	

	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
	
	

}
