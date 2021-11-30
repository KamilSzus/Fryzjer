package Client;

public class WrongFormatDateException extends Exception{
    private final String hour;

    public WrongFormatDateException(String error,String hour) {
        super(error);
        this.hour = hour;
    }

    public String getHour() {
        return hour;
    }
}
