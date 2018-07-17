
import Control.PakingCarControl.*;
import modle.ParkingLot;
import Control.*;
import view.Response;
import view.Request;

import java.util.ArrayList;
import java.util.Scanner;

import modle.ParkingBoy;
import Control.Router;
import Control.PakingLotController.*;
public class main {


    public static Scanner cliInput = new Scanner(System.in);

    public static void main(String[] args) {

        String initRootPath = "firstLevelMain";
        Request request = new Request();
        Router router = initRouter(initRootPath, request);
        router.launch();

        try {
            while (true) {
                String command = cliInput.nextLine();
                request.setCommand(command);
                router.processRequest(request);
            }
        } catch (Exception ex) {
            System.out.println("\n App Exist, cause from route not exist!");
        } finally {
            cliInput.close();
        }
    }

    private static Router initRouter(String currentPage, Request request) {
        ParkingLot parkingLot = new ParkingLot("001","东南停车场",20,0,20);
        ArrayList <ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy boy = new ParkingBoy(parkingLots);
        Response response = new Response();
         Router router = new Router(currentPage);
        router.registerRoute("firstLevelMain", new FirstLevelMainController(request, response, boy));
        router.registerRoute("firstLevelMain/1", new PakingCarMainController(request, response, boy));
        router.registerRoute("firstLevelMain/1/1", new GotoParkingController(request, response, boy));
        router.registerRoute("firstLevelMain/1/2", new GoToPickUpController(request, response, boy));
        router.registerRoute("firstLevelMain/1/1/*", new ParkingController(request, response, boy));
        router.registerRoute("firstLevelMain/1/2/*", new PickUpController(request, response, boy));

        router.registerRoute("firstLevelMain/2", new PakingLotMainController(request, response, boy));
        router.registerRoute("firstLevelMain/2/1", new QueryParkingLotController(request, response, boy));
        router.registerRoute("firstLevelMain/2/2", new GotoAddParkingLotController(request, response, boy));
        router.registerRoute("firstLevelMain/2/3", new GotoDeleteParkingLotController(request, response, boy));
        //router.registerRoute("firstLevelMain/2/1/*", new QueryParkingLotController(request, response, boy));
        router.registerRoute("firstLevelMain/2/2/*", new AddParkingLotController(request, response, boy));
        router.registerRoute("firstLevelMain/2/3/*", new DeleteParkingLotController(request, response, boy));
        return router;
    }
}


