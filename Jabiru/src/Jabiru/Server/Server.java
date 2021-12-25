package Jabiru.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Jabiro.Service.JabiroService;

public class Server implements Runnable
{
	public static boolean serverup;
	public static int port;
	private static Server mInstance;
	private ServerSocket server;
	private ExecutorService pool;
	private Socket someClient;
	
	private Server()
	{
		this.serverup=false;
		this.port=12749;
		
	}
	
	public static Server getmInstance()
	{
		if(mInstance==null)
		{
				mInstance=new Server();
		}
		return mInstance;
	}
	

	@Override
	public void run() 
	{
		try {
			this.server=new ServerSocket(port);
			this.serverup=true;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.pool = Executors.newFixedThreadPool(3);
		System.out.println("Starting server...");
		while(serverup)
		{
			try {
				someClient = server.accept();
				pool.execute(new HandleRequest(someClient));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	public void close() throws IOException
	{
			pool.shutdown();
			someClient.close();
			server.close();
			System.out.println("Check: Threadpoolempty="+pool.isTerminated());
		
		
	}
	
	
}
