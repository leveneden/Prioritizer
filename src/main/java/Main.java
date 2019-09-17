import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String [] args) throws IOException {
        Prioritizer prioritizer = new Prioritizer();
        //prioritizer.listTasksBy(SearchCriteria.DEFAULT);

        // create the mapper
        ObjectMapper mapper = new ObjectMapper();

        // enable pretty printing
        //mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // serialize the object
        mapper.writeValue(new File(Prioritizer.TASKS_FILE), getTask());

        prioritizer.deserializeTasks();
    }

    static Task getTask() {
        Task task = new Task("clean up your room!");
        task.setImportanceScore(100);
        task.setUrgencyScore(2);

        return task;
    }
}
