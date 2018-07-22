package Control.PakingLotController;

import Control.BaseController;
import modle.ParkingBoy;
import view.Request;
import view.Response;

public class GotoDeleteParkingLotController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public GotoDeleteParkingLotController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String process() {

        String forwardPath = "";
        response.send("请输入需要删除的被管理停车场ID:");
        return forwardPath;
    }
}
