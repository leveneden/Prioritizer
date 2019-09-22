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

    public String addTask(String taskName, int urgencyScore, int importanceScore) {
        return "";
    }

    public String addTask(String taskName) {
        return "";
    }

    public String deleteTask(String taskName) {
        return "";
    }

    public String increaseTaskUrgency(String taskName, int urgencyDelta) {
        return "";
    }

    public String increaseTaskImportance(String taskName, int importanceDelta) {
        return "";
    }

    public Task findTask(String taskName, List<Task> tasks) {
        return null;
    }

    public String listTasksBy(SearchCriteria criteria) {

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
        return "";
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

    public String saveTasks(List<Task> tasks) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(tasksFilePath), tasks);
            return this.getClass() + ": Tasks saved succesfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return this.getClass() + ": Tasks couldn't get saved.";
        }
    }
}
