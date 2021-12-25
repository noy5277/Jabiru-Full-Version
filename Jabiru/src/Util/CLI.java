package Util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.Scanner;

import Jabiru.Server.Server;

public class CLI implements Runnable{
	
	private Server listener;
	private InputStream in;
	private OutputStream out;
	
	public CLI(InputStream in, OutputStream out )
	{
		this.in=in;
		this.out=out;
		this.listener=null;
	}
	

	public void addPropertyChangeListener(Server server) {
		this.listener=server;
	}


	@Override
	public void run()
	{
		while(true)
		{
			Scanner scn=new Scanner(in);
			while(listener.serverup)
			{
				String s=scn.nextLine();
				if(s.equals("stop"))
				{
					listener.serverup=false;
					try {
						listener.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			while(listener.serverup==false)
			{
				System.out.println("enter start");
				String s=scn.nextLine();
				if(s.equals("start"))
				{
					new Thread(listener).start();
					listener.serverup=true;
				}
				
			}
		
		}
	}
	
	
	
	

}
