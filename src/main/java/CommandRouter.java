public class CommandRouter {

    public Command currentCommand;

    public void setCurrentCommand(String command) {
        String[] commandList = command.trim().split(" ");
        switch(commandList[0]) {
            case "help":
                this.currentCommand = new CommandHelp();
                break;
            case "exit":
                break;
            // To Do добавить другие команды
        }
    }
}
