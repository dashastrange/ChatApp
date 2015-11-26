package model;

import view.MainFrame;

public class Main {
    public static void main(String[] args) {
        MainFrame cb = new MainFrame();
        CallListenerThread clt = new CallListenerThread(cb);
        cb.whenUCalling();

    }

}
