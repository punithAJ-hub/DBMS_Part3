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
@WebServlet("/ordersDAO")
public class OrdersDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public OrdersDAO(){}
	
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
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/userdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    public List<Orders> listAllOrders() throws SQLException {
        List<Orders> listOrders = new ArrayList<Orders>();        
        String sql = "SELECT * FROM Orders";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int orderId = resultSet.getInt("order_id");
            int  clientId = resultSet.getInt("client_id");
            int  quoteId = resultSet.getInt("quote_id");
            Date startDate = Date.valueOf(resultSet.getString("start_date"));
            Date endDate = Date.valueOf(resultSet.getString("end_date"));
            double totalPrice = resultSet.getDouble("total_Amount");
           
            Orders order = new Orders(orderId,clientId,quoteId,startDate,endDate,totalPrice);
            listOrders.add(order);
        }        
        resultSet.close();
        disconnect();        
        return listOrders;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insertOrders(Orders order) throws SQLException {
    	connect_func("root","password");         
    	System.out.println("Inserting tree details to Order Table");
		String sql = "insert into Orders(order_id,quote_id, client_id,total_Amount,start_date,end_date) values (?,?,?,?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1,order.getOrderId());	
		preparedStatement.setInt(2,order.getQuoteId());
			preparedStatement.setInt(3, order.getClientId());
			preparedStatement.setDouble(4, order.getTotalPrice());
			preparedStatement.setDate(5,Date.valueOf(order.getStartDate().toString()));
			preparedStatement.setDate(6,Date.valueOf(order.getEndDate().toString()));
		preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println("Done Inserting order details to orders Table");
    }

    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM User WHERE email = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(Quote quote) throws SQLException {
        String sql = "update Quote set price = ?, start_date=?, end_date =?,note = ? , status=? where quote_id = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setDouble(1, quote.getPrice());
		preparedStatement.setDate(2, Date.valueOf(quote.getStartDate().toString()) );
		preparedStatement.setDate(3, Date.valueOf(quote.getEndDate().toString()));
		preparedStatement.setString(4, quote.getNote());
		preparedStatement.setString(5, quote.getStatus());
		preparedStatement.setInt(6, quote.getQuoteId());
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
   
  
    public int generateId() throws SQLException {
    	int id = 0;
        String sql = "SELECT MAX(order_id) as id FROM Orders";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            id = resultSet.getInt("id");
        }
         
        resultSet.close();
    
         
        return id;
    }
    
    
    
  
    

    
    

}
