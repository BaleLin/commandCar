package Control.PakingLotController;


import Control.BaseController;
import modle.Car;
import modle.ParkingBoy;
import modle.Receipt;
import view.Request;
import view.Response;

public class AddParkingLotController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public AddParkingLotController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }


    @Override
    public String process() {
        String command = request.getCommand();
        if(command!=null){
            String[] tempDate = command.split("，");
            parkingBoy.addParkingLot(tempDate[0],Integer.parseInt(tempDate[1]));
            response.send("停车场添加成功！");
            return "forward:firstLevelMain";
        }
        return "forward:firstLevelMain";
    }
}
