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
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/treeDAO")
public class TreeDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private QuoteDAO quoteDAO = new QuoteDAO();
	private HttpSession session=null;
	
	public TreeDAO(){}
	
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
    
    public void insertTree(Tree tree) throws SQLException {
    	connect_func("root","password");         
    	System.out.println("Inserting tree details to tree Table");
		String sql = "insert into Tree(quote_id, size, height, location, isNearHouse,note,tree_id) values (?,?,?,?,?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setInt(1,tree.getQuoteId());
			preparedStatement.setString(2, tree.getSize());
			preparedStatement.setDouble(3, tree.getHeight());
			preparedStatement.setString(4,tree.getLocation() );
			preparedStatement.setBoolean(5,tree.isNearHouse );
			preparedStatement.setString(6,tree.getNote() );
			preparedStatement.setInt(7,tree.getTreeId() );
		preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println("Done Inserting tree details to tree Table");
    }

    public boolean delete(int  quoteId) throws SQLException {
        String sql = "DELETE FROM Tree WHERE quote_id = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteId);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    
    
    public Tree getTree(int treeId) throws SQLException {
    	Tree tree = null;
        String sql = "SELECT * FROM Tree WHERE tree_id = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, treeId);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            int quoteId = resultSet.getInt("quote_id");
            String size = resultSet.getString("size");
            double height = resultSet.getDouble("height");
            String location = resultSet.getString("location");
            boolean isNearHouse = resultSet.getBoolean("isNearHouse");
            String note = resultSet.getString("note");
         
            tree = new Tree(treeId,quoteId,size,height,location,isNearHouse,note);
        }
         
        resultSet.close();
        statement.close();
         
        return tree;
    }
    
    public List<Tree> listTreesByQuote(int quoteId) throws SQLException {
    	Tree tree = null;
    	List<Tree> treeList = new ArrayList<Tree>();
        String sql = "SELECT * FROM Tree WHERE quote_id = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteId);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
        	int treeId = resultSet.getInt("tree_id");
            String size = resultSet.getString("size");
            double height = resultSet.getDouble("height");
            String location = resultSet.getString("location");
            boolean isNearHouse = resultSet.getBoolean("isNearHouse");
            String note = resultSet.getString("note");
         
            tree = new Tree(treeId,quoteId,size,height,location,isNearHouse,note);
            treeList.add(tree);
            
        }
         
        resultSet.close();
         
        return treeList;
    }
    
    
    
    public int generateId() throws SQLException {
    	int id = 0;
        String sql = "SELECT MAX(tree_id) as id FROM Tree";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            id = resultSet.getInt("id");
        }
         
        resultSet.close();
    
         
        return id+1;
    }

}
