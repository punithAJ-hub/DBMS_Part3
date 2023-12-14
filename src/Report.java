import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/report")
public class Report {
	
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
//	private userDAO clientDAO = new userDAO();
	
		public Report() {
			
		}
	
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
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    

    
    
    
    public List<Client> getEasyClients() throws SQLException{
    	
   	 	List<Client> clients = new ArrayList<>();       
        String sql = "select  distinct (q.client_id)  from quote_history q where q.quote_id not in (select  q.quote_id from quote_history q where q.status='negotiate' ) and q.status='accepted';";
        List<Integer> ids = new ArrayList<>();
        
        
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
        	int id =resultSet.getInt("client_id");
        	ids.add(id);
        }
        System.out.println("ids " + ids.size());
        
        resultSet.close();
        disconnect();  
        
        for( int id : ids) {
        Client client=new userDAO().getClientById(id);
        System.out.println("client in getEasy : " + client.getClientId());
        clients.add(client);
        }
        System.out.println("Easy clients : " + clients.toString());
        return clients;
   	
   }
    
public List<Client> getBigClients() throws SQLException{
    	
   	 	List<Client> clients = new ArrayList<>();
   	 	Client client  = null;
        String sql = "WITH ClientTreeCounts AS (\n"
        		+ "    SELECT\n"
        		+ "        C.client_id,\n"
        		+ "        C.first_name,\n"
        		+ "        C.last_name,\n"
        		+ "        C.email,\n"
        		+ "        C.phone_no,\n"
        		+ "        COUNT(T.tree_id) AS tree_count\n"
        		+ "    FROM\n"
        		+ "        Client C\n"
        		+ "    JOIN\n"
        		+ "        Quote Q ON C.client_id = Q.client_id\n"
        		+ "    JOIN\n"
        		+ "        Tree T ON Q.quote_id = T.quote_id\n"
        		+ "    GROUP BY\n"
        		+ "        C.client_id, C.first_name, C.last_name\n"
        		+ ")\n"
        		+ "SELECT\n"
        		+ "    CTC.client_id,\n"
        		+ "    CTC.first_name,\n"
        		+ "    CTC.last_name,\n"
        		+ "    CTC.tree_count,\n"
        		+ "    CTC.email,\n"
        		+ "    CTC.phone_no\n"
        		+ "FROM\n"
        		+ "    ClientTreeCounts CTC\n"
        		+ "WHERE\n"
        		+ "    CTC.tree_count = (SELECT MAX(tree_count) FROM ClientTreeCounts);";
        		
       
        
        
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
     
        	 while (resultSet.next()) {
             	String client_id =resultSet.getString("client_id");
                 String firstName =resultSet.getString("first_name");
                 String lastName =resultSet.getString("last_name");
                 String email =resultSet.getString("email");
                 long phoneNo = resultSet.getLong("phone_no");
                 String tree_count = resultSet.getString("tree_count");
                 client = new Client(client_id, firstName,lastName, null,email,phoneNo, tree_count);
                 clients.add(client);  
             }
        
       
        
        resultSet.close();
        disconnect();  
        
     
        return clients;
   	
   }
    
    
    
   public List<Quote> getOneTreeQuote() throws SQLException{
	   
	   List<Quote> quotes = new ArrayList<>();
	   
	   Quote quote = null;
	   String sql = "select t.quote_id,q.client_id, q.request_date,q.status,q.price,q.start_date,q.end_date,q.note from tree t join quote q on q.quote_id=t.quote_id group by t.quote_id  having count(t.quote_id)<2";
       
       connect_func();      
       statement = (Statement) connect.createStatement();
       ResultSet resultSet = statement.executeQuery(sql);
       
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
	   
	   return quotes;
   }
    
    public Client toClientList(ResultSet resultSet) throws SQLException{
    	Client client =null;
    	while (resultSet.next()) {
            String clientId = resultSet.getString("client_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            long phoneNo = Long.parseLong(resultSet.getString("phone_no"));
            client= new Client(clientId,firstName, lastName, null,email,phoneNo, null);
            
        }        
        resultSet.close();
             
        return client;
    	
    }
    
 public List<Client> getProspectiveClients() throws SQLException{
    	
   	 	List<Client> clients = new ArrayList<>(); 
   	 	Client client = null;
        String sql = "select * from client c where c.client_id not in (\n"
        		+ "select  q.client_id from client c  join quote q on c.client_id = q.client_id join orders o on q.quote_id = o.quote_id  where q.status='accepted' group by q.client_id);";
        
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
        	String client_id =resultSet.getString("client_id");
            String firstName =resultSet.getString("first_name");
            String lastName =resultSet.getString("last_name");
            String email =resultSet.getString("email");
            long phoneNo = resultSet.getLong("phone_no");
            String cardNumber = String.valueOf(resultSet.getString("creditcard_info"));
            System.out.println("creditcard : " + cardNumber);
            client = new Client(client_id, firstName,lastName, null,email,phoneNo, cardNumber);
            clients.add(client);  
        }
        
        return clients;
 }
 
 public List<Bill> getOverDueBills() throws SQLException{
 	
	 	List<Bill> bills = new ArrayList<>(); 
	 	Bill bill = null;
     String sql = "SELECT B.bill_id, B.order_id, B.total_amount, B.status, B.note, B.generated_on, B.paid_on\n"
     		+ "FROM Bill B\n"
     		+ "JOIN Orders O ON B.order_id = O.order_id\n"
     		+ "WHERE B.paid_on IS NULL\n"
     		+ "  AND B.generated_on IS NOT NULL\n"
     		+ "  AND CURRENT_DATE > DATE_ADD(B.generated_on, INTERVAL 7 DAY);";
     
     connect_func();      
     statement = (Statement) connect.createStatement();
     ResultSet resultSet = statement.executeQuery(sql);
     
     while (resultSet.next()) {
    	 int billId = resultSet.getInt("bill_id");
     	 int orderId = resultSet.getInt("order_id");
         String status = resultSet.getString("status");
         double totalPrice = resultSet.getDouble("total_Amount");
         String note = resultSet.getString("note");
         Date generated_on = Date.valueOf(resultSet.getString("generated_on"));
        
      
         bill = new Bill(billId,orderId,totalPrice,status,note, generated_on , null);
         bills.add(bill);
     }
     
     return bills;
}
 
 public List<Client> getBadClients() throws SQLException{
 	
	 	List<Client> clients = new ArrayList<>(); 
	 	Client client = null;
     String sql = "SELECT C.client_id, C.first_name, C.last_name, C.email , C.phone_no\n"
     		+ "FROM Client C\n"
     		+ "LEFT JOIN Quote Q ON C.client_id = Q.client_id\n"
     		+ "LEFT JOIN Orders O ON Q.quote_id = O.quote_id\n"
     		+ "LEFT JOIN Bill B ON O.order_id = B.order_id\n"
     		+ "WHERE B.generated_on is not null and B.paid_on is null and CURRENT_DATE > DATE_ADD(B.generated_on, INTERVAL 7 DAY);";
     		
     
     
     connect_func();      
     statement = (Statement) connect.createStatement();
     ResultSet resultSet = statement.executeQuery(sql);
     
     while (resultSet.next()) {
   
         	String client_id =resultSet.getString("client_id");
             String firstName =resultSet.getString("first_name");
             String lastName =resultSet.getString("last_name");
             String email =resultSet.getString("email");
             long phoneNo = resultSet.getLong("phone_no");
             client = new Client(client_id, firstName,lastName, null,email,phoneNo, null);
             clients.add(client);  
             
             System.out.println("client _id : " + client.getClientId());
         
     }
     
     
     resultSet.close();
     disconnect();  
     return clients;
	
}
 
 
 public List<Client> getGoodClients() throws SQLException{
	 	
	 	List<Client> clients = new ArrayList<>(); 
	 	Client client = null;
  String sql = "SELECT C.client_id , C.first_name , C.last_name , C.email , C.phone_no\n"
  		+ "FROM Client C\n"
  		+ " JOIN Quote Q ON C.client_id = Q.client_id\n"
  		+ " JOIN Orders O ON Q.quote_id = O.quote_id\n"
  		+ " JOIN Bill B ON O.order_id = B.order_id\n"
  		+ "WHERE B.generated_on IS NOT NULL\n"
  		+ "  AND B.paid_on IS NOT NULL\n"
  		+ "  AND B.paid_on <= DATE_ADD(B.generated_on, INTERVAL 1 DAY);";
  		
  
  
  connect_func();      
  statement = (Statement) connect.createStatement();
  ResultSet resultSet = statement.executeQuery(sql);
  
  while (resultSet.next()) {

      	String client_id =resultSet.getString("client_id");
          String firstName =resultSet.getString("first_name");
          String lastName =resultSet.getString("last_name");
          String email =resultSet.getString("email");
          long phoneNo = resultSet.getLong("phone_no");
          client = new Client(client_id, firstName,lastName, null,email,phoneNo, null);
          clients.add(client);  
          
          System.out.println("client _id : " + client.getClientId());
      
  }
  
  
  
  
  resultSet.close();
  disconnect();  
  return clients;
	
}
 
 
 public List<Tree> getHighestTree() throws SQLException{
	 	
	 	List<Tree> trees = new ArrayList<>(); 
	 	Tree tree = null;
	 	String sql = "WITH RankedTrees AS (\n"
		+ "    SELECT\n"
		+ "        T.tree_id,\n"
		+ "        T.size,\n"
		+ "        T.height,\n"
		+ "        RANK() OVER (ORDER BY T.height DESC) AS tree_rank\n"
		+ "    FROM\n"
		+ "        Tree T\n"
		+ "    WHERE\n"
		+ "        T.isNearHouse = TRUE\n"
		+ ")\n"
		+ "SELECT\n"
		+ "    RT.tree_id,\n"
		+ "    RT.size,\n"
		+ "    RT.height\n"
		+ "FROM\n"
		+ "    RankedTrees RT\n"
		+ "WHERE\n"
		+ "    RT.tree_rank = 1;\n";
	
		


connect_func();      
statement = (Statement) connect.createStatement();
ResultSet resultSet = statement.executeQuery(sql);

while (resultSet.next()) {

   		int tree_id =resultSet.getInt("tree_id");
        String size =resultSet.getString("size");
        double height =resultSet.getDouble("height");
      
       tree = new Tree(tree_id, 0, size, height , "",false,"");
       trees.add(tree);  
 
}

resultSet.close();
disconnect();  
return trees;
	
}
 
 
 public List<Statistics> getStats() throws SQLException{
 
	 List<Statistics> stats = new ArrayList<>();
	 Statistics stat = null;
	 
	 String sql = "SELECT\n"
	 		+ "    C.client_id, C.first_name, C.last_name,\n"
	 		+ "    COUNT(T.tree_id) AS total_trees,\n"
	 		+ "    SUM(B.total_amount) AS total_paid_amount,\n"
	 		+ "    Q.end_date as workDoneDate\n"
	 		+ "    \n"
	 		+ "FROM\n"
	 		+ "    Client C\n"
	 		+ "JOIN\n"
	 		+ "    Quote Q ON C.client_id = Q.client_id\n"
	 		+ "JOIN\n"
	 		+ "    Orders O ON Q.quote_id = O.quote_id\n"
	 		+ "JOIN\n"
	 		+ "    Tree T ON Q.quote_id = T.quote_id\n"
	 		+ "LEFT JOIN\n"
	 		+ "    Bill B ON O.order_id = B.order_id\n"
	 		+ "WHERE\n"
	 		+ "    B.generated_on is not null and B.paid_on is not null\n"
	 		+ "GROUP BY\n"
	 		+ "    C.client_id , T.tree_id , q.end_date;";
	 
	 connect_func();      
	 statement = (Statement) connect.createStatement();
	 ResultSet resultSet = statement.executeQuery(sql);

	 while (resultSet.next()) {
		 		String first_name = resultSet.getString("first_name");
		 		String last_name = resultSet.getString("last_name");
	    		int client_id =resultSet.getInt("client_id");
	    		int total_trees =Integer.parseInt(resultSet.getString("total_trees"));
	    		double total_amount_paid =resultSet.getDouble("total_paid_amount");
	    		Date workDoneDate = Date.valueOf(resultSet.getString("workDoneDate"));
	       
	        stat = new Statistics(client_id ,first_name ,last_name ,total_trees ,0.0 , total_amount_paid , workDoneDate );
	        stats.add(stat);  
	  
	 }

	 resultSet.close();
	 disconnect();  
	 return stats;
 }
 
    

}
