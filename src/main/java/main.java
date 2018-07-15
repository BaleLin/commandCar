import Control.Control;
import modle.Car;
import modle.ParkingLot;
import modle.Receipt;
import setUpValue.SetUpParkingboy;
import view.Request;
import view.Response;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class main {
    public  static void main(String[] args){

        SetUpParkingboy setUpParkingboy = new SetUpParkingboy();
       Control control = new Control(new com.thoughtworks.tdd.ParkingBoy(setUpParkingboy.getParkingLotList()),new Response(),new Request());
       control.startOprate(18);

    }
    }
