package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Server {
    private static final int port = 9999;

    public static Map<String,String> calender = new TreeMap<>(){
        @Override
        public String toString() {
            StringBuilder list = new StringBuilder();
            calender.
                    forEach((hour, name) ->
                            list.append(hour)
                                    .append(" ")
                                    .append(name)
                                    .append("\n"));
            return list.toString();
        }

    };
    public static Set<Socket> setOfClients = new HashSet<>();

    public static void main(String[] args) {
        try {
            initCalender();

            ServerSocket server = new ServerSocket(port);
            while (true) {
                Socket socket = server.accept();
                setOfClients.add(socket);

                PrintCalender printCalender = new PrintCalender(calender,setOfClients);
                printCalender.printCalenderForEntry(socket);

                ReceivedMessageServer OutputReader = new ReceivedMessageServer(socket,calender,setOfClients);

                OutputReader.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initCalender(){
        calender.put("10:00","free space");
        calender.put("11:00","free space");
        calender.put("12:00","free space");
        calender.put("13:00","free space");
        calender.put("14:00","free space");
        calender.put("15:00","free space");
        calender.put("16:00","free space");
        calender.put("17:00","free space");
    }
}
