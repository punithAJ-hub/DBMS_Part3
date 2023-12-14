import java.util.Date;

public class Quote{
	
	protected int quoteId;
	protected int clientId;
	protected Date requestedDate;
	protected String note;
	protected String status;
	protected double price;
	protected Date startDate;
	protected Date endDate;
	
	
	
	
	
	
	public Quote(int quoteId, int clientId, Date requestedDate, String note, String status, double price,
			Date startDate, Date endDate) {
		this(clientId,requestedDate,note,status,price,startDate,endDate);
		this.quoteId = quoteId;
		
	}
	
	
	public Quote( int clientId, Date requestedDate, String note, String status, double price,
			Date startDate, Date endDate) {
		this.clientId = clientId;
		this.requestedDate = requestedDate;
		this.note = note;
		this.status = status;
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public int getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public Date getRequestedDate() {
		return requestedDate;
	}
	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
	
	
	
	
	
}