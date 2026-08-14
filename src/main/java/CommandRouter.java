public class CommandRouter {
    //класс который выбирает текущую команду, паттерн стратегия
    public Command currentCommand;

    public void setCurrentCommand(String command) {
        String[] commandList = command.trim().split(" ");
        switch(commandList[0]) {
            case "help":
                this.currentCommand = new CommandHelp();
                break;
            case "exit":
                break;
            // To Do Другие команды, в последнюю очередь, чтобы не было конфликтов
        }
    }
}
