import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Prioritizer {

    public static final String TASKS_FILE = "src/main/resources/tasks.txt";
    private BufferedReader bufferedReader;

    public void initializeBufferedReader() throws FileNotFoundException {
        FileReader fileReader = new FileReader(TASKS_FILE);
        bufferedReader = new BufferedReader(fileReader);
    }

    public void closeBufferedReader() throws IOException {
        bufferedReader.close();
    }

    public void addTask() {
    }

    public void deleteTask() {
    }

    public void changeTaskUrgency() {
    }

    public void changeTaskImportance() {
    }

    public void listTasksBy(SearchCriteria criteria) throws IOException {

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

        closeBufferedReader();
    }

    public void reviewTasks() {
    }

    public List<Task> deserializeTasks() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String jsonInput = "[{\"name\":\"clean up your room!\",\"urgencyScore\":2,\"importanceScore\":100}," +
                "{\"name\":\"clean up your room!\",\"urgencyScore\":3,\"importanceScore\":100}," +
                "{\"name\":\"clean up your room!\",\"urgencyScore\":4,\"importanceScore\":100}," +
                "{\"name\":\"clean up your room!\",\"urgencyScore\":5,\"importanceScore\":100}]";
        List<Task> taskList = mapper.readValue(jsonInput, new TypeReference<List<Task>>(){});

        for (Task task: taskList) {
            System.out.println(task);
        }

        String line = null;
        initializeBufferedReader();

        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(line != null && !line.equals("") ) {
            System.out.println(line);
            line = bufferedReader.readLine();
            // tasklist.add() [deserialize object from json]
        }

        // return list
        return taskList;
    }

    public void saveTasks(List<Task> tasks) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // write a whole serialized list into file instead of this:
        mapper.writeValue(new File(Prioritizer.TASKS_FILE), Main.getTask());
    }
}
