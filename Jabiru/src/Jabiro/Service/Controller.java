package Jabiro.Service;

import java.awt.print.Book;
import java.sql.SQLException;

import com.google.gson.Gson;

import Jabiru.Classes.User;

import Jabiru.Server.Request;
import Jabiru.Server.Request.Header;
import Jabiru.Server.Response;
import Jabiru.Server.Server;

public class Controller {
	
	private static JabiroService service;
	private String action;
	

	public Controller(String action) throws ClassNotFoundException, SQLException {
		this.service=JabiroService.getmInstance();
		this.action=action;
		}


	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public Response process(String jsonstring) throws ClassNotFoundException, SQLException
	{
		Response<Object> res=new Response(null);
		switch(action)
		{
		case "login":
			Gson gson=new Gson();
			User temp=gson.fromJson(jsonstring, User.class);
			res.setAnswer(service.signin(temp.getmUserName(), temp.getmPassword()));
			break;
	    }
		return res;
	}
}
	


