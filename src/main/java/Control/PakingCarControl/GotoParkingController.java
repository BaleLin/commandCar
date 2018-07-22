package Control.PakingCarControl;

import Control.*;
import modle.ParkingBoy;
import view.Request;
import view.Response;
public class GotoParkingController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public GotoParkingController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String process() {

        String forwardPath = "";
        if (parkingBoy.isFull()) {
            response.send("车库已满，无法停车！");
            forwardPath = "forward:pakingCarMain";
        } else {
            response.send("请输入车牌号:");
        }
        return forwardPath;
    }
}
