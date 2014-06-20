package com.example.ticket_monitor.app;

import java.io.Serializable;

/**
 * Created by Олег on 20.06.2014.
 */
public class MyBundle implements Serializable {

    private static final long serialVersionUID = 5950169519310163575L;

    int numberTicket;
    int numberWindow;

    public MyBundle(int numberTicket, int numberWindow) {
        this.numberTicket = numberTicket;
        this.numberWindow = numberWindow;
    }

    public int getNumberTicket() {
        return numberTicket;
    }

    public int getNumberWindow() {
        return numberWindow;
    }

}
