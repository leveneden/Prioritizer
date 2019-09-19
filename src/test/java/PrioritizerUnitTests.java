import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class PrioritizerUnitTests {

    /*
    Naming Convention:
    MethodName_StateUnderTest_ExpectedBehavior
     */

    @BeforeEach
    void setUp() {
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
    void addTask() {
    }

    // deleteTask()
    @Test
    void deleteTask() {
    }

    // changeTaskUrgency()
    @Test
    void changeTaskUrgency() {
    }

    // changeTaskImportance()
    @Test
    void changeTaskImportance() {
    }

    // listTasksBy()
    @Test
    void listTasksBy() {
    }

    // deserializeTasks()
    @Test
    void deserializeTasks() {
    }

    // saveTasks()
    @Test
    void saveTasks() {
    }
}