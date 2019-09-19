import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.List;

public class Prioritizer {

    private String tasksFilePath = "src/main/resources/tasks.json";

    public Prioritizer() {}

    public Prioritizer(String tasksFilePath) {
        this.tasksFilePath = tasksFilePath;
    }

    public void addTask(Task task) {
    }

    public void deleteTask() {
    }

    public void changeTaskUrgency() {
    }

    public void changeTaskImportance() {
    }

    public void listTasksBy(SearchCriteria criteria) {

        List<Task> tasks = deserializeTasks();

        switch (criteria) {
            case DEFAULT:
                break;

            case REVERSE:
                break;

            case URGENCY:
                break;

            case IMPORTANCE:
                break;
        }
    }


    public List<Task> deserializeTasks() {

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(
                    FileUtils.readFileToString(new File(tasksFilePath), "utf-8"),
                    new TypeReference<List<Task>>(){}
                    );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveTasks(List<Task> tasks) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(tasksFilePath), tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
