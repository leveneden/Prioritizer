import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private Scanner scanner;
    private Prioritizer prioritizer;

    private final String INVALID_NUMBER_OPTION = "[FAILURE] Your input doesn't correspond to a valid option. Try typing a valid option number.";
    private final String INVALID_NUMBER_RANGE_FOR_SCORE = "[FAILURE] Your input doesn't match the stablised range. Try typing a number that falls in range.";

    public ConsoleUI() {
        scanner = new Scanner(System.in);
        prioritizer = new Prioritizer();
    }

    public void start() {
        showHeader();
        while (true) {
            showMenu();
        }
    }

    private void showHeader() {

    }

    private void showMenu() {

        int indexOptionSelected = -1;

        System.out.println("Select an option from the menu below by typing the corresponding number:\n");

        for (ConsoleOption option: ConsoleOption.values()) {
            System.out.println(option.getEnumeratedDescription());
        }

        if (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            try {
                indexOptionSelected = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println(INVALID_NUMBER_OPTION);
            }
        }

        performChosenOperation(indexOptionSelected);
    }

    private void performChosenOperation(int optionIndex) {

        ConsoleOption optionSelected = null;

        for (ConsoleOption option: ConsoleOption.values()) {
            if (option.getIndex() == optionIndex) {
                optionSelected = option;
            }
        }

        if (optionSelected == null) return;

        switch (optionSelected) {
            case CREATE_TASK:
                createTask();
                break;
            case DELETE_TASK:
                deleteTask();
                break;
            case REVIEW_TASKS:
                reviewTasks();
                break;
            case LIST_TASKS:
                listTasks();
                break;
            case EXIT:
                System.exit(0);
                break;
            default:
                System.out.println(INVALID_NUMBER_OPTION);
        }
    }

    private void listTasks() {


    }

    private void reviewTasks() {
        List<Task> tasks = prioritizer.deserializeTasks();

        if (tasks.isEmpty()) {
            System.out.println("Your tasks are empty, there's nothing to review.");
            return;
        }

        System.out.println("Okay, let's review your tasks.");

        for (Task task: tasks) {

            System.out.println("Task: " + task.getName() + ", Urgency: " + task.getUrgencyScore() + ", Importance: " + task.getImportanceScore());
            System.out.println("what would you like to do with this task?");
            System.out.println("1. Journal.");
            System.out.println("2. Delete.");

            boolean answerIsValid = false;
            int indexOptionSelected = -1;

            do {
                String optionSelectedInput = scanner.nextLine();

                try {
                    indexOptionSelected = Integer.parseInt(optionSelectedInput);

                    if (indexOptionSelected < 1 || indexOptionSelected > 2) {
                        System.out.println(INVALID_NUMBER_OPTION);
                    } else {
                        answerIsValid = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(INVALID_NUMBER_OPTION);
                }
            } while (!answerIsValid);



            switch (indexOptionSelected) {
                case 1:
                    answerIsValid = false;
                    do {
                        System.out.println("Okay, how much would you increase this task urgency? (0-100)");
                        String deltaSelectedInput = scanner.nextLine();
                        int urgencyDelta = -1;

                        try {
                            urgencyDelta = Integer.parseInt(deltaSelectedInput);
                        } catch (NumberFormatException e) {
                            System.out.println(INVALID_NUMBER_RANGE_FOR_SCORE);
                            continue;
                        }

                        if (urgencyDelta < 0 || urgencyDelta >100) {
                            System.out.println(INVALID_NUMBER_RANGE_FOR_SCORE);
                            continue;
                        }

                        System.out.println(prioritizer.increaseTaskUrgency(task.getName(), urgencyDelta));
                        answerIsValid = true;

                    } while (!answerIsValid);

                    answerIsValid = false;
                    do {
                        System.out.println("Okay, how much would you increase this task urgency? (0-100)");
                        String deltaSelectedInput = scanner.nextLine();
                        int importanceDelta = -1;

                        try {
                            importanceDelta = Integer.parseInt(deltaSelectedInput);
                        } catch (NumberFormatException e) {
                            System.out.println(INVALID_NUMBER_RANGE_FOR_SCORE + " first");
                            continue;
                        }

                        if (importanceDelta < 0 || importanceDelta > 100) {
                            System.out.println(INVALID_NUMBER_RANGE_FOR_SCORE + " second");
                            continue;
                        }

                        System.out.println(prioritizer.increaseTaskImportance(task.getName(), importanceDelta));
                        answerIsValid = true;

                    } while (!answerIsValid);
                    break;
                case 2:
                    System.out.println(prioritizer.deleteTask(task.getName()));
                    break;
                default:
                    return;
            }
        }
    }

    private void deleteTask() {

        System.out.println("Type in the task name:");
        String taskName = scanner.nextLine();

        System.out.println(prioritizer.deleteTask(taskName));
    }

    private void createTask() {

        int urgencyScore = -1;
        int importanceScore = -1;

        System.out.println("Type in the task name:");
        String taskName = scanner.nextLine();

        boolean answerIsValid = false;
        do {
            System.out.println("Type in an urgency score (0-100):");
            String urgencyInput = scanner.nextLine();

            try {
                int parsedInput = Integer.parseInt(urgencyInput);
                if (parsedInput < 0 || parsedInput > 100) {
                    System.out.println(INVALID_NUMBER_RANGE_FOR_SCORE);
                } else {
                    urgencyScore = parsedInput;
                    answerIsValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println(INVALID_NUMBER_RANGE_FOR_SCORE);
            }
        } while (!answerIsValid);

        answerIsValid = false;

        do {
            System.out.println("Type in an importance score (0-100):");
            String importanceInput = scanner.nextLine();

            try {
                int parsedInput = Integer.parseInt(importanceInput);
                if (parsedInput < 0 || parsedInput > 100) {
                    System.out.println(INVALID_NUMBER_RANGE_FOR_SCORE);
                } else {
                    urgencyScore = parsedInput;
                    answerIsValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println(INVALID_NUMBER_RANGE_FOR_SCORE);
            }
        } while (!answerIsValid);

        System.out.println(prioritizer.addTask(taskName, urgencyScore, importanceScore));
    }
}
