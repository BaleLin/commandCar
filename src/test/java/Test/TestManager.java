//package Test;
//
//import modle.Car;
//import modle.Manager;
//import modle.ParkingLot;
//import org.fest.assertions.core.Assert;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import com.thoughtworks.tdd.ParkingBoy;
//import static org.fest.assertions.api.Fail.fail;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;
//import Exception.deleteFailException;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import Exception.DeleteWrongParkingLotId;
//
//public class TestManager {
//
//    @Test
//    public void should_manage_can_add_be_parkingLot_Successfully_given_a_parkingLot() {
//        List<ParkingLot> parkingLotlist = new ArrayList<>();
//        Manager manager = new Manager(parkingLotlist);
//        manager.addParkingLot("东南停车场",40);
//        manager.addParkingLot("东南停车场",40);
//        manager.deleteParkingLot("001");
//        manager.addParkingLot("东南停车场",40);
//        ParkingLot newParkingLot = parkingLotlist.get(1);
//        ParkingLot parkingLot = new ParkingLot("002","东南停车场",40,0,40);
//        //assertThat(newParkingLot,is(parkingLot));
//        System.out.println(newParkingLot.getId());
//     }
//    @Test
//    public void should_parkingLotList_return_true_given_a_null_parkingLotList() {
//        List<ParkingLot> parkingLotlist = new ArrayList<>();
//        Manager manager = new Manager(parkingLotlist);
//        assertThat(manager.isParkingLotListNull(),is(true));
//
//    }
//
//    @Test
//    public void should_manage_delete_parkingLot_fail_given_a_null_parkingLotList() {
//        List<ParkingLot> parkingLotlist = new ArrayList<>();
//        Manager manager = new Manager(parkingLotlist);
//        try{
//            manager.deleteParkingLot("002");
//            fail("should not delete successful,but it successful!");
//        }catch (deleteFailException exception){
//
//        }
//    }
//
//    @Test
//    public void should_manage_delete_parkingLot_successful_given_a_right_parkingLot_id() {
//        List<ParkingLot> parkingLotlist = new ArrayList<>();
//        Manager manager = new Manager(parkingLotlist);
//        manager.addParkingLot("东南停车场",40);
//        manager.addParkingLot("东南停车场",40);
//        try{
//            manager.deleteParkingLot("001");
//        }catch (deleteFailException exception){
//            fail("should  delete successful,but it fail!");
//        }
//        assertThat(parkingLotlist.size(),is(1));
//    }
//    @Test
//    public void should_manage_delete_parkingLot_fail_given_a_wrong_parkingLot_id() {
//        List<ParkingLot> parkingLotlist = new ArrayList<>();
//        Manager manager = new Manager(parkingLotlist);
//        manager.addParkingLot("东南停车场",40);
//        try{
//            manager.deleteParkingLot("002");
//            fail("should  delete fail,but it successful!!");
//        }catch (DeleteWrongParkingLotId exception){
//
//        }
//    }
//
//    @Test
//    public void should_manage_query_parkingLot_successful_given_a_not_null_parkingLot() {
//        List<ParkingLot> parkingLotlist = new ArrayList<>();
//        Manager manager = new Manager(parkingLotlist);
//        manager.addParkingLot("东南停车场",40);
//        manager.addParkingLot("东南停车场",40);
//        manager.addParkingLot("东北停车场",20);
//        System.out.println(manager.queryParkingLotList());
//    }
//
//    @Test
//    public void should_manage_query_a_right_message_given_a_not_null_parkingLot_park_again_() {
//        List<ParkingLot> parkingLotlist = new ArrayList<>();
//        Manager manager = new Manager(parkingLotlist);
//        manager.addParkingLot("东南停车场",2);
//        manager.addParkingLot("东南停车场",2);
//        manager.addParkingLot("东北停车场",2);
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLotlist);
//        parkingBoy.park(new Car("粤C888"));
//        parkingBoy.park(new Car("粤C111"));
//        parkingBoy.park(new Car("粤C222"));
//        parkingBoy.park(new Car("粤C333"));
//        System.out.println(manager.queryParkingLotList());
//    }
//
//}
