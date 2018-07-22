package Control.PakingLotController;


import Control.BaseController;
import modle.ParkingBoy;
import view.Request;
import view.Response;

public class PakingLotMainController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public PakingLotMainController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }


    @Override
    public String process() {
        response.send("1. 查看停车场详情\n" +
                "2. 添加停车场 \n" +"3.删除停车场"+
                "请输入您要进行的操作：");
        return "";
    }
}
