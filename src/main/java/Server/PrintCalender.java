package Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

public class PrintCalender implements Runnable {
    private final Map<String,String> calender;
    private final Set<Socket> setOfClients;

    public PrintCalender(Map<String, String> calender, Set<Socket> setOfClients) {
        this.calender = calender;
        this.setOfClients = setOfClients;
    }

    public Map<String, String> getCalender() {
        return calender;
    }

    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }

    public void printCalenderForEntry(Socket socket) {
        try {
            ObjectOutputStream outputMsg = new ObjectOutputStream(socket.getOutputStream());
            outputMsg.writeObject("Timetable\n" + calender.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        setOfClients
                .forEach(socket -> {
                    try {
                        ObjectOutputStream outputMsg = new ObjectOutputStream(socket.getOutputStream());
                        outputMsg.writeObject("Timetable\n"+calender.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }


}
