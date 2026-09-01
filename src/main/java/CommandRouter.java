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
            case "bubble":
                this.currentCommand = new CommandBubbleSort();
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
            case "quicksorteven":
                this.currentCommand = new CommandQuickSortEven();
                break;
            case "list":
                this.currentCommand = new CommandList();
                break;
            case "add":
                this.currentCommand = new CommandAdd();
                break;
            case "random":
                this.currentCommand = new CommandRandom();
                break;
            case "randomswap":
                this.currentCommand = new CommandRandomSwap();
                break;
            case "loadFromFile":
                this.currentCommand = new CommandLoadFromFile();
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
