package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ReceivedMessageServer implements Runnable {
    private final Socket socket;
    private final Map<String,String> calender;
    private final Set<Socket> setOfClients;

    public ReceivedMessageServer(Socket socket, Map<String, String> calender, Set<Socket> setOfClients) {
        this.socket = socket;
        this.calender = calender;
        this.setOfClients = setOfClients;
    }

    private void getOrder(ObjectInputStream inputMsg, String order) throws IOException, ClassNotFoundException {
        ObjectOutputStream outputMsg = new ObjectOutputStream(socket.getOutputStream());
        Map<String, String> message = (Map<String, String>) inputMsg.readObject();

        if(order.equals("ADD")){
            Optional<String> key = message.keySet()
                    .stream()
                    .findFirst();
            if(calender.get(key.get()).equals("free space")){
                calender.putAll(message);
                outputMsg.writeObject("Add");
            }
            else{
                outputMsg.writeObject("Deadline busy");
            }
        }
        else if(order.equals("DELETE")) {
            Optional<String> key = message.keySet()
                    .stream()
                    .findFirst();
            calender.put(key.get(), "free space");
            outputMsg.writeObject("Deleted");

        }
        else{
            outputMsg.writeObject("Unknown Order");
        }
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inputMsg = new ObjectInputStream(socket.getInputStream());
            String order = (String) inputMsg.readObject();
            getOrder(inputMsg,order);

            PrintCalender printCalender = new PrintCalender(calender,setOfClients);
            printCalender.start();
            Thread.sleep(160);
            System.out.println(calender);

        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
