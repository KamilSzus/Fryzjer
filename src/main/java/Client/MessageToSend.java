package Client;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

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

    public void createMessage() throws WrongFormatDateException {
        System.out.println("Chose Order ADD or DELETE");
        setOrder(loadWord());
        System.out.println("podaj na ktora godzine chcesz sie zapisac w formacie HH:MM");
        String hour = loadWord();
        if(verifyIsHourCorrect(hour).equals(false)){
            throw new WrongFormatDateException("Wrong date",hour);
        }
        System.out.println("podaj swoje dane");
        String name = loadWord();
        message.put(hour,name);
    }

    private Boolean verifyIsHourCorrect(String hour){
        Pattern pattern = Pattern.compile("^(1[0-7]):00$");
        return pattern.matcher(hour).matches();
    }

    private static String loadWord() {
        Scanner load = new Scanner(System.in);
        return load.nextLine();
    }
}
