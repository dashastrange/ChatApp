package controller;

public class Protocol {
    
	public int whichone(String s){
	    if (s==null) return 0;
	    else if (s.equals("Message")) return 1;
	    else if (s.equals("Disconnect")) return 2;
	    else if (s.equals("Accepted")) return 3;
	    else if (s.equals("Rejected")) return 4;
	    else if (s.equals("Busy")) return 5;
	    else return 0;
	}
}
