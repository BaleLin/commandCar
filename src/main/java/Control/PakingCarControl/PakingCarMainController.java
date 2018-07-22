package Control.PakingCarControl;


import Control.*;
import modle.ParkingBoy;
import view.Request;
import view.Response;

public class PakingCarMainController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public PakingCarMainController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }


    @Override
    public String process() {
        response.send("1. 停车\n" +
                "2. 取车 \n" +
                "请输入您要进行的操作：");
        return "";
    }
}
