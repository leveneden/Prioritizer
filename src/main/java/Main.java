import persistence.impl.TaskPersister;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        TaskPersister taskTaskPersister = new TaskPersister();

        get("/hola", (req, res) -> "Hola Mundo");

        get("/hola/:name", (req, res) -> {
            return "Hola " + req.params(":name") + "!"; // buenas practicas!
        });
    }
}
