import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        
    }

    @Test
    void findTask_TaskWithSameNameAndSameCaseExists_ReturnTask() {
        final String TASK_NAME = "Delete System32";
        Task task = new Task(TASK_NAME);

        assertEquals(task, prioritizer.findTask(TASK_NAME));
    }

    @Test
    void findTask_TaskWithSameNameAndDifferentCaseExists_ReturnTask() {
        final String TASK_NAME = "DELETE System32";
        Task task = new Task(TASK_NAME);

        assertEquals(task, prioritizer.findTask(TASK_NAME));
    }
}