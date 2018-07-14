package Control;

import modle.Car;
import modle.ParkingLot;
import modle.Receipt;
import view.Request;
import view.Response;
import com.thoughtworks.tdd.ParkingBoy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Control {
    ParkingBoy parkingBoy;
    Response response;
    Request request;
    public Control(ParkingBoy parkingBoy, Response response, Request request) {
        this.parkingBoy = parkingBoy;
        this.response = response;
        this.request = request;
    }

    public ParkingBoy getParkingBoy() {
        return parkingBoy;
    }


    public void startOprate(int flag) {
        while (flag>0){
            flag--;
            response.showOperateMessage();
            int inputOrder = request.inputOprateInstructions();
            if (inputOrder != 1 && inputOrder != 2) {
                response.showErrorOperateMessage();
            } else {
                switch (inputOrder) {
                    case 1: {
                        if (parkingBoy.isFull())
                            response.showParkFullMessage();
                        else {
                            response.showInputCarIdMessage();
                            Car car = new Car(request.inputCarId());
                            Receipt receipt = parkingBoy.park(car);
                            response.showParkSuccessfulMessage(receipt);
                        }
                    }
                    break;
                    case 2: {
                        response.showInputReciptNumberMessage();
                        String str = request.inputReceiptNumber();
                        try{
                            Car myCar = parkingBoy.unpark(new Receipt(str));
                            response.showUnpartCarMessage(myCar);
                        }catch (RuntimeException exception){
                            response.showtReciptNumberErrorMessage();
                        }

                    }
                    break;
                }


            }
        }

        }
    }


