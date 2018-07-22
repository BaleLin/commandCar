package Control.PakingCarControl;


import Control.*;
import modle.ParkingBoy;
import view.Request;
import view.Response;

public class GoToPickUpController implements BaseController {

    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public GoToPickUpController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String process() {
        response.send("请输入小票编号：");
        return "";
    }

}
