import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SQLOutputImpl;

import org.apache.taglibs.standard.lang.jstl.Constants;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.sql.PreparedStatement;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, 
maxRequestSize = 1024 * 1024 * 5 * 5)
public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private TreeDAO treeDAO = new TreeDAO();
	    private QuoteDAO quoteDAO = new QuoteDAO();
	    private OrdersDAO ordersDAO = new OrdersDAO();
	    private BillDAO billDAO = new BillDAO();
	    private String currentUser;
	    private HttpSession session=null;
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
	    public void init()
	    {
	    	userDAO = new userDAO();
	    	currentUser= "";
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getServletPath();
	        System.out.println(action);
	    
	    try {
        	switch(action) {  
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/initialize":
        		userDAO.init();
        		System.out.println("Database successfully initialized!");
        		rootPage(request,response,"");
        		break;
        	case "/root":
        		rootPage(request,response, "");
        		break;
        	case "/logout":
        		logout(request,response);
        		break;
        	case "/createQuote":
        		createQuote(request,response);
        		break;
        		
        	case "/submitRequest":
        		submitRequest(request,response);
        		break;
        		
        	case "/addTree":
        		addTree(request,response);
        		break;
        		
        	case "/updateQuote":
        		updateQuote(request, response);
        		break;
        	case "/treeDetails":
        		getTreeDetails(request, response);
        		break;
        	case "/acceptQuote":
        		acceptQuote(request, response);
        		break;
        	case "/quotes":
        		getQuotes(request, response);
        		break;
        	case "/negotiate":
        		negotiate(request,response);
        		break;
        	case "/viewOrders":
        		viewOrders(request,response);
        		break;
        	case "/generateBill":
        		generateBill(request,response);
        		break;
        	case "/viewBills":
        		viewBills(request,response);
        		break;
        	case "/pay":
        		payment(request,response);
        		break;
        	case "/quoteHistory":
        		viewQuoteHistory(request,response);
        		break;
        	case "/rejectQuote":
        		deleteQuote(request,response);
        		break;
        	case "/reject":
        		System.out.println("rejcted");
        		rejectBill(request, response);
        		break;
        	case "/generateReport" :
        		System.out.println("generateReport");
        		generateReport(request, response);
        		
        	 
	    	}
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        	
	    private void listUser(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");

	     
	        List<user> listUser = userDAO.listAllUsers();
	        request.setAttribute("listUser", listUser);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }
	    	        
	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
			request.setAttribute("listUser", userDAO.listAllUsers());
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    
	    
	    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 String userName = request.getParameter("userName");
	    	 String password = request.getParameter("password");
	    	 
	    	 System.out.printf("Username %s password %s" , userName,password);
	    	 
	    	 if (userName.equals("root") && password.equals("pass1234")) {
				 System.out.println("Login Successful! Redirecting to root");
				 session = request.getSession();
				 session.setAttribute("username", userName);
				 rootPage(request, response, "");
	    	 }
	    	 
	    	 else if (userName.equals("David.smith@gmail.com") && password.equals("david1234")) {
	    		 session=request.getSession();
	    		 session.setAttribute("userName", "David Smith");
	    		 davidSmithPage(request, response);
	    	 }
	    	 
	    	 else if (userDAO.isClient(userName, password)!=null) {
	    		 session=request.getSession();
	    		 int quoteId = quoteDAO.generateId();
	    		 int clientId = userDAO.getClientId(userName);
	    		 session.setAttribute("clientId",clientId );
	    		
	    		 request.setAttribute("listQuoteRequests", quoteDAO.getQuotesByStatus("Requested", clientId));
	    		 request.getRequestDispatcher("clientView.jsp").forward(request, response); 
	    	 }
	    	 else if(userDAO.isValid(userName, password)) 
	    	 {
			 	 currentUser = userName;
				 System.out.println("Login Successful! Redirecting");
				 request.getRequestDispatcher("activitypage.jsp").forward(request, response);
			 			 			 			 
	    	 }
	    	 else {
	    		 request.setAttribute("loginStr","Login Failed: Please check your credentials.");
	    		 request.getRequestDispatcher("login.jsp").forward(request, response);
	    	 }
	    }
	           
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String userName = request.getParameter("userName");
	   	 	String firstName = request.getParameter("firstName");
	   	 	String lastName = request.getParameter("lastName");
	   	 	String password = request.getParameter("password");
	   	 	String confirm = request.getParameter("confirmation");
	   	 	String userRole = request.getParameter("userRole");
	   	 	long phoneNo = Long.parseLong(request.getParameter("phoneNo"));
	   	 	String address = request.getParameter("address");
	   	 	String creditCardInfo = request.getParameter("creditCardInfo");
	   	 	
	   	 	
	   	 	if (password.equals(confirm)) {
	   	 		if (!userDAO.checkUserName(userName)) {
		   	 		System.out.println("Registration Successful! Added to database");
		            user users = new user(userName,firstName, lastName, password,userRole);
		            System.out.println("User in insert :" + users.getUserName());
		   	 		userDAO.insert(users);
		   	 		System.out.println("User Role :" + userRole);
		   	 		
		   	 		if(userRole.equals("Clients")) {
	   	 			Client client = new Client(firstName,lastName,address, userName,phoneNo,creditCardInfo);
	   	 			userDAO.insertClient(client);
		   	 		}
		   	 		response.sendRedirect("login.jsp");
		   	 		
	   	 		}
		   	 	else {
		   	 		System.out.println("Username taken, please enter new username");
		    		 request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   		 request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 	}
	    }    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}
	   
	    
	    //Add Tree
	    
	    protected void addTree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 int quoteId = Integer.parseInt(session.getAttribute("quoteId").toString());
	    	 System.out.println("quote Id from add tree is :" + quoteId);
	    	 System.out.println("client Id from add tree is :" + session.getAttribute("clientId").toString());
	    	 String size = request.getParameter("size");
	    	 double height = Double.parseDouble(request.getParameter("height"));
	    	 String location = request.getParameter("location");
	    	 boolean isNearHouse = request.getParameter("isNearHouse").equalsIgnoreCase("Yes")?true:false;
	    	 String note = request.getParameter("note");
	    	 int treeId = treeDAO.generateId();
	    	 String status= "Requested";
	         double price = 0;
	            
	         int clientId = (int) session.getAttribute("clientId");
	         System.out.println("Client Id before quote insert :" + clientId);
	         
	         Date date = Date.valueOf(LocalDate.now());
	         System.out.println("date date : " +date);
	            
	         Quote quote = new Quote(quoteId,clientId,date,note,status,price,date,date);
	         if(!quoteDAO.isValid(quoteId)) {
	        	 quoteDAO.insertQuote(quote);
	        	 LocalDateTime updateddate =  LocalDateTime.now();
	        	 System.out.println("updateddate : " +updateddate);
	        	 int id = quoteDAO.generateIdQhistory()+1;
	        	 System.out.println("history id before from tree " + id);
	        	 Quote_history history = new Quote_history(id,quoteId,clientId,updateddate.toString(),note,status,price,date,date);
	        	 System.out.println("history id from tree " + history.getId());
	        	 quoteDAO.insertQuoteHistory(history);
	        	 
	         }
	        	 
	    	 Tree tree = new Tree(treeId,quoteId,size,height,location,isNearHouse,note);
	    	 treeDAO.insertTree(tree);
	    	 request.setAttribute("treesAdded", treeDAO.listTreesByQuote(quoteId));
	    	 System.out.println("size " +treeDAO.listTreesByQuote(quoteId).size());
	    	 
	    	System.out.println("Adding images");
	    	addImages(request);
	    	 
	    	 
		     request.getRequestDispatcher("createQuote.jsp").forward(request, response);
