package Jabiro.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Jabiro.StringMaching.IAlgoStringMatching;
import Jabiro.StringMaching.KMP;
import Jabiru.Classes.Comment;
import Jabiru.Classes.Issue;
import Jabiru.Classes.Project;
import Jabiru.Classes.User;
import Jabiru.Clock.Clock;
import Jabiru.Clock.IAlgoClock;
import Jabito.Dao.DaoFileImpl;
import Jabito.Dao.IDao;

public class JabiroService
{
	
	private IAlgoClock Clock;
	private static IAlgoStringMatching mSearch;
	private static IDao DB;
	private static JabiroService mInstance;
	private static API <String,Issue> mIssues;
	private static API <String,Project> mProjects;
	private static API <String,User> mUsers;
	private static HashMap <String,Object> mRestore;
	public User admin;
	
	
	private JabiroService() throws ClassNotFoundException, SQLException {
		Clock=new Clock();
		Clock.start();
		this. admin=new User("admin","admin","","admin");
		mIssues=new API<String,Issue>();
		mProjects=new API<String,Project>();
		mUsers=new API<String,User>();
		mRestore=new HashMap<String,Object>();
		mSearch=new KMP();
		mUsers.add("admin", admin);
		DB=DaoFileImpl.getmInstance();
		DB.insertIntoUser(admin);
		
	}
	
	public User getAdmin() {
		return admin;
	}

	public API<String, Issue> getmIssues() {
		return mIssues;
	}

	public void setmIssues(API<String, Issue> mIssues) {
		this.mIssues = mIssues;
	}

	public API<String, Project> getmProjects() {
		return mProjects;
	}

	public void setmProjects(API<String, Project> mProjects) {
		this.mProjects = mProjects;
	}

	public API<String, User> getmUsers() {
		return mUsers;
	}

	public void setmUsers(API<String, User> mUsers) {
		this.mUsers = mUsers;
	}

	public HashMap<String, Object> getmRestore() {
		return mRestore;
	}

	public void setmRestore(HashMap<String, Object> mRestore) {
		this.mRestore = mRestore;
	}

	public void clearall()
	{
		mIssues.removeall();;
		mProjects.removeall();
		mUsers.removeall();
		mRestore.clear();
	}

	
	//singleton
	public static JabiroService getmInstance() throws ClassNotFoundException, SQLException  {
		if(mInstance==null)
		{
				mInstance=new JabiroService();
		}
		return mInstance;
	}

	public Boolean search(String pat,String txt )
	{
		if(mSearch.Search(pat,txt))
		{
			return true;
		}
		return false;
	}
	

	public IAlgoClock getClock() {
		return Clock;
	}
	
	
	
	public void deleteIssue(String issueid) throws ClassNotFoundException, SQLException
	{
		DB.DeleteFromIssue(this.mIssues.getobject(issueid));
		this.mIssues.delete(issueid);
		
	}
	
	public void deleteProject(String projectid) throws ClassNotFoundException, SQLException
	{
		DB.DeleteFromProject(this.mProjects.getobject(projectid));
		this.mProjects.delete(projectid);
	}
	
	public void deleteUser(String UserName) throws ClassNotFoundException, SQLException
	{
		DB.DeleteFromUser(this.mUsers.getobject(UserName));
		this.mUsers.delete(UserName);
	}

	public void pushIssueTable() throws ClassNotFoundException, SQLException
	{
		for (String i : this.mIssues.Keyset())
		{
			DB.insertIntoIssue(this.mIssues.getobject(i));
		}
		this.mIssues.removeall();
		System.out.println(Clock.getTime()+ " Push Issue Table");
	}
	
	public void pushProjectTable() throws ClassNotFoundException, SQLException
	{
		for (String i : this.mProjects.Keyset())
		{
			DB.insertIntoProject(this.mProjects.getobject(i));
		}
		this.mProjects.removeall();
		System.out.println(Clock.getTime()+ " Push Project Table");
	}
	
	public void pushUserTable() throws ClassNotFoundException, SQLException
	{
		for (String i : this.mUsers.Keyset())
		{
			DB.insertIntoUser(this.mUsers.getobject(i));
		}
		this.mUsers.removeall();
		System.out.println(Clock.getTime()+ " Push User Table");
	}
	
	public Issue retrieveissue(String id) throws ClassNotFoundException, SQLException
	{
		Issue temp=DB.retrieveFromIssue(id);
		this.mIssues.add(temp.getmId(), temp);
		System.out.println(Clock.getTime()+" retrieve issue");
		return temp;
	}
	
	public HashSet<Issue> retrieveAllissues() throws ClassNotFoundException, SQLException
	{
		return DB.retrieveAllIssues();
	}
	
	public Project retrieveProject(String name) throws ClassNotFoundException, SQLException
	{
		Project temp=DB.retrieveFromProject(name);
		this.mProjects.add(temp.getmName(),temp);
		System.out.println(Clock.getTime()+" retrieve Project");
		return temp;
	}
	
	public User retrieveUser(String username) throws ClassNotFoundException, SQLException
	{
		User temp=DB.retrieveFromUser(username);
		this.mUsers.add(username,temp);
		System.out.println(Clock.getTime()+" retrieve User");
		return temp;
	}
	
	public Boolean signin(String username, String password) throws ClassNotFoundException, SQLException
	{
		User temp=retrieveUser(username);
		return password.equals(temp.getmPassword());
		
	}
	
	public HashSet<Issue> getAssignedToIssues(String UserName) throws ClassNotFoundException, SQLException
	{
		System.out.println(Clock.getTime()+" retrieve Assigned To Set issues");
		return DB.retrieveByAssignedIssue(UserName);
	}
	
	public HashSet<Issue> getLastIssuesfrom(Date fromDate) throws ClassNotFoundException, SQLException
	{
		System.out.println(Clock.getTime()+" retrieve Last Issues from issues");
		return DB.retrieveByDateIssue(fromDate);
	}
	
	public void closeDB() throws SQLException
	{
		DB.close();
		System.out.println(Clock.getTime()+" Close Database");
	}
	
	public void shutdownDB()
	{
		DB.shutdown();
		System.out.println(Clock.getTime()+" Shutdown Database");
	}

}
