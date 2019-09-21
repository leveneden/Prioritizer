import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PrioritizerUnitTests {

    /*
     *  Naming Convention:
     *  MethodName_StateUnderTest_ExpectedBehavior
     */

    private Prioritizer prioritizer;

    @BeforeEach
    void setUp() {
        prioritizer = new Prioritizer("src/test/resources/tasksMock.json");
        writeDefaultMockToFile();
    }

    @AfterEach
    void tearDown() {
        writeDefaultMockToFile();
    }

    // addTask()
    @Test
    void addTask_ExistsATaskWithSameNameAndSameCase_DoesntGetAddedAndReturnFalse() {

    }

    @Test
    void addTask_TaskNameIsEmpty_DoesntGetAddedAndReturnFalse() {
    }

    @Test
    void addTask_ExistsATaskWithSameNameAndDifferentCase_DoesntGetAddedAndReturnFalse() {
    }

    @Test
    void addTask_DoesntExistATaskWithSameName_GetsAddedAndReturnTrue() {
    }

    // deleteTask()
    @Test
    void deleteTask_TaskExists_ReturnTrueAndGetsDeleted() {
    }

    @Test
    void deleteTask_TaskDoesntExist_ReturnFalse() {
    }

    // increaseTaskUrgency()
    @Test
    void increaseTaskUrgency_TaskExists_TaskUrgencyGetsIncreasedByDeltaAndReturnTrue() {
    }

    @Test
    void increaseTaskUrgency_TaskDoesntExist_TaskUrgencyDoesntGetIncreasedAndReturnFalse() {
    }

    // increaseTaskImportance()
    @Test
    void increaseTaskImportance_TaskExists_TaskImportanceGetsIncreasedByDeltaAndReturnTrue() {
    }

    @Test
    void increaseTaskImportance_TaskDoesntExist_TaskImportanceDoesntGetIncreasedAndReturnFalse() {
    }

    // listTasksBy()
    @Test
    void listTasksBy_DefaultOrder_TeasksGetReturnedInExpectedOrder() {
    }

    @Test
    void listTasksBy_ReverseOrder_TeasksGetReturnedInExpectedOrder() {
    }

    @Test
    void listTasksBy_ImportanceOrder_TeasksGetReturnedInExpectedOrder() {
    }

    @Test
    void listTasksBy_UrgencyOrder_TeasksGetReturnedInExpectedOrder() {
    }

    // deserializeTasks()
    @Test
    void deserializeTasks_FileToReadFromExists_ReturnListOfTasks() {
    }

    @Test
    void deserializeTasks_FileToReadFromDoesntExist_ReturnNull() {
    }

    // saveTasks()
    @Test
    void saveTasks_EmptyList_SavesEmptyListAndReturnTrue() {
    }

    @Test
    void saveTasks_PopulatedListWithTaskObjects_SavesListAndReturnTrue() {
    }

    @Test
    void saveTasks_PopulatedListWithSomeNullValuesObjects_Unknown() {
    }

    // findTask()
    @Test
    void findTask_TaskWithSameNameDoesntExist_ReturnNull() {
        assertNull(prioritizer.findTask("nonexistent task", generateTaskList()));
    }

    @Test
    void findTask_TaskWithSameNameAndSameCaseExists_ReturnTask() {
        List<Task> tasks = generateTaskList();
        int randomIndex = getRandomIndex(tasks);
        Task taskToBeFound = tasks.get(randomIndex);
        final String TASK_NAME = taskToBeFound.getName();

        assertEquals(taskToBeFound, prioritizer.findTask(TASK_NAME, tasks));
    }

    @Test
    void findTask_TaskWithSameNameAndDifferentCaseExists_ReturnTask() {
        List<Task> tasks = generateTaskList();
        int randomIndex = getRandomIndex(tasks);
        Task taskToBeFound = tasks.get(randomIndex);
        final String TASK_NAME_WITH_DIFFERENT_CASE = taskToBeFound.getName().toUpperCase();

        assertEquals(taskToBeFound, prioritizer.findTask(TASK_NAME_WITH_DIFFERENT_CASE, tasks));
    }

    @Test
    void findTask_TaskListPassedIsEmpty_ReturnNull() {
        assertNull(prioritizer.findTask("task1", new ArrayList<Task>()));
    }

    @Test
    void findTask_TaskListPassedIsNull_ReturnNull() {
        assertNull(prioritizer.findTask("task1", null));
    }

    @Test
    void findTask_TaskNamePassedIsEmpty_ReturnNull() {
        List<Task> tasks = generateTaskList();

        assertNull(prioritizer.findTask("", tasks));
    }

    // Utility Methods
    private void writeDefaultMockToFile() {

        String defaultMock = "[{\"name\":\"clean up your room!\",\"urgencyScore\":40,\"importanceScore\":30},\n" +
                "{\"name\":\"order your drawer\",\"urgencyScore\":22,\"importanceScore\":45},\n" +
                "{\"name\":\"learn to fold your clothes\",\"urgencyScore\":3,\"importanceScore\":76},\n" +
                "{\"name\":\"put a plan in there\",\"urgencyScore\":5,\"importanceScore\":17},\n" +
                "{\"name\":\"clean the damn thing up, see if you can do it\",\"urgencyScore\":82,\"importanceScore\":86}\n" +
                "]";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/test/resources/tasksMock.json"));
            writer.write(defaultMock);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<Task> generateTaskList() {
        return Arrays.asList(
                new Task("task1"),
                new Task("task2"),
                new Task("task3"),
                new Task("task4"),
                new Task("task5"),
                new Task("task6"),
                new Task("task7"),
                new Task("task8")
        );
    }

    int getRandomIndex(List list) {
        return (int) (Math.random() * list.size());
    }
}
