
public class Main {
    public static void main(String[] args) {
        Chatbox cb = new Chatbox();
        CallListenerThread clt = new CallListenerThread(cb);
        cb.whenUCalling();

    }

}
