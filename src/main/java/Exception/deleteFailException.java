package Exception;

public class deleteFailException extends RuntimeException{
    public deleteFailException(String delete_parkingLot_is_fail){
        super(delete_parkingLot_is_fail);
    }
}