package Jabito.Dao;


import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Jabiru.Classes.Comment;
import Jabiru.Classes.Issue;
import Jabiru.Classes.Project;
import Jabiru.Classes.User;

public abstract interface IDao {
	
	public void insertIntoIssue(Issue issue) throws ClassNotFoundException, SQLException;
	
	public void insertIntoComment(Issue issue) throws SQLException;
	 
	public void insertIntoProject(Project p) throws SQLException;
	
	public void insertIntoUser(User u) throws SQLException;
	
	public Issue retrieveFromIssue(String issue) throws SQLException, ClassNotFoundException;
	
	public HashSet<Issue> retrieveByDateIssue(Date fromdate) throws SQLException, ClassNotFoundException;
	public HashSet<Issue> retrieveByAssignedIssue(String assignedto) throws SQLException, ClassNotFoundException;
	
	public Set<Comment> retrieveFromComment(String issue) throws SQLException;
	
	public Project retrieveFromProject(String projectName) throws SQLException;
	
	public User retrieveFromUser(String username) throws SQLException;
	
	public HashSet<Issue> retrieveAllIssues() throws SQLException, ClassNotFoundException;
	/*
	public Map<String,Project> retrieveAllProjects();
	
	public Map<String,User> retrieveAllUsers();
	*/
	public void DeleteFromIssue(Issue issue) throws SQLException;
	
	public void DeleteFromProject(Project p) throws SQLException;
	
	public void DeleteFromUser(User u) throws SQLException;
	
	public void shutdown();
	
	public void close() throws SQLException;
}