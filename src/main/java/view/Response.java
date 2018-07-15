package view;

import modle.Car;
import modle.Receipt;

public class Response {
    public void showOperateMessage(){
        System.out.println("1. 停车\n" + "2. 取车 \n" + "请输入您要进行的操作：");
    }
    public void showErrorOperateMessage(){
        System.out.println("非法指令，请查证后再输");
    }
    public void showParkSuccessfulMessage(Receipt receipt){
        System.out.println("停车成功，您的小票是：\n您的取票号码是:"+receipt.getReceiptNumber());
    }
    public void showInputCarIdMessage(){
        System.out.println("请输入车牌号码：");
    }
    public void showInputReciptNumberMessage(){
        System.out.println("请输入小票编号：");
    }
    public void showUnpartCarMessage(Car car){
        System.out.println("车已取出，您的车牌号是:"+car.getcId());
    }
    public void showParkFullMessage(){
        System.out.println("对不起，车已经停满了");
    }

    public void showtReciptNumberErrorMessage(){
        System.out.println("非法小票，无法取出车，请查证后再输");
    }
}