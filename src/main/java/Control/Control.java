package Control;

import modle.Car;
import modle.ParkingLot;
import modle.Receipt;

import java.util.Scanner;

public class Control{
    ParkingLot parkingLot = new ParkingLot(2);
    public void startOprate(){
        int flag = 0;
        while (flag == 0){
            Scanner read = new Scanner(System.in);

            int inputOrder = read.nextInt();
            if (inputOrder!=1&&inputOrder!=2){
                System.out.println("非法指令，请查证后再输");
            }else {
                switch (inputOrder){
                    case 1:{
                        Car car = new Car("粤C88888");
                        Receipt receipt = parkingLot.park(car);
                        System.out.println("停车成功，您的小票是：\n"+receipt.getReceiptNumber());
                    }
                    break;
                    case 2:{
                        System.out.println("请输入小票编号：");
                        Scanner inputTik = new Scanner(System.in);
                        String stee = inputTik.nextLine();
                        System.out.println("您输入的是:"+stee);
                        Car myCarNumber = parkingLot.unpark(new Receipt(stee));
                        System.out.println(myCarNumber.getcId());
                    }
                    break;

                }
            }
        }
    }
}
