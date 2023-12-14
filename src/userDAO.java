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
@WebServlet("/userDAO")
public class userDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public userDAO() {
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	protected void connect_func() throws SQLException {
		// uses default connection to the database
		if (connect == null || connect.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			connect = (Connection) DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/testdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false&user=root&password=password");
			System.out.println(connect);
		}
	}

	public boolean database_login(String userName, String password) throws SQLException {
		try {
			connect_func("root", "password");
			String sql = "select * from user where user_name = ?";
			preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			ResultSet rs = preparedStatement.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.out.println("failed login");
			return false;
		}
	}

	// connect to the database
	public void connect_func(String username, String password) throws SQLException {
		if (connect == null || connect.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			connect = (Connection) DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/userdb?" + "useSSL=false&user=" + username + "&password=" + password);
			System.out.println(connect);
		}
	}

	public List<user> listAllUsers() throws SQLException {
		List<user> listUser = new ArrayList<user>();
		String sql = "SELECT * FROM User";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			String userName = resultSet.getString("user_name");
			String firstName = resultSet.getString("first_name");
			String lastName = resultSet.getString("last_name");
			String password = resultSet.getString("password");
			String userRole = resultSet.getString("user_role");

			user users = new user(userName, firstName, lastName, password, userRole);
			listUser.add(users);
		}
		resultSet.close();
		disconnect();
		return listUser;
	}

	protected void disconnect() throws SQLException {
		if (connect != null && !connect.isClosed()) {
			connect.close();
		}
	}

	public void insert(user users) throws SQLException {
		connect_func("root", "password");
		String sql = "insert into User(user_name, first_name, last_name, password, user_role) values (?,?,?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, users.getUserName());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getUserRole());

		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	public void insertClient(Client client) throws SQLException {
		connect_func("root", "password");
		System.out.println("Starting to insert data to Client");
		int id = generateId();
		String sql = "insert into Client(first_name, last_name, address, email,phone_no,creditcard_info,client_id) values (?,?,?,?,?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, client.getFirstName());
		preparedStatement.setString(2, client.getLastName());
		preparedStatement.setString(3, client.getAddress());
		preparedStatement.setString(4, client.getEmail());
		preparedStatement.setString(5, String.valueOf(client.getPhoneNo()));
		preparedStatement.setString(6, client.getCredit_card_info());
		preparedStatement.setInt(7, id);

		preparedStatement.executeUpdate();
		System.out.println("Done inserting data to Client");
		preparedStatement.close();

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

	public boolean update(user users) throws SQLException {
		String sql = "update User set user_name = ?, first_name=?, last_name =?,password = ? , user_role=? where user_name = ?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, users.getUserName());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getUserRole());

		boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		return rowUpdated;
	}

	public user getUser(String userName) throws SQLException {
		user user = null;
		String sql = "SELECT * FROM User WHERE user_name = ?";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, userName);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			String password = resultSet.getString("password");
			String userRole = resultSet.getString("user_role");
			user = new user(userName, firstName, lastName, password, userRole);
		}

		resultSet.close();
		statement.close();

		return user;
	}

	public int getClientId(String userName) throws SQLException {
		int clientId = 0;
		String sql = "SELECT client_id FROM Client WHERE email = ?";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, userName);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			clientId = resultSet.getInt("client_id");

		}

		resultSet.close();
		statement.close();

		return clientId;
	}

	public Client getClientById(int id) throws SQLException {
		Client client = null;
		String sql = "SELECT * FROM Client WHERE client_id = ?";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			String client_id = resultSet.getString("client_id");
			System.out.println("client_id in while loop :" + client_id);
			String firstName = resultSet.getString("first_name");
			String lastName = resultSet.getString("last_name");
			String email = resultSet.getString("email");
			long phoneNo = resultSet.getLong("phone_no");
			client = new Client(client_id, firstName, lastName, null, email, phoneNo, "122222");

			System.out.println("client outside : " + client.getClientId());
			return client;
		}
		return null;

	}

	public boolean checkUserName(String userName) throws SQLException {
		boolean checks = false;
		String sql = "SELECT * FROM User WHERE user_name = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, userName);
		ResultSet resultSet = preparedStatement.executeQuery();

		System.out.println(checks);

		if (resultSet.next()) {
			checks = true;
		}

		System.out.println(checks);
		return checks;
	}

	public boolean checkPassword(String password) throws SQLException {
		boolean checks = false;
		String sql = "SELECT * FROM User WHERE password = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, password);
		ResultSet resultSet = preparedStatement.executeQuery();

		System.out.println(checks);

		if (resultSet.next()) {
			checks = true;
		}

		System.out.println(checks);
		return checks;
	}

	public boolean isValid(String userName, String password) throws SQLException {
		String sql = "SELECT * FROM User";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		resultSet.last();

		int setSize = resultSet.getRow();
		resultSet.beforeFirst();

		for (int i = 0; i < setSize; i++) {
			resultSet.next();
			if (resultSet.getString("user_name").equals(userName) && resultSet.getString("password").equals(password)) {
				return true;
			}
		}
		return false;
	}

	public String isClient(String userName, String password) throws SQLException {
		System.out.println("Client verification");
		String sql = "SELECT * FROM User";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		resultSet.last();

		int setSize = resultSet.getRow();
		resultSet.beforeFirst();
		String firstName = null;

		for (int i = 0; i < setSize; i++) {
			resultSet.next();
			if (resultSet.getString("user_name").equals(userName) && resultSet.getString("password").equals(password)
					&& resultSet.getString("user_role").equalsIgnoreCase("Clients")) {
				firstName = resultSet.getString("first_name");
			}
		}

		return firstName;
	}

	public int generateId() throws SQLException {
		int id = 0;
		String sql = "SELECT MAX(client_id) as id FROM Client";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			id = resultSet.getInt("id");
		}

		resultSet.close();

		return id + 1;
	}

	public void init() throws SQLException, FileNotFoundException, IOException {
		connect_func();
		statement = (Statement) connect.createStatement();

		String[] INITIAL = {

				"use testdb; ", "SET FOREIGN_KEY_CHECKS=0;", "delete from  User;", "delete from Quote_history;",
				"delete from Bill;", "delete from Orders;", "delete from Quote;", "delete from Client;",
				"delete from Tree;"

		};

		String[] TUPLES = { ("insert into User(user_name, first_name, last_name, password, user_role)"
				+ "values ('susie@gmail.com', 'Susie ', 'Guzman', 'susie1234','clients'),"
				+ "('don@gmail.com', 'Don', 'Cummings','don123','clients'),"
				+ "('margarita@gmail.com', 'Margarita', 'Lawson','margarita1234','clients'),"
				+ "('jo@gmail.com', 'Jo', 'Brady','jo1234','clients'),"
				+ "('wallace@gmail.com', 'Wallace', 'Moore','wallace1234', 'clients'),"
				+ "('amelia@gmail.com', 'Amelia', 'Phillips','amelia1234','clients' ),"
				+ "('sophie@gmail.com', 'Sophie', 'Pierce','sophie1234','clients' ),"
				+ "('angelo@gmail.com', 'Angelo', 'Francis','angelo1234','clients' ),"
				+ "('rudy@gmail.com', 'Rudy', 'Smith','rudy1234','clients' ),"
				+ "('jeannette@gmail.com', 'Jeannette ', 'Stone','jeannette1234','clients' ),"
				+ "('root', 'default', 'default','pass1234', 'Admin root');") };

		String[] CLIENTS = {
				"INSERT INTO Client(client_id, first_name, last_name, address, phone_no, email, creditcard_info) VALUES "
						+ "(1, 'John', 'Doe', '123 Main St, Detroit, USA', '5555555555', 'john@gmail.com', '************1234'), "
						+ "(2, 'Jane', 'Smith', '456 Elm St, Flint, USA', '5551234567', 'jane@gmail.com', '************5678'), "
						+ "(3, 'Bob', 'Johnson', '789 Oak St, Chicago, USA', '5559876543', 'bob@gmail.com', '************9876'), "
						+ "(4, 'Alice', 'Brown', '246 Maple St, Novi, USA', '5553332222', 'alice@gmail.com', '************2222'), "
						+ "(5, 'Charlie', 'Wilson', '789 Pine St, Troy, USA', '5551114444', 'charlie@gmail.com', '************4444'), "
						+ "(6, 'Emily', 'Jones', '654 Cedar St, Detroit, USA', '5554447777', 'emily@gmail.com', '************7777'), "
						+ "(7, 'Daniel', 'Taylor', '890 Birch St, Flint, USA', '5558883333', 'daniel@gmail.com', '************3333'), "
						+ "(8, 'Ella', 'White', '753 Oak St, Chicago, USA', '5552226666', 'ella@gmail.com', '************6666'), "
						+ "(9, 'Frank', 'Lee', '125 Pine St, Novi, USA', '5559992222', 'frank@gmail.com', '************2222'), "
						+ "(10, 'Grace', 'Smith', '367 Maple St, Troy, USA', '5554441111', 'grace@gmail.com', '************1111');" };

		String[] TREES = { "INSERT INTO Tree(tree_id, size, height, location, isNearHouse, note, quote_id) VALUES "
				+ "(101, 'Large', 15.5, 'Backyard', true, 'Tall oak tree', 1001), "
				+ "(102, 'Medium', 8.2, 'Front yard', false, 'Small apple tree',1002), "
				+ "(103, 'Small', 5.0, 'Side yard', true, 'Dwarf pine tree',1003), "
				+ "(104, 'Medium', 10.0, 'Front yard', false, 'Maple tree',1004), "
				+ "(105, 'Large', 20.0, 'Backyard', true, 'Pine tree',1005), "
				+ "(106, 'Small', 6.5, 'Backyard', false, 'Birch tree',1006), "
				+ "(107, 'Medium', 9.0, 'Front yard', true, 'Cherry tree',1007), "
				+ "(108, 'Large', 18.5, 'Backyard', false, 'Cypress tree',1008), "
				+ "(109, 'Medium', 12.0, 'Front yard', true, 'Palm tree',1009), "
				+ "(110, 'Small', 7.0, 'Side yard', false, 'Willow tree',1010);" };

		String[] QUOTES = { "INSERT INTO Quote(quote_id, client_id, request_date, note, status) VALUES "
				+ "(1001, 1, '2023-10-24', 'Tree trimming request',  'Pending'), "
				+ "(1002, 2, '2023-10-25', 'Tree removal request',  'Approved'), "
				+ "(1003, 3, '2023-10-26', 'Tree pruning request',  'Pending'), "
				+ "(1004, 4, '2023-10-27', 'Tree removal request',  'Approved'), "
				+ "(1005, 5, '2023-10-28', 'Tree trimming request','Pending'), "
				+ "(1006, 6, '2023-10-29', 'Tree pruning request', 'Approved'), "
				+ "(1007, 7, '2023-10-30', 'Tree removal request', 'Pending'), "
				+ "(1008, 8, '2023-10-31', 'Tree trimming request', 'Approved'), "
				+ "(1009, 9, '2023-11-01', 'Tree pruning request',  'Pending'), "
				+ "(1010, 10, '2023-11-02', 'Tree removal request',  'Approved');" };

		String[] ORDERS = {
				"INSERT INTO Orders(order_id, quote_id, start_date, end_date,client_id, total_Amount) VALUES "
						+ "(10001, 1001, '2023-10-26', '2023-10-28',1, 100), "
						+ "(10002, 1002, '2023-10-27', '2023-10-30',2, 200), "
						+ "(10003, 1003, '2023-10-28', '2023-10-30',3, 300), "
						+ "(10004, 1004, '2023-10-29', '2023-11-01',4, 400), "
						+ "(10005, 1005, '2023-10-30', '2023-11-02',5, 500), "
						+ "(10006, 1006, '2023-10-31', '2023-11-03',6, 600), "
						+ "(10007, 1007, '2023-11-01', '2023-11-03',7, 700), "
						+ "(10008, 1008, '2023-11-02', '2023-11-04',8, 800), "
						+ "(10009, 1009, '2023-11-03', '2023-11-05',9, 900), "
						+ "(10010, 1010, '2023-11-04', '2023-11-06',10, 1000);" };

		String[] BILLS = { "INSERT INTO Bill(bill_id, order_id, total_amount, status, note) VALUES "
				+ "(100001, 10001, 500.00, 'Paid', 'Tree trimming service'), "
				+ "(100002, 10002, 750.00, 'Pending', 'Tree removal service'), "
				+ "(100003, 10003, 450.00, 'Paid', 'Tree pruning service'), "
				+ "(100004, 10004, 800.00, 'Pending', 'Tree removal service'), "
				+ "(100005, 10005, 600.00, 'Paid', 'Tree trimming service'), "
				+ "(100006, 10006, 350.00, 'Pending', 'Tree pruning service'), "
				+ "(100007, 10007, 900.00, 'Paid', 'Tree removal service'), "
				+ "(100008, 10008, 550.00, 'Pending', 'Tree trimming service'), "
				+ "(100009, 10009, 400.00, 'Paid', 'Tree pruning service'), "
				+ "(100010, 10010, 700.00, 'Pending', 'Tree removal service');" };

		for (int i = 0; i < INITIAL.length; i++) {
			statement.execute(INITIAL[i]);
		}

		System.out.println("Done intitial");
		for (int i = 0; i < CLIENTS.length; i++) {
			statement.execute(CLIENTS[i]);
		}
		System.out.println("Done clients");
		for (int i = 0; i < TUPLES.length; i++) {
			statement.execute(TUPLES[i]);
		}
		System.out.println("Done tuples");
		for (int i = 0; i < QUOTES.length; i++) {
			statement.execute(QUOTES[i]);
		}
		System.out.println("Done quotes");
		for (int i = 0; i < TREES.length; i++) {
			statement.execute(TREES[i]);
		}
		System.out.println("Done trees");

		for (int i = 0; i < ORDERS.length; i++) {
			statement.execute(ORDERS[i]);
		}
		System.out.println("Done orders");
		for (int i = 0; i < BILLS.length; i++) {
			statement.execute(BILLS[i]);
		}
		System.out.println("Done bills");

		disconnect();
	}

}
