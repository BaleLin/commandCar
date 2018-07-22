package Control.PakingLotController;

import Control.BaseController;
import modle.ParkingBoy;
import view.Request;
import view.Response;

public class GotoQueryParkingLotController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public GotoQueryParkingLotController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String process() {

        String forwardPath = "";
        return forwardPath;
    }
}
