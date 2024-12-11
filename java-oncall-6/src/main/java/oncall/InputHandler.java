package oncall;

import camp.nextstep.edu.missionutils.Console;

public class InputHandler {

    private InputHandler(){}

    public static String printMessageAndReadSingleLine(String message) {
        System.out.print(message);
        return Console.readLine();
    }
}
