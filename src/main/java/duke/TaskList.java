package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static final String deadline = "deadline";
    public static final String point = " .";
    public static final String findMessage = "Here are the matching tasks in your list: ";
    public static final String taskAddedMessage = "Got it. I've added this task:";
    public static final String listMessage = "Here are the tasks in your list:";
    public static final String todo = "todo";
    public static final String event = "event";
    public static final String by = "/by";
    public static final String at = "/at";
    public static final String space = " ";
    public static final String emptyString = "";


    /**
     * @param index
     * @return task at index i
     */
    public Task getTaskFromList(int index) {
        return tasks.get(index);
    }


    /**
     * @return TaskList size
     */
    public int getListSize() {
        return tasks.size();
    }

    /**
     * finds a task in list by input from user
     *
     * @param input
     */
    public void findTasks(String input) {
        Message.printHorizontalLine();
        System.out.println(findMessage);
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(input)) {
                System.out.print(i + 1 + ". ");
                System.out.println(tasks.get(i));
            }
        }
    }

    /**
     * adds a new task to the TaskList by translating
     * the users input
     *
     * @param input
     */
    public void addTask(String input) {
        try {
            translateTask(input);
            printTaskAdded();
        } catch (DukeException e) {
            Message.printError();
        }
    }

    /**
     * prints the current TaskList
     */
    public void printList() {
        Message.printHorizontalLine();
        System.out.println(listMessage);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1 + point);
            System.out.println(tasks.get(i));
        }
        Message.printHorizontalLine();
    }


    /**
     * marks task at index i as done and informs the user
     *
     * @param i
     */

    public void markItemAsDoneAndPrintDone(int i) {
        tasks.get(i - 1).markDone();
    }

    /**
     * marks task at index i as done
     *
     * @param i
     */

    public void markItemDone(int i) {
        tasks.get(i - 1).setMark();
    }


    /**
     * marks task at index i as undone
     *
     * @param i
     */
    public void unmarkItemDone(int i) {
        tasks.get(i - 1).unmarkDone();
    }


    /**
     * deletes index task from list
     *
     * @param index
     * @throws DukeException
     */
    public void deleteTask(int index) throws DukeException {
        try {
            Task taskToDelete = tasks.get(index - 1);
            tasks.remove(index - 1);
            Message.printTaskDeleted(taskToDelete);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException();
        }
    }

    /**
     * translated the input by user to a new task in the list
     *
     * @param input
     * @throws DukeException
     */

    public void translateTask(String input) throws DukeException {
        String[] divideByFirstSpace = input.split(space, 2);
        if (divideByFirstSpace.length < 2 || divideByFirstSpace[1].equals(emptyString)) {
            throw new DukeException();
        }
        switch (divideByFirstSpace[0]) {
            case deadline: {
                try {
                    String[] details = divideByFirstSpace[1].split(by);
                    AddDeadline(details[0].strip(), details[1].strip());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException();
                }
                break;
            }
            case event: {
                try {
                    String[] details = divideByFirstSpace[1].split(at);
                    AddEvent(details[0].strip(), details[1].strip());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException();
                }
                break;

            }
            case todo:
                AddTodo(divideByFirstSpace[1].strip());
                break;
            default:
                throw new DukeException();
        }

    }

    /**
     * adds a new deadline task
     *
     * @param description
     * @param byDescription
     * @throws DukeException
     */
    public static void AddDeadline(String description, String byDescription) throws DukeException {
        if (description.equals(emptyString) || byDescription.equals(emptyString)) {
            throw new DukeException();
        }
        tasks.add(new duke.task.Deadline(description, byDescription));

    }

    /**
     * adds a new event task to the list
     *
     * @param description
     * @param atDescription
     * @throws DukeException
     */
    public static void AddEvent(String description, String atDescription) throws DukeException {
        if (description.equals(emptyString) || atDescription.equals(emptyString)) {
            throw new DukeException();
        }
        tasks.add(new duke.task.Event(description, atDescription));
    }

    /**
     * adds a new todoTask to the list
     *
     * @param description
     */
    public static void AddTodo(String description) {
        tasks.add(new duke.task.Todo(description));

    }

    /**
     * prints to the user that a new task has been added
     */
    public void printTaskAdded() {
        Message.printHorizontalLine();
        System.out.println(taskAddedMessage);
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.printf(Message.amountOfTasksInList, tasks.size());
        Message.printHorizontalLine();
    }

}
