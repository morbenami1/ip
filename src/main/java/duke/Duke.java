package duke;

public class Duke {
    protected static TaskList dukeList = new TaskList();
    public static final String markDone = "mark";
    public static final String delete = "delete";
    public static final String bye = "bye";
    public static final String list = "list";
    public static final String space = " ";
    public static final String unmarkDone = "unmark";
    public static final String filename = "duke.txt";

    public static void run() throws DukeException {
        Message.printLogo();
        Message.printGreeting();
        try {
            Storage.openOrCreateFile();
            Storage.uploadDataToList();
        } catch (DukeException e){
            Message.printSystemError();
        }
        Ui.input();
    }

    /**
     * calls the run function and starts the duke application
     * @param args
     */
    public static void main(String[] args)  {
        try {
            run();
        } catch (DukeException e) {
            Message.printUnknownError();
        }
    }
}
