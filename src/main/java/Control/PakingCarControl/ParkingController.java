package Control.PakingCarControl;


import Control.*;
import modle.Car;
import modle.ParkingBoy;
import modle.Receipt;
import view.Request;
import view.Response;

public class ParkingController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public ParkingController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }


    @Override
    public String process() {
        String cardNum = request.getCommand();

        Receipt receipt = parkingBoy.park(new Car(cardNum));

        response.send("停车成功，您的小票是：\n" +
                receipt.getReceiptNumber());
        return "forward:firstLevelMain";
    }
}
