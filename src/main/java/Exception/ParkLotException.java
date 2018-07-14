package Exception;

public class ParkLotException extends RuntimeException{
    public ParkLotException(String parking_space_is_full){
        super(parking_space_is_full);
    }
}
