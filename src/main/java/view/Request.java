package view;

import java.util.Scanner;

public class Request {
    public int inputOprateInstructions(){
        Scanner read = new Scanner(System.in);
        return read.nextInt();
    }
    public String inputCarId(){
        Scanner read = new Scanner(System.in);
        return read.nextLine();
    }

    public String inputReceiptNumber(){
        Scanner read = new Scanner(System.in);
        return read.nextLine();
    }
}
