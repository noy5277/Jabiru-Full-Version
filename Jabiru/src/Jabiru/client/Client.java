package Jabiru.client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.gson.Gson;

import Jabiru.Classes.User;
import Jabiru.Server.Request;
import Jabiru.Server.Response;

import java.awt.BorderLayout;

public class Client{
	
	private InetAddress address;
	private Socket server ;
	private Scanner reader;
	private PrintWriter writer;
	private String userName;
	
	
	public Client() throws IOException
	{
		this.address = InetAddress.getByName("localhost");
		this.server = new Socket(address, 12749);
		this.reader = new Scanner(new InputStreamReader(server.getInputStream()));
		this.writer = new PrintWriter(new OutputStreamWriter(server.getOutputStream()));
		this.userName=new String();
	}
	
	
	
	
	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public void createLoginRequest(User u) throws IOException
	{
		Request<User> req=new Request<User>();
		Gson gson=new Gson();
		req.getH().setAction("login");
		req.setB(u);
		String jsonString=gson.toJson(req);
		this.writer.write(jsonString);
		this.writer.flush();
		String massage=reader.nextLine();
		System.out.println("check:"+massage);
		
		
	}
	
	public void closeConnection() throws IOException
	{
		this.writer.close();
		this.reader.close();
		this.server.close();
	}

	
}

	
