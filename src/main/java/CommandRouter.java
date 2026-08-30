public class CommandRouter {

    public Command currentCommand;

    public void setCurrentCommand(String command) {
        String[] commandList = command.trim().split(" ");
        String cmd = commandList[0].toLowerCase();
        switch (cmd) {
            case "help":
                this.currentCommand = new CommandHelp();
                break;
            case "quick":
                this.currentCommand = new CommandQuickSort();
                break;
            case "selection":
                this.currentCommand = new CommandSelectionSort();
                break;
            case "bubblesorteven":
                this.currentCommand = new CommandBubbleSortEven();
                break;
            case "selectionsorteven":
                this.currentCommand = new CommandSelectionSortEven();
                break;
            case "bubble":
                this.currentCommand = new CommandBubbleSort();
                break;
            case "list":
                System.out.println("Команда list пока не реализована.");
                this.currentCommand = null;
                break;
            case "random":
                System.out.println("Команда random пока не реализована.");
                this.currentCommand = null;
                break;
            case "file":
                System.out.println("Команда file пока не реализована.");
                this.currentCommand = null;
                break;
            case "save":
                this.currentCommand = new CommandSaveToFile();
                break;
            default:
                this.currentCommand = null;
                break;
            // "exit" реализовал в AppData
            // case "exit":
            //     break;
            // To Do добавить другие команды
        }
    }
}
