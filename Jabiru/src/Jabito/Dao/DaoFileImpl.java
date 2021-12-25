package Jabito.Dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Jabiru.Classes.Comment;
import Jabiru.Classes.Issue;
import Jabiru.Classes.Project;
import Jabiru.Classes.User;

public class DaoFileImpl implements IDao{
	
	private static DaoFileImpl mInstance;
	private String dbpath;
	private String path;
	private Connection conn = null;
	private Statement stat = null;
	
	private DaoFileImpl() throws ClassNotFoundException, SQLException
	{
		this.path="C:\\Derby\\database.db";
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		Class.forName(driver);
		this.dbpath="jdbc:derby:"+this.path+";create=true";
		this.conn = DriverManager.getConnection(dbpath,"Jabiro","Aa123456");
		this.stat=conn.createStatement();
		
		
		
		
	}
	
	
	
	public static DaoFileImpl getmInstance() throws ClassNotFoundException, SQLException {
		File tempFile = new File("c:/Derby/database.db");
		if((mInstance == null) && (!(tempFile.exists())))
		{
			mInstance = new DaoFileImpl();
			System.out.println("create Database");
			mInstance.createtables();
		}
		else
		{
			mInstance = new DaoFileImpl();
		}
		return mInstance;
	}



	public String getDbpath() {
		return dbpath;
	}



