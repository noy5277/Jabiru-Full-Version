package Jabiru.Classes;


import java.sql.SQLException;
import java.util.Date;

import Jabiro.Service.JabiroService;
import Jabito.Dao.DaoFileImpl;

public class main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		JabiroService service=JabiroService.getmInstance();
		Project p=new Project("HIT","bathen","050-9874851","bathen@hit.com","24x7");
		User u=new User("noyz", "noy zozel", "noy5277@gmail.com", "P@sssw0rd");
		Issue i=new Issue(p, u ,"create new server","hi, i need to create new server with ip address 10.0.0.2 thanks." );
		Comment c=new Comment(u, "the server created");
		i.getmFlow().add(c);
		service.getmProjects().add(p.getmName(), p);
		service.getmIssues().add(i.getmId(), i);
		service.getmUsers().add(u.getmUserName(), u);
		service.pushProjectTable();
		service.pushIssueTable();
		service.pushUserTable();
		service.retrieveissue(i.getmId());
		service.retrieveProject("HIT");
		service.retrieveissue(i.getmId());
		service.getmIssues().getobject(i.getmId()).setmStringStatus("RESOLVED");
		service.closeDB();
		service.shutdownDB();
		service.getClock().stop();

	}

}
