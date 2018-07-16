package view;

import java.util.Scanner;

public class Request {
    private String parameter;



    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String input() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
