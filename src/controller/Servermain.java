import java.io.IOException;


public class Servermain {

	public static void main(String[] args) {
		MessageReciever con;
		CallListenerThread serv=new CallListenerThread();
		serv.starte();
		while (serv.getSocket()==null){}
		System.out.println("Hello")	;
	    con = new MessageReciever(serv.getSocket());
		
	}

}
