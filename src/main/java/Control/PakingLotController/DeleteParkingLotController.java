package Control.PakingLotController;


import Control.BaseController;
import modle.ParkingBoy;
import view.Request;
import view.Response;
import Exception.*;
public class DeleteParkingLotController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public DeleteParkingLotController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }


    @Override
    public String process() {
        String command = request.getCommand();
        if(command!=null){
            try {
                if(parkingBoy.deleteParkingLot(command)){
                    response.send("停车场删除成功！");
                }
            }
            catch (DeleteWrongParkingLotId ex1){
                response.send("停车场添加失败，原因：此停车场不存在！");
            }catch (deleteFailException ex2){
                response.send("并没有停车场可供删除，删除失败！");
            }
            return "forward:firstLevelMain";
        }
        return "forward:firstLevelMain";
    }
}
