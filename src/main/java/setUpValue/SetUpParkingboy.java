package setUpValue;

import modle.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class SetUpParkingboy {
    public static final int N = 4;
    public List<ParkingLot> getParkingLotList() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            ParkingLot parkingLot = new ParkingLot(4);
            parkingLotList.add(parkingLot);
        }
        return parkingLotList;
    }
}
