package controller;

public class Servermain {

	public static void main(String[] args) {
		CallListener serv=new CallListener();
		MessageReciever con = new MessageReciever(serv.WaitForConnection());
	}

}
