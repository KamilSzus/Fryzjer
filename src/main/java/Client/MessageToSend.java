package Client;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MessageToSend {

    private final Map<String,String> message = new TreeMap<>();
    private String order;

    public Map<String, String> getMessage() {
        return message;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void createMessage(){
        System.out.println("Chose Order ADD or DELETE");
        setOrder(loadWord());
        System.out.println("podaj na ktora godzine chcesz sie zapisac w formacie HH:MM");
        String hour = loadWord();
        System.out.println("podaj swoje dane");
        String name = loadWord();
        message.put(hour,name);

    }

    private static String loadWord() {
        Scanner load = new Scanner(System.in);
        return load.nextLine();
    }
}
