package Control;

import modle.ParkingBoy;
import view.Request;
import view.Response;

public class FirstLevelMainController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public FirstLevelMainController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }


    @Override
    public String process() {
        response.send("1. 停车服务\n" +
                "2. 停车场管理 \n" +
                "请输入您要进行的操作：");
        return "";
    }

}