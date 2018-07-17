package view;

import java.util.Scanner;

public class Request {
    private String command;
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String input() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
