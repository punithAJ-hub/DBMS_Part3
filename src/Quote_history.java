import java.sql.Date;

public class Quote_history{
		protected int id;
		protected int quoteId;
		protected int clientId;
		protected String last_modified;
		protected String note;
		
		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getLast_modified() {
			return last_modified;
		}


		public void setLast_modified(String last_modified) {
			this.last_modified = last_modified;
		}
		protected String status;
		protected double price;
		protected Date startDate;
		protected Date endDate;
		
		
		
		public Quote_history(int id,int quoteId, int clientId, String last_modified, String note, String status, double price,
				Date startDate, Date endDate) {
			
			this(quoteId,clientId,last_modified,note,status,price,startDate,endDate);
			this.id=id;
			
		}
		
		
		public Quote_history( int quoteId,int clientId, String last_modified, String note, String status, double price,
				Date startDate, Date endDate) {
			
			this.quoteId=quoteId;
			this.clientId = clientId;
			this.last_modified = last_modified;
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
		public String getRequestedDate() {
			return last_modified;
		}
		public void setRequestedDate(String last_modified) {
			this.last_modified = last_modified;
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