package Util;

import Jabiru.Server.Server;

public class Driver {

	public static void main(String[] args)
	{
			CLI cli = new CLI(System.in, System.out);
			Server server = Server.getmInstance();
			cli.addPropertyChangeListener(server);
			new Thread(cli).start();

	}

}
