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

    public void setTasksFilePath(String tasksFilePath) {
        this.tasksFilePath = tasksFilePath;
    }

    public boolean addTask(String taskName) {
        return false;
    }

    public boolean deleteTask(String taskName) {
        return false;
    }

    public boolean increaseTaskUrgency(String taskName, int urgencyDelta) {
        return false;
    }

    public boolean increaseTaskImportance(String taskName, int importanceDelta) {
        return false;
    }

    public Task findTask(String taskName, List<Task> tasks) {
        return null;
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

    public boolean saveTasks(List<Task> tasks) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(tasksFilePath), tasks);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
