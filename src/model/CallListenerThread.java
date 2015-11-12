import java.net.Socket;


public class CallListenerThread extends Thread{
     CallListener cl;
     Thread t;
     int a =0;
     boolean isSearch=true;
     Socket socket1=null;
     CallListenerThread(){
    	 t= new Thread(this);
    	 cl=new CallListener();
     }
     public void starte(){
    	 t.start();
     }
     
     public void run() {
    	 while (isSearch == true){
    		socket1= cl.WaitForConnection();
    	 }
    	 try {
			t.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
     }
     
     public Socket getSocket()
     {
    	return socket1; 
     }
}
