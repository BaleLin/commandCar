package modle;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import Exception.ParkLotException;
public class ParkingLot {
    int size;
    Map<Receipt, Car> receipt_car_map = new HashMap<>();
    String id;
    String name;
    int totalSpace;
    int existenceNumber;

    public ParkingLot(int size) {
        this.size = size;
    }

    public ParkingLot(String id) {
        this.id = id;
    }

    public ParkingLot(int size, Map<Receipt, Car> receipt_car_map, String id, String name, int totalSpace, int existenceNumber) {
        this.size = size;
        this.receipt_car_map = receipt_car_map;
        this.id = id;
        this.name = name;
        this.totalSpace = totalSpace;
        this.existenceNumber = existenceNumber;
    }

    public ParkingLot( String id, String name,int totalSpace,int existenceNumber,int size) {
        this.size = size;
        this.id = id;
        this.name = name;
        this.totalSpace = totalSpace;
        this.existenceNumber = existenceNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(int totalSpace) {
        this.totalSpace = totalSpace;
    }

    public int getExistenceNumber() {
        return existenceNumber;
    }

    public void setExistenceNumber(int existenceNumber) {
        this.existenceNumber = existenceNumber;
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
            this.setSize(--this.size);
            this.setExistenceNumber(++this.existenceNumber);
            return receipt;
        }
    }

    public Car unpark(Receipt recipt) {
       if(this.getReceipt_car_map().keySet().contains(recipt)){
            Car thiscar = this.getReceipt_car_map().get(recipt);
            this.getReceipt_car_map().remove(recipt);
            int curruntSize = this.getSize();
            this.setSize(++curruntSize);
            this.setExistenceNumber(--existenceNumber);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLot that = (ParkingLot) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
