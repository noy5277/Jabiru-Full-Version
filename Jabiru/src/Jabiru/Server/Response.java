package Jabiru.Server;

import Jabiro.Service.API;

public class Response <T>{
	
	private T answer;

	public Response(T answer) {
		this.answer = answer;
	}

	public T getAnswer() {
		return answer;
	}

	public void setAnswer(T answer) {
		this.answer = answer;
	}
	
	
	

}
