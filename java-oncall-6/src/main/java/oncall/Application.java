package oncall;

import camp.nextstep.edu.missionutils.Console;

import java.lang.Exception;

public class Application {
    public static void main(String[] args) {

        OncallController oncallController = new OncallController(
                new WorkerSelector()
        );

        try {
            oncallController.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Console.close();
        }
    }
}
