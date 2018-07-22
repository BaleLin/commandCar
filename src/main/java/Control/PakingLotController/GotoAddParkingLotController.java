package Control.PakingLotController;

import Control.BaseController;
import modle.ParkingBoy;
import view.Request;
import view.Response;

public class GotoAddParkingLotController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public GotoAddParkingLotController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String process() {

        String forwardPath = "";
        response.send("请输入你套添加的停车场信息（格式为：名称，车位）:");
        return forwardPath;
    }
}