	public void setDbpath(String dbpath) {
		this.dbpath = dbpath;
	}



	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}



	public Connection getConn() {
		return this.conn;
	}



	public void setConn(Connection conn) {
		this.conn = conn;
	}



	public void createtables() throws SQLException
	{
		 String query = "CREATE TABLE Issue( "
		         + "IssueId VARCHAR(255), "
		         + "ProjectName VARCHAR(255), "
		         + "Reporter VARCHAR(255), "
		         + "Priority VARCHAR(255), "
		         + "Category VARCHAR(255), "
		         + "AssignedTo VARCHAR(255),"
		         + "Subject VARCHAR(255),"
		         + "Summarry VARCHAR(255),"
		         + "Attachment VARCHAR(255),"
		         + "Assigned SMALLINT,"
		         + "Updated_Day INT, "
				 + "Updated_Month INT,"
				 + "Updated_Years INT,"
				 + "Updated_Hour INT,"
				 + "Updated_Minutes INT,"
				 + "DueDate_Day INT, "
				 + "DueDate_Month INT,"
				 + "DueDate_Years INT,"
				 + "DueDate_Hour INT,"
				 + "DueDate_Minutes INT,"
		         + "Submitted_Day INT, "
				 + "Submitted_Month INT,"
				 + "Submitted_Years INT,"
				 + "Submitted_Hour INT,"
				 + "Submitted_Minutes INT)";
		    	 stat.execute(query);
		String query1 = "CREATE TABLE Project( "
			     + "Name VARCHAR(255), "
		         + "Contact VARCHAR(255), "
		         + "Phone VARCHAR(255), "
		         + "Email VARCHAR(255), "
		         + "SLA VARCHAR(255), "
			     + "Enabled SMALLINT)";
					stat.execute(query1);
	    String query2 = "CREATE TABLE Comment( "
	    		 + "IssueId VARCHAR(255), "
	    		 + "C_Date_Day INT, "
				 + "C_Date_Month INT,"
				 + "C_Date_Years INT,"
				 + "C_Date_Hour INT,"
				 + "C_Date_Minutes INT,"
				 + "UserName VARCHAR(255), "
				 + "Comment VARCHAR(255))";
					stat.execute(query2);
	    String query3 = "CREATE TABLE Users( "
			      + "RealName VARCHAR(255), "
				  + "Password VARCHAR(255), "
				  + "Email VARCHAR(255), "
				  + "UserName VARCHAR(255), "
				  + "Enabled SMALLINT)";  
					stat.execute(query3);
		 System.out.println("create tables");
		
	}
	
	public void connect() throws SQLException 
	{
		this.dbpath="jdbc:derby:"+this.path+";create=false";
		this.conn = DriverManager.getConnection(this.dbpath,"Jabiro","Aa123456");
		this.stat=conn.createStatement();
		System.out.println("connection Succeed");
	}
	
	@Override
	public void insertIntoIssue(Issue issue) throws SQLException
	{
		PreparedStatement psInsert=null;
		psInsert = this.conn.prepareStatement("insert into Issue"
		       	+ " (IssueId,ProjectName,Reporter,Priority,Category,AssignedTo,Subject,Summarry,Attachment,Assigned,"
				+ "Updated_Day,Updated_Month,Updated_Years,Updated_Hour,Updated_Minutes,"
				+ "DueDate_Day,DueDate_Month,DueDate_Years,DueDate_Hour,DueDate_Minutes,"
				+ "Submitted_Day,Submitted_Month,Submitted_Years,Submitted_Hour,Submitted_Minutes)"
		       	+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		       psInsert.setString(1, issue.getmId());
			   psInsert.setString(2, issue.getmProject().getmName());
			   psInsert.setString(3, issue.getmReporter().getmUserName());
			   psInsert.setString(4, issue.getmStringPriority());
			   psInsert.setString(5, issue.getmStringCategoty());
			   psInsert.setString(6, issue.getmAssignedTo().getmUserName());
			   psInsert.setString(7, issue.getmSubject());
			   psInsert.setString(8, issue.getmSummarry());
			   psInsert.setString(9, issue.getmAttachment().getPath());
			   psInsert.setBoolean(10, issue.getmAssigned());
			   psInsert.setInt(11, issue.getmUpdated().getDate());
			   psInsert.setInt(12, issue.getmUpdated().getMonth()+1);
			   psInsert.setInt(13, issue.getmUpdated().getYear()+1900);
			   psInsert.setInt(14, issue.getmUpdated().getHours());
			   psInsert.setInt(15, issue.getmUpdated().getMinutes()+1);
			   psInsert.setInt(16, issue.getmDueDate().getDate());
			   psInsert.setInt(17, issue.getmDueDate().getMonth()+1);
			   psInsert.setInt(18, issue.getmDueDate().getYear()+1900);
			   psInsert.setInt(19, issue.getmDueDate().getHours());
			   psInsert.setInt(20, issue.getmDueDate().getMinutes()+1);
			   psInsert.setInt(21, issue.getmDateSubmitted().getDate());
			   psInsert.setInt(22, issue.getmDateSubmitted().getMonth()+1);
			   psInsert.setInt(23, issue.getmDateSubmitted().getYear()+1900);
			   psInsert.setInt(24, issue.getmDateSubmitted().getHours());
			   psInsert.setInt(25, issue.getmDateSubmitted().getMinutes()+1);
			   psInsert.executeUpdate();
			   insertIntoComment(issue);
			   
	}



	@Override
	public void insertIntoComment(Issue issue) throws SQLException
	{
		for (Comment c : issue.getmFlow())
		{
			PreparedStatement psInsert=null;
			psInsert = this.conn.prepareStatement("insert into Comment"
			       	+ " (IssueId,C_Date_Day,C_Date_Month,C_Date_Years,C_Date_Hour,C_Date_Minutes,UserName,Comment)"
			       	+ " values (?,?,?,?,?,?,?,?)");
			psInsert.setString(1, issue.getmId());
			psInsert.setInt(2, c.getmDate().getDate());
			psInsert.setInt(3, c.getmDate().getMonth()+1);
			psInsert.setInt(4, c.getmDate().getYear()+1900);
			psInsert.setInt(5, c.getmDate().getHours());
			psInsert.setInt(6, c.getmDate().getMinutes()+1);
			psInsert.setString(7, c.getmUser().getmUserName());
			psInsert.setString(8, c.getComment());
			psInsert.executeUpdate();

		}
			  
	}



	@Override
	public void insertIntoProject(Project p) throws SQLException
	{
		PreparedStatement psInsert=null;
		psInsert = this.conn.prepareStatement("insert into Project"
		       	+ "(Name,Contact,Phone,Email,SLA,Enabled)"
		       	+ " values (?,?,?,?,?,?)");
		psInsert.setString(1, p.getmName());
		psInsert.setString(2, p.getmContact());
		psInsert.setString(3, p.getmPhone());
		psInsert.setString(4, p.getmEmail());
		psInsert.setString(5, p.getmSLA());
		psInsert.setBoolean(6, p.getmEnable());
		psInsert.executeUpdate();

		
	}



	@Override
	public void insertIntoUser(User u) throws SQLException {
		PreparedStatement psInsert=null;
		psInsert = this.conn.prepareStatement("insert into Users"
		       	+ "(RealName, Password,Email, UserName, Enabled)"
		       	+ " values (?,?,?,?,?)");
		psInsert.setString(1, u.getmRealName());
		psInsert.setString(2, u.getmPassword());
		psInsert.setString(3, u.getmEmail());
		psInsert.setString(4, u.getmUserName());
		psInsert.setBoolean(5, u.getmEnabled());
		psInsert.executeUpdate();
		
		
	}



	@Override
	public Issue retrieveFromIssue(String issueid) throws SQLException, ClassNotFoundException
	{
		
		 String project=new String();
		 String reporter=new String();
		 String Assignedto=new String();
		 Issue temp=new Issue();
		 Date update=new Date();
		 Date due=new Date();
		 Date Submitted=new Date();
		 String query = "SELECT * FROM \"JABIRO\".\"ISSUE\" WHERE IssueId LIKE " + "'"+issueid+"'";
	     ResultSet rs = stat.executeQuery(query);
	     while(rs.next())
	     {
	    	project=rs.getString("ProjectName");
	    	reporter=rs.getString("Reporter");
	     	Assignedto=rs.getString("AssignedTo");
	     	temp.setmId(issueid);
	     	temp.setmStringPriority(rs.getString("Priority"));
	     	temp.setmStringCategory(rs.getString("Category"));
	     	temp.setmSubject(rs.getString("Subject"));
	     	temp.setmSummarry(rs.getString("Summarry"));
	     	temp.setmAttachment(new File(rs.getString("Attachment")));
	     	temp.setmAssigned(rs.getBoolean("Assigned"));
	     	update.setDate(rs.getInt("Updated_Day"));
	     	update.setMonth(rs.getInt("Updated_Month"));
	     	update.setYear(rs.getInt("Updated_Years"));
	     	update.setHours(rs.getInt("Updated_Hour"));
	     	update.setMinutes(rs.getInt("Updated_Minutes"));
	     	due.setDate(rs.getInt("DueDate_Day"));
	     	due.setMonth(rs.getInt("DueDate_Month"));
	     	due.setYear(rs.getInt("DueDate_Years"));
	     	due.setHours(rs.getInt("DueDate_Hour"));
	     	due.setMinutes(rs.getInt("DueDate_Minutes"));
	     	Submitted.setDate(rs.getInt("Submitted_Day"));
	     	Submitted.setMonth(rs.getInt("Submitted_Month"));
	     	Submitted.setYear(rs.getInt("Submitted_Years"));
	     	Submitted.setHours(rs.getInt("Submitted_Hour"));
	     	Submitted.setMinutes(rs.getInt("Submitted_Minutes"));
	     	
	     }
	     temp.setmProject(retrieveFromProject(project));
	     temp.setmReporter(retrieveFromUser(reporter));
	     temp.setmAssignedTo(retrieveFromUser(Assignedto));
	     temp.setmUpdated(update);
	     temp.setmDueDate(due);
	     temp.setmDateSubmitted(Submitted);
	     temp.setmFlow(retrieveFromComment(issueid));
	     return temp;
	}



	@Override
	public HashSet<Comment> retrieveFromComment(String issueid) throws SQLException
	{
		String user=new String();
		Date d=new Date();
		Comment c = new Comment(null, "");
		HashSet<Comment> temp=new HashSet<Comment>();
		String query = "SELECT * FROM Comment WHERE IssueId LIKE '"+ issueid+ "'";
		ResultSet rs = stat.executeQuery(query);
		while(rs.next())
		{
			user=rs.getString("UserName");
			c.setComment(rs.getString("Comment"));
			d.setDate(rs.getInt("C_Date_Day"));
			d.setMonth(rs.getInt("C_Date_Month"));
			d.setYear(rs.getInt("C_Date_Years"));
			d.setHours(rs.getInt("C_Date_Hour"));
			d.setMinutes(rs.getInt("C_Date_Minutes"));
			c.setmDate(d);
			temp.add(c);
		}
		c.setmUser(retrieveFromUser(user));
		return temp;
		
	}



	@Override
	public Project retrieveFromProject(String projectName) throws SQLException
	{
		Project p=new Project("", "", "", "", "");
		String query = "SELECT * FROM Project WHERE Name LIKE "+ "'"+projectName+"'";
		ResultSet rs = stat.executeQuery(query);
		while(rs.next())
		{
			p.setmName(rs.getString("Name"));
			p.setmContact(rs.getString("Contact"));
			p.setmEmail(rs.getString("Email"));
			p.setmPhone(rs.getString("Phone"));
			p.setmSLA(rs.getString("SLA"));
			p.setmEnable(rs.getBoolean("Enabled"));
		}
		return p;
	}



	@Override
	public User retrieveFromUser(String userName) throws SQLException
	{
		User u=new User("", "", "", "");
		String query = "SELECT * FROM Users WHERE UserName LIKE '"+ userName+"'";
		ResultSet rs = stat.executeQuery(query);
		while(rs.next())
		{
			u.setmRealName(rs.getString("RealName"));
			u.setmPassword(rs.getString("Password"));
			u.setmEmail(rs.getString("Email"));
			u.setmUserName(rs.getString("Username"));
			u.setmEnabled(rs.getBoolean("Enabled"));
		}
		return u;
		
	}
	
	@Override
	public void close() throws SQLException
	{
		conn.close();
		conn=null;
	}
	
	@Override
	public void shutdown()
	{
		this.dbpath="jdbc:derby:"+this.path+";shutdown=true";
		try {
			this.conn = DriverManager.getConnection(dbpath,"Jabiro","Aa123456");
		} catch (SQLException e) {
		}
	}


/*

	@Override
	public Map<String,Issue> retrieveAllIssues() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Map<String,Project> retrieveAllProjects() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Map<String,User> retrieveAllUsers() {
		// TODO Auto-generated method stub
		
	}


*/
	@Override
	public void DeleteFromIssue(Issue issue) throws SQLException
	{
		String query = "DELETE FROM Issue WHERE IssueId ="+ issue.getmId();
	    int num = stat.executeUpdate(query);
	    System.out.println("Number of records deleted are: "+num);
	}

	@Override
	public void DeleteFromProject(Project p) throws SQLException {
		String query = "DELETE FROM Project WHERE ProjectName ="+ p.getmName();
	    int num = stat.executeUpdate(query);
	    System.out.println("Number of records deleted are: "+num);
		
	}

	@Override
	public void DeleteFromUser(User u) throws SQLException {
		String query = "DELETE FROM Users WHERE User ="+ u.getmUserName();
	    int num = stat.executeUpdate(query);
	    System.out.println("Number of records deleted are: "+num);
		
	}

	@Override
	public HashSet<Issue> retrieveByDateIssue(Date fromdate) throws SQLException, ClassNotFoundException
	{
		HashSet<String> Issueid=new HashSet<>();
		HashSet<Issue> temp=new HashSet<Issue>();
		String query = "SELECT * FROM \"JABIRO\".\"ISSUE\" "
			      + "where Submitted_Day>=" + String.valueOf(fromdate.getDate())+" AND "
			      + "Submitted_Month>=" + String.valueOf(fromdate.getMonth())+" AND "
			      + "Submitted_years>=" + String.valueOf(fromdate.getYear());
		ResultSet rs = stat.executeQuery(query);
		while(rs.next())
		{
			Issueid.add(rs.getString("IssueId"));
		}
		for (String i: Issueid)
		{
			temp.add(retrieveFromIssue(i));
		}
		return temp;
		
		
	}
	
	public HashSet<Issue> retrieveByAssignedIssue(String assignedto) throws SQLException, ClassNotFoundException
	{
		HashSet<String> Issueid=new HashSet<>();
		HashSet<Issue> temp=new HashSet<Issue>();
		String query = "SELECT * FROM \"JABIRO\".\"ISSUE\" "
			      + "where AssignedTo LIKE '"+assignedto+"'";
		ResultSet rs = stat.executeQuery(query);
		while(rs.next())
		{
			Issueid.add(rs.getString("IssueId"));
		}
		for (String i: Issueid)
		{
			temp.add(retrieveFromIssue(i));
		}
		return temp;
	}



	@Override
	public HashSet<Issue> retrieveAllIssues() throws SQLException, ClassNotFoundException
	{
		HashSet<String> Issueid=new HashSet<>();
		HashSet<Issue> temp=new HashSet<Issue>();
		String query = "SELECT * FROM \"JABIRO\".\"ISSUE\" ";
		ResultSet rs = stat.executeQuery(query);
		while(rs.next())
		{
			Issueid.add(rs.getString("IssueId"));
		}
		for (String i: Issueid)
		{
			temp.add(retrieveFromIssue(i));
		}
		return temp;
		
		
	}
	
	

}
	

