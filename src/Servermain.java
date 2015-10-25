
public class Servermain {

	public static void main(String[] args) {
		CallListener serv=new CallListener();
		Messeger m=new Messeger(serv.WaitForConnection());
        m.Recieved();
	}

}
