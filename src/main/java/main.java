
import Control.Control;
import jdk.nashorn.internal.parser.Scanner;
import modle.ParkingLot;

import view.Response;
import view.user;
import view.Request;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import modle.ParkingBoy;
import Control.Router;
public class main {
    public  static void main(String[] args) {

        ParkingLot parkingLot = new ParkingLot("001","东南停车场",20,0,20);
        ArrayList <ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        Request request = new Request();
        Response response = new Response();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Control control = new Control(parkingBoy,response);
        String currentPage = "main";
        Router router = new Router(currentPage,control);
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        while(true){
            router.toPage();
            String command  = scanner.nextLine();
            request.setParameter(command);
            router.handleRequest(request);
        }
    }
    }

