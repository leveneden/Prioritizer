import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

        List<Task> tasks = deserializeTasks();

        if (taskName.equals(""))
            return getClass() + ": [FAILURE] Task name is empty. Task wasn't added.";

        if (findTask(taskName, tasks) != null) {
            return getClass() + ": [FAILURE] A task with the same name exists. Task wasn't added.";
        } else {
            tasks.add(new Task(taskName, urgencyScore, importanceScore));
            saveTasks(tasks);
            return getClass() + ": [SUCCESS] Task successfully added.";
        }
    }

    public String addTask(String taskName) {
        return addTask(taskName, 0, 0);
    }

    public String deleteTask(String taskName) {

        List<Task> tasks = deserializeTasks();
        Task task = findTask(taskName, tasks);

        if (task != null) {
            tasks.remove(task);
            saveTasks(tasks);
            return getClass() + ": [SUCCESS] Task successfully deleted.";
        } else {
            return getClass() + ": [FAILURE] Task wasn't found in your tasks. " +
                    "Deleting a nonexistent task is redundant.";
        }
    }

    public String increaseTaskUrgency(String taskName, int urgencyDelta) {

        List<Task> tasks = deserializeTasks();

        if (urgencyDelta > 100)
            return getClass() + ": [FAILURE] Urgency delta is greater than 100. Task urgency score wasn't increased.";

        if (urgencyDelta < 0)
            return getClass() + ": [FAILURE] Urgency delta is less than 0. Task urgency score wasn't increased.";

        Task task = findTask(taskName, tasks);

        if (task == null) {
            return getClass() + ": [FAILURE] Task wasn't found in your tasks. Task urgency score wasn't increased.";
        } else {
            task.setUrgencyScore(task.getUrgencyScore() + urgencyDelta);
            saveTasks(tasks);
            return getClass() + ": [SUCCESS] Task urgency score successfully increased.";
        }
    }

    public String increaseTaskImportance(String taskName, int importanceDelta) {

        List<Task> tasks = deserializeTasks();

        if (importanceDelta > 100)
            return getClass() + ": [FAILURE] Importance delta is greater than 100. Task importance score wasn't increased.";

        if (importanceDelta < 0)
            return getClass() + ": [FAILURE] Importance delta is less than 0. Task importance score wasn't increased.";

        Task task = findTask(taskName, tasks);

        if (task == null) {
            return getClass() + ": [FAILURE] Task wasn't found in your tasks. Task importance score wasn't increased.";
        } else {
            task.setImportanceScore(task.getImportanceScore() + importanceDelta);
            saveTasks(tasks);
            return getClass() + ": [SUCCESS] Task importance score successfully increased.";
        }
    }

    Task findTask(String taskName, List<Task> tasks) {
        if (tasks != null && tasks.stream().anyMatch(t -> t.getName().toLowerCase().equals(taskName.toLowerCase())))
            return tasks.stream().filter(t -> t.getName().toLowerCase().equals(taskName.toLowerCase())).findFirst().get();
        return null;
    }

    public String listTasksBy(SearchCriteria criteria) {

        List<Task> tasks = deserializeTasks();
        List<Task> sortedTasks = new ArrayList<>();

        switch (criteria) {
            case DEFAULT:
                sortedTasks = tasks;
                break;

            case REVERSE:
                sortedTasks = new ArrayList<>(tasks);
                Collections.reverse(sortedTasks);
                break;

            case URGENCY:
                sortedTasks = tasks.stream()
                        .sorted(Comparator.comparingInt(Task::getUrgencyScore))
                        .collect(Collectors.toList());
                Collections.reverse(sortedTasks);
                break;

            case IMPORTANCE:
                sortedTasks = tasks.stream()
                        .sorted(Comparator.comparingInt(Task::getImportanceScore))
                        .collect(Collectors.toList());
                Collections.reverse(sortedTasks);
                break;
        }

        return listTasks(sortedTasks);
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

    private String saveTasks(List<Task> tasks) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(tasksFilePath), tasks);
            return this.getClass() + ": Tasks saved succesfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return this.getClass() + ": Tasks couldn't get saved.";
        }
    }

    public String listTasks(List<Task> tasks) {

        if (tasks.isEmpty())
            return getClass() + ": [FAILURE] Task list is empty.";
        String result = "";

        for (int i=0; i<tasks.size(); i++) {
            Task task = tasks.get(i);
            result += (i + 1) + ". {\"name\":\"" + task.getName() + "\", \"urgencyScore\":" + task.getUrgencyScore()
                    + ", \"importanceScore\":" + task.getImportanceScore() + "}\n";
        }
        return result;
    }
}
