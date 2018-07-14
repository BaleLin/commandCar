package modle;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import Exception.ParkLotException;
public class ParkingLot {
    int size;
    Map<Receipt, Car> receipt_car_map = new HashMap<>();

    public ParkingLot(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<Receipt, Car> getReceipt_car_map() {
        return receipt_car_map;
    }

    public void setReceipt_car_map(Map<Receipt, Car> receipt_car_map) {
        this.receipt_car_map = receipt_car_map;
    }

    public Receipt park(Car car) throws ParkLotException {
        if (this.isFull()){
            throw new ParkLotException("parking_space_is_full");
        }else{
            Receipt receipt = new Receipt(UUID.randomUUID().toString());
            receipt_car_map.put(receipt,car);
            int curruntSize = this.getSize();
            this.setSize(curruntSize--);
            return receipt;
        }
    }

    public Car unpark(Receipt recipt) {
       if(this.getReceipt_car_map().keySet().contains(recipt)){
            Car thiscar = this.getReceipt_car_map().get(recipt);
            this.getReceipt_car_map().remove(recipt);
            int curruntSize = this.getSize();
            this.setSize(curruntSize++);
            return  thiscar;
        }
        else {
            return null;
        }
    }


    public Boolean isFull() {
        if(this.getSize()==0){
            return true;
        }else {
            return false;
        }
    }
}
