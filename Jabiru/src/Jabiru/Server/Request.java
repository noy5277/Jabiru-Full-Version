package Jabiru.Server;

import java.io.FileOutputStream;

import com.google.gson.Gson;

import Jabiro.Service.API;



public class Request <T>
{	
	public static class Header
	{
		private String action;

		public Header(String action)
		{
			this.action = action;
		}

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}
		
		
	}
	
	
	private Header headers;
	private T body ;
	
	public Request() {
		this.headers = new Header(null);
	}

	public Header getH() {
		return headers;
	}

	public void setH(Header h) {
		this.headers = h;
	}

	public T getB() {
		return body;
	}

	public void setB(T b) {
		this.body = b;
	}
	
	
}
