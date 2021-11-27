package Client;

import java.util.*;

public class MessageToSend {

    private final Map<String,String> message = new TreeMap<>();

    public void createMessage(){
        System.out.println("podaj na ktora godzine chcesz sie zapisac w formacie HH:MM");
        String hour = loadWord();
        System.out.println("podaj swoje dane");
        String name = loadWord();
        message.put(hour,name);

    }

    public Map<String, String> getMessage() {
        return message;
    }

    private static String loadWord() {
        Scanner load = new Scanner(System.in);
        return load.nextLine();
    }
}
