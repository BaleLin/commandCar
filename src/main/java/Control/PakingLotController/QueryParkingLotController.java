package Control.PakingLotController;


import Control.BaseController;
import modle.Car;
import modle.ParkingBoy;
import modle.ParkingLot;
import modle.Receipt;
import view.Request;
import view.Response;

public class QueryParkingLotController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public QueryParkingLotController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }


    @Override
    public String process() {
        StringBuffer queryString = new StringBuffer();
        queryString.append("|停车场ID|名称|车位|已停车辆|剩余车位|\n");
        queryString.append("======================================\n");
        int sumTotalSpace = 0;
        int sumExistenceNumber = 0;
        int sumSize = 0;
        for(ParkingLot parkingLot:parkingBoy.queryParkingLotList()){
            String tempString = String.format("|%s|%s|%d(个)|%d(辆)|%d(个)|\n",parkingLot.getId(),parkingLot.getName(),parkingLot.getTotalSpace(),parkingLot.getExistenceNumber(),parkingLot.getSize());
            queryString.append(tempString);
            sumTotalSpace += parkingLot.getTotalSpace();
            sumExistenceNumber += parkingLot.getExistenceNumber();
            sumSize += parkingLot.getSize();
        }
        queryString.append( "总车位："+String.valueOf(sumTotalSpace)+"(个)\n");
        queryString.append( "停车总量："+String.valueOf(sumExistenceNumber)+"（辆）\n");
        queryString.append( "总剩余车位："+String.valueOf(sumSize)+"(个)\n");
        response.send(queryString.toString());
        return "forward:firstLevelMain";
    }
}
