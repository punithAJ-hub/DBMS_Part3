import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/quoteDAO")

public class QuoteDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public QuoteDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false&user=root&password=password");
            System.out.println(connect);
        }
    }
    
    public boolean database_login(String userName, String password) throws SQLException{
    	try {
    		connect_func("root","password");
    		String sql = "select * from user where user_name = ?";
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, userName);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
    	}
    }
	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/testdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    public List<Tree> listAllTree() throws SQLException {
        List<Tree> listTree = new ArrayList<Tree>();        
        String sql = "SELECT * FROM Tree";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int treeId = resultSet.getInt("tree_id");
            int  quoteId = resultSet.getInt("client_id");
            String size = resultSet.getString("last_name");
            double height = resultSet.getDouble("height");
            String location = resultSet.getString("location");
            boolean isNearHouse = resultSet.getBoolean("isNearHouse");
            String note = resultSet.getString("note");
            

             
            Tree tree = new Tree(treeId,quoteId,size,height,location,isNearHouse,note);
            listTree.add(tree);
        }        
        resultSet.close();
        disconnect();        
        return listTree;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insertQuote(Quote quote) throws SQLException {
    	connect_func("root","password");         
    	System.out.println("Inserting Quote details to Quote Table");
		String sql = "insert into Quote(quote_id, client_id, request_date, note, status,price,start_date,end_date) values (?,?,?,?,?,?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			System.out.println("schema :" + connect.getSchema());
			preparedStatement.setInt(1,quote.getQuoteId());
			preparedStatement.setInt(2, quote.getClientId());
			preparedStatement.setDate(3, (Date) quote.getRequestedDate());
			preparedStatement.setString(4,quote.getNote() );
			preparedStatement.setString(5,quote.getStatus() );
			preparedStatement.setDouble(6,quote.getPrice());
			preparedStatement.setDate(7,(Date)quote.getStartDate());
			preparedStatement.setDate(8,(Date)quote.getEndDate());
		preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect(); 
        System.out.println("Done Inserting Quote details to Quote Table");
    }

    
    
    public void insertQuoteHistory(Quote_history quotehistory) throws SQLException {
    	connect_func("root","password");         
    	System.out.println("Inserting Quote_history details to Quote_history Table");
		String sql = "insert into Quote_history(id,quote_id, client_id, last_updated, note, status,price,start_date,end_date) values (?,?,?,?,?,?,?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			System.out.println("schema :" + connect.getSchema());
			System.out.println("id of history" + quotehistory.getId());
			preparedStatement.setInt(1,quotehistory.getId());
			preparedStatement.setInt(2,quotehistory.getQuoteId());
			preparedStatement.setInt(3, quotehistory.getClientId());
			preparedStatement.setString(4, quotehistory.getRequestedDate());
			preparedStatement.setString(5,quotehistory.getNote() );
			preparedStatement.setString(6,quotehistory.getStatus() );
			preparedStatement.setDouble(7,quotehistory.getPrice());
			preparedStatement.setDate(8,Date.valueOf(quotehistory.getStartDate().toString()));
			preparedStatement.setDate(9,Date.valueOf(quotehistory.getEndDate().toString()));
		preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect(); 
        System.out.println("Done Inserting Quote_history details to Quote_history Table");
    }
    public boolean delete(int  quoteId) throws SQLException {
        String sql = " DELETE FROM Quote WHERE quote_id = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteId);
     
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(Quote quote) throws SQLException {
        String sql = "update Quote set price = ?, start_date=?, end_date =?,note = ? , status=? where quote_id = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setDouble(1, quote.getPrice());
        System.out.println("Date in update DAO : " + Date.valueOf(quote.getStartDate().toString()));
		preparedStatement.setDate(2, Date.valueOf(quote.getStartDate().toString()) );
		preparedStatement.setDate(3, Date.valueOf(quote.getEndDate().toString()));
		preparedStatement.setString(4, quote.getNote());
		preparedStatement.setString(5, quote.getStatus());
		preparedStatement.setInt(6, quote.getQuoteId());
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public boolean updateStatus(int quoteId,String status) throws SQLException {
        String sql = "update Quote set status = ? where quote_id = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, status);
		preparedStatement.setInt(2, quoteId);
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public boolean negotiate(int quoteId,String status,String note) throws SQLException {
        String sql = "update Quote set status = ? , note = ? where quote_id = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, status);
		preparedStatement.setString(2, note);
		preparedStatement.setInt(3, quoteId);
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    
  
    public int generateId() throws SQLException {
    	int id = 0;
        String sql = "SELECT MAX(quote_id) as id FROM Quote";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            id = resultSet.getInt("id");
        }
         
        resultSet.close();
    
         
        return id;
    }
    
    public int generateIdQhistory() throws SQLException {
    	int id = 0;
        String sql = "SELECT MAX(id) as ids FROM Quote_history";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            id = resultSet.getInt("ids");
        }
         
        resultSet.close();
    
         
        return id;
    }
    
    public boolean isValid(int quoteId) throws SQLException {
    	int id =0;
    	String sql = "SELECT MAX(quote_id) as id FROM Quote";
        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            id = resultSet.getInt("id");
        }
         
        resultSet.close();
    	return id==quoteId;
    }
    
    
    public List<Quote> getQuotesByStatus(String status) throws SQLException{
    	List<Quote> quotes= new ArrayList<>();
    	Quote quote = null;
    	
    	String sql = "SELECT * from Quote where status = ?";
    	
        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, status);
         
        ResultSet resultSet = preparedStatement.executeQuery();
        
         
        while (resultSet.next()) {
        	int quoteId = resultSet.getInt("quote_id");
        	int clientId = resultSet.getInt("client_id");
            String note = resultSet.getString("note");
            Date requestDate = Date.valueOf(resultSet.getString("request_date"));
            Date startDate = Date.valueOf(resultSet.getString("start_date"));
            Date endDate = Date.valueOf(resultSet.getString("end_date"));
            double price = resultSet.getDouble("price");
            String qstatus = resultSet.getString("status");
            quote= new Quote(quoteId,clientId,requestDate,note,qstatus,price,startDate,endDate);
            quotes.add(quote);
            
        }
         
        resultSet.close();
		return quotes;
    	
    }
    
    public List<Quote_history> getQuotesHistory() throws SQLException{
    	List<Quote_history> quotes= new ArrayList<>();
    	Quote_history quote = null;
    	
    	String sql = "SELECT * from Quote_history";
    	
        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
       
         
        ResultSet resultSet = preparedStatement.executeQuery();
        
         
        while (resultSet.next()) {
        	int id = resultSet.getInt("id");
        	int quoteId = resultSet.getInt("quote_id");
        	int clientId = resultSet.getInt("client_id");
            String note = resultSet.getString("note");
            String requestDate = resultSet.getString("last_updated");
            Date startDate = Date.valueOf(resultSet.getString("start_date").toString());
            Date endDate = Date.valueOf(resultSet.getString("end_date").toString());
            double price = resultSet.getDouble("price");
            String qstatus = resultSet.getString("status");
            quote= new Quote_history(id,quoteId,clientId,requestDate,note,qstatus,price,startDate,endDate);
            quotes.add(quote);
            
        }
         
        resultSet.close();
		return quotes;
    	
    }
    public List<Quote> getQuotesByStatus(String status, int clientId) throws SQLException{
    	List<Quote> quotes= new ArrayList<>();
    	Quote quote = null;
    	
    	String sql = "SELECT * from Quote where status = ? and client_id = ?";
    	
        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, status);
        preparedStatement.setInt(2, clientId);
         
        ResultSet resultSet = preparedStatement.executeQuery();
        
         
        while (resultSet.next()) {
        	int quoteId = resultSet.getInt("quote_id");
//        	int clientId = resultSet.getInt("client_id");
            String note = resultSet.getString("note");
            Date requestDate = Date.valueOf(resultSet.getString("request_date"));
            Date startDate = Date.valueOf(resultSet.getString("start_date"));
            Date endDate = Date.valueOf(resultSet.getString("end_date"));
            double price = resultSet.getDouble("price");
            String qstatus = resultSet.getString("status");
            quote= new Quote(quoteId,clientId,requestDate,note,qstatus,price,startDate,endDate);
            quotes.add(quote);
            
        }
         
        resultSet.close();
		return quotes;
    	
    }
    
    public List<Quote> getQuotesNegOrReq() throws SQLException{
    	List<Quote> quotes= new ArrayList<>();
    	Quote quote = null;
    	
    	String sql = "SELECT * from Quote where status IN ('requested','negotiate')";
    	
        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        
         
        ResultSet resultSet = preparedStatement.executeQuery();
        
         
        while (resultSet.next()) {
        	int quoteId = resultSet.getInt("quote_id");
        	int clientId = resultSet.getInt("client_id");
            String note = resultSet.getString("note");
            Date requestDate = Date.valueOf(resultSet.getString("request_date").toString());
            Date startDate = Date.valueOf(resultSet.getString("start_date").toString());
            Date endDate = Date.valueOf(resultSet.getString("end_date").toString());
            double price = resultSet.getDouble("price");
            String qstatus = resultSet.getString("status");
            quote= new Quote(quoteId,clientId,requestDate,note,qstatus,price,startDate,endDate);
            quotes.add(quote);
            
        }
        
         
        resultSet.close();
        
        System.out.println("Length : " + quotes.size());
		return quotes;
    	
    }
    
    
    
    public List<Quote> getQuotesByclientId(int  clientId) throws SQLException{
    	List<Quote> quotes= new ArrayList<>();
    	Quote quote = null;
    	
    	String sql = "SELECT * from Quote where client_id = ?";
    	
        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, clientId);
         
        ResultSet resultSet = preparedStatement.executeQuery();
        
         
        while (resultSet.next()) {
        	int quoteId = resultSet.getInt("quote_id");
            String note = resultSet.getString("note");
            Date requestDate = Date.valueOf(resultSet.getString("request_date").toString());
            Date startDate = Date.valueOf(resultSet.getString("start_date").toString());
            Date endDate = Date.valueOf(resultSet.getString("end_date").toString());
            double price = resultSet.getDouble("price");
            String qstatus = resultSet.getString("status");
            quote= new Quote(quoteId,clientId,requestDate,note,qstatus,price,startDate,endDate);
            quotes.add(quote);
            
        }
         
        resultSet.close();
		return quotes;
    	
    }
    
    

}
