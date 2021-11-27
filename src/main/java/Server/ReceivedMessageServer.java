package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

public class ReceivedMessageServer implements Runnable {
    private Socket socket;
    private final Map<String,String> calender;
    private final Set<Socket> setOfClients;

    public ReceivedMessageServer(Socket socket, Map<String, String> calender, Set<Socket> setOfClients) {
        this.socket = socket;
        this.calender = calender;
        this.setOfClients = setOfClients;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inputMsg = new ObjectInputStream(socket.getInputStream());
            Map<String, String> message = (Map<String, String>) inputMsg.readObject();
            ObjectOutputStream outputMsg = new ObjectOutputStream(socket.getOutputStream());
            calender.putAll(message);
            outputMsg.writeObject(calender.toString());

            Thread.sleep(160);
            PrintCalender printCalender = new PrintCalender(calender,setOfClients);
            printCalender.start();

        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
