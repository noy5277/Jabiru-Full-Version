package Jabiro.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Jabiru.Classes.Comment;
import Jabiru.Classes.Issue;
import Jabiru.Classes.Project;
import Jabiru.Classes.User;

public class JabiroServiceTest {
	
	private static JabiroService service;
	private Project p;
	private User u;
	private Issue i;
	private Comment c;
	
	@Before
	public void init() throws ClassNotFoundException, SQLException
	{
		service=JabiroService.getmInstance();
		p=new Project("HIT","bathen","050-9874851","bathen@hit.com","24x7");
		u=new User("noyz", "noy zozel", "noy5277@gmail.com", "P@sssw0rd");
		i=new Issue(p, u ,"create new server","hi, i need to create new server with ip address 10.0.0.2 thanks." );
		c=new Comment(u, "the server created");
		
	}
	
	@Test
	public void checkProjectTable() throws ClassNotFoundException, SQLException
	{
		service.getmProjects().add(p.getmName(), p);
		service.pushProjectTable();
		service.retrieveProject("HIT");
		assertEquals("bathen@hit.com",service.getmProjects().getobject("HIT").getmEmail());
	}
	
	@Test
	public void checkIssueTable() throws ClassNotFoundException, SQLException
	{
		service.getmIssues().add(i.getmId(), i);
		service.getmIssues().getobject(i.getmId()).getmFlow().add(c);
		service.pushIssueTable();
		service.retrieveissue(i.getmId());
		assertEquals("create new server",service.getmIssues().getobject(i.getmId()).getmSubject());
	}
	
	@Test
	public void checkUserTable() throws ClassNotFoundException, SQLException
	{
		service.getmUsers().add(u.getmUserName(), u);
		service.pushUserTable();
		service.retrieveUser(u.getmUserName());
		assertEquals("noy5277@gmail.com",service.getmUsers().getobject(u.getmUserName()).getmEmail());
	}
	
	
	@AfterClass
	public static void end() throws SQLException, ClassNotFoundException
	{
		File f=new File("C:\\Derby\\database.db");
		f.delete();
		service.closeDB();
		service.shutdownDB();
		service.getClock().stop();
	}

}
