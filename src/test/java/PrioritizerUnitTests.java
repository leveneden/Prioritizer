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

    // addTask(String taskName)
    @Test
    void addTask_ExistsATaskWithSameNameAndSameCase_DoesntGetAdded() {
        final String NAME_ALREADY_IN_LIST = generateTaskList().get(getRandomIndex(generateTaskList())).getName();

        assertEquals("class Prioritizer: [FAILURE] A task with the same name exists. Task wasn't added.",
                prioritizer.addTask(NAME_ALREADY_IN_LIST));
    }

    @Test
    void addTask_TaskNameIsEmpty_DoesntGetAdded() {
        assertEquals("class Prioritizer: [FAILURE] Task name is empty. Task wasn't added.",
                prioritizer.addTask(""));
    }

    @Test
    void addTask_ExistsATaskWithSameNameAndDifferentCase_DoesntGetAdded() {
        final String NAME_ALREADY_IN_LIST_BUT_WITH_DIFFERENT_CASE = generateTaskList()
                .get(getRandomIndex(generateTaskList())).getName().toUpperCase();

        assertEquals("class Prioritizer: [FAILURE] A task with the same name exists. Task wasn't added.",
                prioritizer.addTask(NAME_ALREADY_IN_LIST_BUT_WITH_DIFFERENT_CASE));
    }

    @Test
    void addTask_DoesntExistATaskWithSameName_GetsAdded() {
        assertEquals("class Prioritizer: [SUCCESS] Task added successfully.",
                prioritizer.addTask("Make my bed"));
    }

    // addTask(String taskName, int urgencyScore, int importanceScore)
    @Test
    void addTask2_TaskNameIsEmpty_DoesntGetAdded() {
        assertEquals("class Prioritizer: [FAILURE] Task name is empty. Task wasn't added.",
                prioritizer.addTask("", randomInt(100), randomInt(100)));
    }

    @Test
    void addTask2_ExistsATaskWithSameNameAndDifferentCase_DoesntGetAdded() {
        final String NAME_ALREADY_IN_LIST_BUT_WITH_DIFFERENT_CASE = generateTaskList()
                .get(getRandomIndex(generateTaskList())).getName().toUpperCase();

        assertEquals("class Prioritizer: [FAILURE] A task with the same name exists. Task wasn't added.",
                prioritizer.addTask(NAME_ALREADY_IN_LIST_BUT_WITH_DIFFERENT_CASE, randomInt(100), randomInt(100)));
    }

    @Test
    void addTask2_DoesntExistATaskWithSameName_GetsAdded() {
        assertEquals("class Prioritizer: [SUCCESS] Task successfully added.",
                prioritizer.addTask("Make my bed", randomInt(100), randomInt(100)));
    }

    // deleteTask()
    @Test
    void deleteTask_TaskExists_TaskGetsDeleted() {
        assertEquals("class Prioritizer: [SUCCESS] Task successfully deleted.",
                prioritizer.addTask("Task5"));
    }

    @Test
    void deleteTask_TaskDoesntExist_DoesntGetDeleted() {
        assertEquals("class Prioritizer: [FAILURE] Task wasn't found in your tasks. " +
                        "Deleting a nonexistent task is redundant.",
                prioritizer.addTask("nonexistent task"));
    }

    // increaseTaskUrgency()
    @Test
    void increaseTaskUrgency_ValidCase_TaskUrgencyGetsIncreasedByDelta() {
        assertEquals("class Prioritizer: [SUCCESS] Task urgency score successfully increased.",
                prioritizer.increaseTaskUrgency("Task4", randomInt(100)));
    }

    @Test
    void increaseTaskUrgency_TaskDoesntExist_TaskUrgencyDoesntGetIncreased() {
        assertEquals("class Prioritizer: [FAILURE] Task wasn't found in your tasks. " +
                        "Task urgency score wasn't increased.",
                prioritizer.increaseTaskUrgency("nonexistent task", randomInt(100)));
    }

    @Test
    void increaseTaskUrgency_UrgencyDeltaIsLessThanZero_TaskUrgencyDoesntGetIncreased() {
        assertEquals("class Prioritizer: [FAILURE] Urgency delta is less than 0. " +
                        "Task urgency score wasn't increased.",
                prioritizer.increaseTaskUrgency("Task4", -1));
    }

    @Test
    void increaseTaskUrgency_UrgencyDeltaIsGreaterThanOneHundred_TaskUrgencyDoesntGetIncreased() {
        assertEquals("class Prioritizer: [FAILURE] Urgency delta is greater than 100. " +
                        "Task urgency score wasn't increased.",
                prioritizer.increaseTaskUrgency("Task4", 101));
    }

    // increaseTaskImportance()
    @Test
    void increaseTaskImportance_ValidCase_TaskImportanceGetsIncreasedByDelta() {
        assertEquals("class Prioritizer: [SUCCESS] Task importance score successfully increased.",
                prioritizer.increaseTaskImportance("Task4", randomInt(100)));
    }

    @Test
    void increaseTaskImportance_TaskDoesntExist_TaskImportanceDoesntGetIncreased() {
        assertEquals("class Prioritizer: [FAILURE] Task wasn't found in your tasks. " +
                        "Task importance score wasn't increased.",
                prioritizer.increaseTaskImportance("nonexistent task", randomInt(100)));
    }

    @Test
    void increaseTaskImportance_ImportanceDeltaIsLessThanZero_TaskImportanceDoesntGetIncreased() {
        assertEquals("class Prioritizer: [FAILURE] Importance delta is less than 0. " +
                        "Task importance score wasn't increased.",
                prioritizer.increaseTaskImportance("Task4", -1));
    }

    @Test
    void increaseTaskImportance_ImportanceDeltaIsGreaterThanOneHundred_TaskImportanceDoesntGetIncreased() {
        assertEquals("class Prioritizer: [FAILURE] Importance delta is greater than 100. " +
                        "Task importance score wasn't increased.",
                prioritizer.increaseTaskImportance("Task4", 101));
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
                new Task("Task1", 75, 70),
                new Task("Task2", 30, 31),
                new Task("Task3", 56, 30),
                new Task("Task4", 49, 4),
                new Task("Task5", 45, 87),
                new Task("Task6", 93, 40),
                new Task("Task7", 23, 3),
                new Task("Task8", 29, 24)
        );
    }

    int getRandomIndex(List list) {
        return (int) (Math.random() * list.size());
    }

    int randomInt(int maxNum) {
        return (int) (Math.random() * maxNum) + 1;
    }
}
