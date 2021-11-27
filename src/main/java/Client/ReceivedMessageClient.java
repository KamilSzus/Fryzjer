package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ReceivedMessageClient implements Runnable {
    private final Socket socket;

    ReceivedMessageClient(Socket socket)
    {
        this.socket=socket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ObjectInputStream inputMsg = new ObjectInputStream(socket.getInputStream());
                String message = (String) inputMsg.readObject();
                System.out.println(message);
            }
        }catch (IOException | ClassNotFoundException e){
            System.out.println(e);
        }
    }

    public void start ()
    {
        Thread thread = new Thread(this);
        thread.start ();
    }
}
