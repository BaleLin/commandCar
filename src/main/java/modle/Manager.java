package modle;

import java.text.Format;
import java.util.List;
import Exception.deleteFailException;
import Exception.DeleteWrongParkingLotId;
public class Manager {
    List<ParkingLot> parkingLotList;

    public Manager(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public void setParkingLotList(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

//    public void addParkingLot(String name,int totalSpace){
//        String id = getParkingLotId();
//
//        ParkingLot parkingLot = new ParkingLot(id,name,totalSpace,0,totalSpace);
//        parkingLotList.add(parkingLot);
//    }
//
//    private String getParkingLotId() {
//        String id ="";
//        if (parkingLotList.size()==0){
//            id = String.format("%03d",parkingLotList.size()+1);
//        }else {
//            int lastIndex = parkingLotList.size()-1;
//            int intId = Integer.parseInt(parkingLotList.get(lastIndex).getId());
//            id = String.format("%03d",intId+1);
//        }
//        return id;
//    }
//
//    public void deleteParkingLot(String id) throws deleteFailException,DeleteWrongParkingLotId {
//         if(this.isParkingLotListNull()){
//             throw new deleteFailException("have not parkingLot to delete");
//         }else {
//             if (parkingLotList.contains(new ParkingLot(id))){
//                 parkingLotList.remove(new ParkingLot(id));
//             }else {
//                 throw new DeleteWrongParkingLotId("have not this ParkingLot");
//             }
//
//         }
//    }
//
//    public StringBuffer queryParkingLotList(){
//        StringBuffer queryString = new StringBuffer();
//        queryString.append("|停车场ID|名称|车位|已停车辆|剩余车位|\n");
//        queryString.append("======================================\n");
//        int sumTotalSpace = 0;
//        int sumExistenceNumber = 0;
//        int sumSize = 0;
//        for(ParkingLot parkingLot:parkingLotList){
//            String tempString = String.format("|%s|%s|%d(个)|%d(辆)|%d(个)|\n",parkingLot.getId(),parkingLot.getName(),parkingLot.getTotalSpace(),parkingLot.getExistenceNumber(),parkingLot.getSize());
//            queryString.append(tempString);
//            sumTotalSpace += parkingLot.getTotalSpace();
//            sumExistenceNumber += parkingLot.getExistenceNumber();
//            sumSize += parkingLot.getSize();
//        }
//        queryString.append( "总车位："+String.valueOf(sumTotalSpace)+"(个)\n");
//        queryString.append( "停车总量："+String.valueOf(sumExistenceNumber)+"（辆）\n");
//        queryString.append( "总剩余车位："+String.valueOf(sumSize)+"(个)\n");
//        return queryString;
//    }
//    public boolean isParkingLotListNull(){
//        if(parkingLotList.size()==0)
//            return true;
//        else
//            return false;
//    }
}