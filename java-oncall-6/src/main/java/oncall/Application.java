package oncall;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {

        OncallController oncallController = new OncallController(
                new WorkerSelector()
        );

        oncallController.run();
        Console.close();
    }
}