//	    	 response.sendRedirect("createQuote.jsp");
	    	
	    }
	    
	    protected void createQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String quoteId = String.valueOf(quoteDAO.generateId()+1);
	    	session.setAttribute("quoteId", quoteId);
	    	request.getRequestDispatcher("createQuote.jsp").forward(request, response);
	    }
	    
	    
	    private void davidSmithPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("David view");
			request.setAttribute("listQuoteRequests", quoteDAO.getQuotesNegOrReq());
			
			for(Quote q :quoteDAO.getQuotesNegOrReq()) {
				System.out.println(q.getStatus());
			}
	    	request.getRequestDispatcher("davidSmithView.jsp").forward(request, response);
	    
	    
	    }
	    
	    private void updateQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Calling update Quote method");
	    	
	    	Quote quote = null;
	    	int clientId =Integer.parseInt( request.getParameter("clientId"));
	    	System.out.println("client Id : " + clientId);
	    	int quoteId =Integer.parseInt( request.getParameter("quoteId"));
	    	System.out.println("quote Id : " + quoteId);
	    	double price = Double.parseDouble(request.getParameter("price"));
	    	System.out.println("price : " + price);
	    	Date startDate =Date.valueOf(request.getParameter("startDate"));
	    	System.out.println("startDate : " + startDate);
	    	Date endDate =Date.valueOf(request.getParameter("endDate"));
	    	System.out.println("endDate : " + endDate);
	    	String note = request.getParameter("note");
	    	System.out.println("Note : " + note);
	    	String status = request.getParameter("status");
	    	System.out.println("status: " + status);
	    	Date requestedDate = Date.valueOf( LocalDate.now(ZoneId.of("EST", ZoneId.SHORT_IDS)));
	    	System.out.println("req date: " + requestedDate);
	    	quote = new Quote(quoteId,clientId,requestedDate,note,status,price,startDate,endDate );
	    	quoteDAO.update(quote);
	    	LocalDateTime updateddate =  LocalDateTime.now();
	    	quoteDAO.insertQuoteHistory(new Quote_history(quoteDAO.generateIdQhistory()+1,quoteId,clientId,updateddate.toString(),note,status,price,startDate,endDate));
       	 
	    	System.out.println("Done updating Quote ");
	    	String message = status + " Quote for quoteId : " + quoteId;
	    	request.setAttribute("message", message);
	    	request.setAttribute("listQuoteRequests", quoteDAO.getQuotesNegOrReq());
	    	request.getRequestDispatcher("davidSmithView.jsp").forward(request, response);

	    	
	    	
	    	
	    }
	    
	    private void getTreeDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Calling TreeDetails method");
	    
	    	int quoteId =Integer.parseInt(request.getParameter("quoteId"));
	    	
	    	List<Tree> treeDetails = treeDAO.listTreesByQuote(quoteId);
	    	
	    	request.setAttribute("listOfTrees", treeDetails);
			
	    	request.getRequestDispatcher("treeDetails.jsp").forward(request, response);
	    	System.out.println("Done getting tree details ");
	    	
	    	
	    }
	    
	    private void getQuotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Calling getQuotes method");
	    
	    	int clientId =Integer.parseInt(request.getParameter("clientId"));
	    	System.out.println("client Id :" + clientId);
	    	
	    	List<Quote> quotes = quoteDAO.getQuotesByclientId(clientId);
	    	request.setAttribute("quotes", quotes);
	    	request.getRequestDispatcher("viewQuotes.jsp").forward(request, response);
	    	System.out.println("Done getting quote details ");
	    	
	    	
	    }
	    
	    private void acceptQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Calling acceptQuote method");
	    	int quoteId =Integer.parseInt(request.getParameter("quoteId"));
	    	System.out.println(quoteId);
	    	int clientId =Integer.parseInt(request.getParameter("clientId"));
	    	System.out.println(clientId);
	    	Date startDate =Date.valueOf((request.getParameter("startDate")));
	    	System.out.println(startDate);
	    	Date endDate =Date.valueOf((request.getParameter("endDate")));
	    	System.out.println(endDate);
	    	double totalPrice =Double.parseDouble(request.getParameter("price"));
	    	System.out.println(totalPrice);
	    	quoteDAO.updateStatus(quoteId, "accepted");
	    	LocalDateTime updateddate =  LocalDateTime.now();
	    	quoteDAO.insertQuoteHistory(new Quote_history(quoteDAO.generateIdQhistory()+1,quoteId,clientId,updateddate.toString(),"","accepted",totalPrice,startDate,endDate));
       	 
	    	int orderId = ordersDAO.generateId()+1;
	    	Orders order = new Orders(orderId,clientId,quoteId,startDate,endDate,totalPrice);
	    	ordersDAO.insertOrders(order);
	    	request.getRequestDispatcher("clientView.jsp").forward(request, response);
	    	System.out.println("Done calling acceptQuote  ");
	    	
	    }
	    
	    private void negotiate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Calling acceptQuote method");
	    	int quoteId =Integer.parseInt(request.getParameter("quoteId"));
	    	String note = request.getParameter("note");
	    	quoteDAO.negotiate(quoteId, "negotiate",note);
	    	LocalDateTime updateddate =  LocalDateTime.now();
	    	Date date = Date.valueOf(LocalDate.now().toString());
	    	quoteDAO.insertQuoteHistory(new Quote_history(quoteDAO.generateIdQhistory()+1,quoteId,(int) session.getAttribute("clientId"),updateddate.toString(),note,"negotiate",0.0,date,date));
       	 
	    	request.getRequestDispatcher("clientView.jsp").forward(request, response);
	    	System.out.println("Done calling acceptQuote  ");
	    	
	    	
	    }
	    
	    private void submitRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Calling submitRequest method");
	    	int clientId = Integer.parseInt(session.getAttribute("clientId").toString());
	    	request.setAttribute("listQuoteRequests", quoteDAO.getQuotesByStatus("Requested", clientId));
    		request.getRequestDispatcher("clientView.jsp").forward(request, response); 
	    
	    	
	    }
	    
	    private void viewOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Calling viewOrders method");
	    
	    	request.setAttribute("orders", ordersDAO.listAllOrders());
	    	
	    	request.getRequestDispatcher("orders.jsp").forward(request, response);
	    	System.out.println("Done calling viewOrders  ");
	    	
	    	
	    }
	    
	    
	    private void generateBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Calling generateBill method");
	    	int billId = billDAO.generateId()+1;
	    	int orderId =Integer.parseInt(request.getParameter("orderId"));
	    	System.out.println("QuoteID :" +orderId);
	    	String note = request.getParameter("note");
	    	System.out.println("Note :" +note);
	    	String status = "Pending";
	    	double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
	    	System.out.println("Price :" +totalPrice);
	    	Date generated_on = Date.valueOf(LocalDate.now());
	    	Bill bill = new Bill(billId,orderId,totalPrice,status,note,generated_on,null);
	    	System.out.println("generated_on : " + generated_on);
	    	int generatedBillId=billDAO.insertBill(bill);
	    	request.setAttribute("orders", ordersDAO.listAllOrders());
	    	String message=  "Bill has been generated with Bill Id :" + generatedBillId ;
	    	request.setAttribute("message", message);
	    	
	    	request.getRequestDispatcher("orders.jsp").forward(request, response);
	    	
	    	
	    	System.out.println("Done calling viewOrders  ");
	    	
	    	
	    }
	    
	    
	    private void viewBills(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Calling viewBills method");
	    
	    	int clientId = Integer.parseInt(request.getParameter("clientId"));
	    	System.out.println(" viewBills method clientId :" + clientId);
	    	request.setAttribute("bills", billDAO.getPendingOrPaidBills(clientId));
	    	request.getRequestDispatcher("viewBills.jsp").forward(request, response);
	    	System.out.println("Done viewBills viewOrders  ");
	    	
	    	
	    }
	    
	    private void payment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Calling payment method");
	    
	    	int billId = Integer.parseInt(request.getParameter("billId"));
	    	request.setAttribute("bills", billDAO.getBillsByClientId(billId));
	    	String status ="Paid";
	    	billDAO.updateStatustoPaid(billId);
	    	request.setAttribute("bills", billDAO.getBillsByClientId(Integer.parseInt(session.getAttribute("clientId").toString())));
	    	request.getRequestDispatcher("viewBills.jsp").forward(request, response);
	    	System.out.println("Done payment viewOrders  ");
	    	
	    	
	    }
	    
	    private void viewQuoteHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Calling viewQuoteHistory method");
	    	request.setAttribute("quotes", quoteDAO.getQuotesHistory());
	    	request.getRequestDispatcher("quoteHistory.jsp").forward(request, response);
	    	System.out.println("Done viewQuoteHistory ");
	    	
	    	
	    }
	    
	    private void deleteQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Calling deleteQuote method");
	    	int quoteId= Integer.parseInt(request.getParameter("quoteId"));
	    	int clientId =Integer.parseInt(session.getAttribute("clientId").toString());
	    	if(treeDAO.delete(quoteId)) {
	    		quoteDAO.delete(quoteId);
	    	}
	    	
	    	String msg = "deleted quote with quoteID : " + quoteId;
	    	
	    	request.setAttribute("message",msg );
	    	List<Quote> quotes = quoteDAO.getQuotesByclientId(clientId);
	    	request.setAttribute("quotes", quotes);
	    	request.getRequestDispatcher("viewQuotes.jsp").forward(request, response);
	    	System.out.println("Done deleteQuote ");
	    	
	    	
	    }
	    
	    private void rejectBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Calling rejectBill method");
	    	int billId= Integer.parseInt(request.getParameter("billId"));
	    	String note = request.getParameter("note").toString();
	    	int clientId = Integer.parseInt(session.getAttribute("clientId").toString());
	    	billDAO.updateStatusRejected(billId, "rejected" , note);
	    	List<Bill>  bills= billDAO.getPendingOrPaidBills(clientId);
	    	request.setAttribute("bills", bills);
	    	request.getRequestDispatcher("viewBills.jsp").forward(request, response);
	    	System.out.println("Done deleteQuote ");
	    	
	    	
	    }
	    
	    
	    private void addImages(HttpServletRequest request) {
	    	// Define the folder path where you want to save the uploaded files
	    	String uploadFolder = "/Users/punithaj/Desktop/Fall2023/DBMS/database/WebContent/images"; // Replace with the actual path
	    	
	    	String uploadPath = getServletContext().getRealPath("") + File.separator + uploadFolder;
	    	File uploadDir = new File(uploadPath);
	    	if (!uploadDir.exists()) uploadDir.mkdir();


	    	try {
	    		for (Part part : request.getParts()) {
	    		   String fileName = getFileName(part);
	    		    part.write(uploadPath + File.separator + fileName);
	    		}

	    	}catch(Exception e) {
	    		System.out.println("Error while getting images "); 
	    		e.printStackTrace();
	    	}

	    	// Helper method to get the file name from a Part
	    	

	    }
	    
	    
	    private String getFileName(Part part) {
	        for (String content : part.getHeader("content-disposition").split(";")) {
	            if (content.trim().startsWith("treeimage"))
	                return content.substring(content.indexOf("=") + 2, content.length() - 1);
	            }
	        return "";
	    }
	    
	    private void generateReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Calling generateReport method");
	    
	    	Report report = new Report();
	    	
	    	List<Client> easyClients = report.getEasyClients();
	    	request.setAttribute("bigClients", report.getBigClients());
	    	request.setAttribute("easyClients", easyClients);
	    	request.setAttribute("oneTreeQuotes", report.getOneTreeQuote());
	    	request.setAttribute("prospectiveClients", report.getProspectiveClients());
	    	request.setAttribute("overDueBills", report.getOverDueBills());
	    	System.out.println("size in report : " + report.getBadClients().size());
	    	request.setAttribute("badClients", report.getBadClients());
	    	request.setAttribute("goodClients", report.getGoodClients());
	    	request.setAttribute("stats", report.getStats());
	    	request.setAttribute("highestTrees", report.getHighestTree());
	    	request.getRequestDispatcher("report.jsp").forward(request, response);
	    	
	    	
	    	System.out.println("Done generating report ");
	    	
	    	
	    }
}
 
	        
	        
	        
	    


