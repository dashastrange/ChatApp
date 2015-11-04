package controller;

public class Protocol {
    
	public int whichone(String s){
	    if (s==null) return 0;
	    else if (s.equals("Message\n")) return 1;
	    else if (s.equals("Disconnect\n")) return 2;
	    else if (s.equals("Accepted\n")) return 3;
	    else if (s.equals("Rejected\n")) return 4;
	    else if (s.equals("Busy\n")) return 5;
	    else return 0;
	}
}
