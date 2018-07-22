package Control.PakingCarControl;


import Control.*;
import modle.Car;
import modle.ParkingBoy;
import modle.Receipt;
import view.Request;
import view.Response;

import java.util.UUID;

public class PickUpController implements BaseController {

    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public PickUpController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String process() {
        try {
            String reciptID = request.getCommand();

            Car car = parkingBoy.unpark(new Receipt(reciptID));

            if (car == null) {
                response.send("非法小票，无法取出车，请查证后再输");
            } else {
                response.send("车已取出，您的车牌号是: " + car.getcId());
            }

        } catch (IllegalArgumentException ex) {
            response.send("非法Recipt ID，无法取出车，请查证后再输");
        } finally {
            return "forward:firstLevelMain";
        }

    }


}
