package oncall;

public class SplitUtil {

    private SplitUtil(){}

    private final static String BASIC_DELIMITER = ",";
    public static String[] splitByDelimiter(String message, String param) {
        return message.split(param);
    }

    public static String[] splitByDelimiter(String message) {
        return splitByDelimiter(message, BASIC_DELIMITER);
    }
}
