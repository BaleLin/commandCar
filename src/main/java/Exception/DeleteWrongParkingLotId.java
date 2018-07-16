package Exception;

public class DeleteWrongParkingLotId extends RuntimeException{
    public DeleteWrongParkingLotId(String exceptionName){
        super(exceptionName);
    }
}
