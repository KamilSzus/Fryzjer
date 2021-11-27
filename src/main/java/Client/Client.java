package Client;

/*
 6) Fryzjer - zgodnie ze stroną czyli:

 Aplikacja obslugujaca salon fryzjerski z jednym fryzjerem.
 Dla ulatwienia nalezy przyjac ze salon pracuje w godzinach 10-18
 a kazda wizyta trwa rowno godzine. Klienci zglaszaja (i wycofuja) terminy wizyt
 ktore chcieliby odbyc. Klienci widza aktualny stan zajetosci fryzjera
 (na ekranie klienta wyswietla sie zmiana widoku po kazdej aktualizacji terminarza fryzjera).
 Serwer rozsyla komunikaty o zarezerwowanych i wycofanych uslugach (i terminach)
 do wszystkich klientow powodujac odswiezenie widoku klienta. Nalezy zadbac o odpowiednia
 synchronizacje dostepu do zasobow i ich aktualizacje. Nalezy zadbac o wydajnosc aplikacji:
 zuzycie procesora i wykorzystanie lacza sieciowego.
 */

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    private static final int port = 9999;

    public static void main(String[] args) {
        try {
            InetAddress host = InetAddress.getLocalHost();
            do {
                Socket socket = new Socket(host.getHostName(), port);
                ReceivedMessageClient msgInputThread = new ReceivedMessageClient(socket);
                ObjectOutputStream outputMsg = new ObjectOutputStream(socket.getOutputStream());
                MessageToSend messageToSend = new MessageToSend();

                messageToSend.createMessage();
                outputMsg.writeObject(messageToSend.getMessage());
                msgInputThread.start();

                System.out.println("rozloczyc sie???????? ");
            } while (!loadWord().equals("tak"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static String loadWord() {
        Scanner load = new Scanner(System.in);
        return load.nextLine();
    }
}
