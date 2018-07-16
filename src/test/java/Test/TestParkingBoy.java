package Test;

import modle.*;
import org.fest.assertions.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.fest.assertions.api.Fail.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import Exception.ParkLotException;
import Exception.DeleteWrongParkingLotId;
import Exception.deleteFailException;
public class TestParkingBoy {


    @Test
    public void should_parkingboy_can_park_be_Successfully_given_one_partking_lot_is_not_full() {
        ParkingLot parkingLot = mock(ParkingLot.class);
        List<ParkingLot> parkingLotlist = new ArrayList<>();
        parkingLotlist.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotlist);
        try {
            Car car = new Car("粤C8888");
            when(parkingLot.isFull()).thenReturn(false);
            parkingBoy.park(car);
           verify(parkingLot).park(car);
        } catch (ParkLotException exception) {
            fail("Should park sucessfully");
        }
    }


    @Test
    public void should_parkingboy_can_unpark_be_Successfully_given_one_partking_lot_is_not_full() {
        ParkingLot parkingLot = mock(ParkingLot.class);
        List<ParkingLot> parkingLotlist = new ArrayList<>();
        parkingLotlist.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotlist);
        Car car = new Car("粤C8888");
        Receipt receipt =  parkingBoy.park(car);
         when(parkingLot.isFull()).thenReturn(false);
        when(parkingLot.park(car)).thenReturn(receipt);
        when(parkingLot.unpark(receipt)).thenReturn(car);
        assertThat(parkingBoy.unpark(receipt),is(car));
        verify(parkingLot).isFull();
        verify(parkingLot).park(car);
        verify(parkingLot).unpark(receipt);
    }

    @Test
    public void should_parkingboy_can_park_Successfully_given_more_partking_lot_is_not_full() {
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        List<ParkingLot> parkingLotlist = new ArrayList<>();
        parkingLotlist.add(parkingLot1);
        parkingLotlist.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotlist);
        Car car1 = new Car("粤C8888");
        Car car2 = new Car("粤C6666");
        Receipt receipt1 = new Receipt(UUID.randomUUID().toString());
        Receipt receipt2 = new Receipt(UUID.randomUUID().toString());
        try {
            when(parkingLot1.isFull()).thenReturn(false,true);
            when(parkingLot1.park(car1)).thenReturn(receipt1);
            parkingBoy.park(car1);
            when(parkingLot2.isFull()).thenReturn(false);
            when(parkingLot2.park(car2)).thenReturn(receipt2);
            parkingBoy.park(car2);
            verify(parkingLot1).park(car1);
            verify(parkingLot2).park(car2);
        } catch (ParkLotException exception) {
            fail("Should park sucessfully");
        }
    }


    @Test
    public void should_parkingboy_can_park_Successfully_given_more_partking_lot_is_full() {
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        List<ParkingLot> parkingLotlist = new ArrayList<>();
        parkingLotlist.add(parkingLot1);
        parkingLotlist.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotlist);
        Car car1 = new Car("粤C8888");
        Car car2 = new Car("粤C6666");
        Receipt receipt1 = new Receipt(UUID.randomUUID().toString());
        Receipt receipt2 = new Receipt(UUID.randomUUID().toString());
        when(parkingLot1.isFull()).thenReturn(false,true);
        when(parkingLot1.park(car1)).thenReturn(receipt1);
        parkingBoy.park(car1);
        when(parkingLot2.isFull()).thenReturn(false);
        when(parkingLot2.park(car2)).thenReturn(receipt2);
        parkingBoy.park(car2);
        when(parkingLot1.unpark(receipt1)).thenReturn(car1);
        when(parkingLot2.unpark(receipt2)).thenReturn(car2);
        assertThat(parkingBoy.unpark(receipt1),is(car1));
        assertThat(parkingBoy.unpark(receipt2),is(car2));
        verify(parkingLot1).park(car1);
        verify(parkingLot2).park(car2);
        verify(parkingLot1).unpark(receipt1);
        verify(parkingLot2).unpark(receipt2);

    }


    @Test
    public void should_park_successfully_when_call_park_again_given_a_full_parking_lot_take_out_of_car() {
        ParkingLot parkingLot = mock(ParkingLot.class);
        List<ParkingLot> parkingLotlist = new ArrayList<>();
        parkingLotlist.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotlist);

        Car car1 = new Car("粤C8888");
        Receipt receipt1 = new Receipt(UUID.randomUUID().toString());
        when(parkingLot.isFull()).thenReturn(false);
        when(parkingLot.park(car1)).thenReturn(receipt1);
        parkingBoy.park(car1);

        when(parkingLot.unpark(receipt1)).thenReturn(car1);
        parkingBoy.unpark(receipt1);
        try {

            Car car2 = new Car("粤C5555");
            Receipt receipt2 = new Receipt(UUID.randomUUID().toString());
            when(parkingLot.isFull()).thenReturn(false);
            when(parkingLot.park(car2)).thenReturn(receipt2);
            parkingBoy.park(car2);
            when(parkingLot.unpark(receipt2)).thenReturn(car2);
            assertThat(parkingBoy.unpark(receipt2),is(car2));
            verify(parkingLot).park(car1);
            verify(parkingLot).park(car2);
            verify(parkingLot).unpark(receipt1);
            verify(parkingLot).unpark(receipt2);
        }catch (ParkLotException exception) {
            fail("Should park sucessfully");
        }
    }


    @Test
    public void should_parkingboy_can_park_order() {
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        List<ParkingLot> parkingLotlist = new ArrayList<>();
        parkingLotlist.add(parkingLot1);
        parkingLotlist.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotlist);
        Car car1 = new Car("粤C8888");
        Receipt receipt1 = new Receipt(UUID.randomUUID().toString());
        when(parkingLot1.isFull()).thenReturn(true);
        when(parkingLot2.isFull()).thenReturn(false);
        when(parkingLot2.park(car1)).thenReturn(receipt1);
        parkingBoy.park(car1);
        when(parkingLot2.unpark(receipt1)).thenReturn(car1);
        assertThat(parkingLot2.unpark(receipt1),is(car1));
        verify(parkingLot2).park(car1);
        verify(parkingLot2).unpark(receipt1);

    }
    @Test
    public void should_manage_can_add_be_parkingLot_Successfully_given_a_parkingLot() {
        List<ParkingLot> parkingLotlist = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotlist);
        parkingBoy.addParkingLot("东南停车场",40);
        parkingBoy.addParkingLot("东南停车场",40);
        parkingBoy.deleteParkingLot("001");
        parkingBoy.addParkingLot("东南停车场",40);
        ParkingLot newParkingLot = parkingLotlist.get(1);
        ParkingLot parkingLot = new ParkingLot("002","东南停车场",40,0,40);
        System.out.println(newParkingLot.getId());
    }
    @Test
    public void should_parkingLotList_return_true_given_a_null_parkingLotList() {
        List<ParkingLot> parkingLotlist = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotlist);
        assertThat(parkingBoy.isParkingLotListNull(),is(true));

    }

    @Test
    public void should_manage_delete_parkingLot_fail_given_a_null_parkingLotList() {
        List<ParkingLot> parkingLotlist = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotlist);
        try{
            parkingBoy.deleteParkingLot("002");
            fail("should not delete successful,but it successful!");
        }catch (deleteFailException exception){

        }
    }

    @Test
    public void should_manage_delete_parkingLot_successful_given_a_right_parkingLot_id() {
        List<ParkingLot> parkingLotlist = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotlist);
        parkingBoy.addParkingLot("东南停车场",40);
        parkingBoy.addParkingLot("东南停车场",40);
        try{
            parkingBoy.deleteParkingLot("001");
        }catch (deleteFailException exception){
            fail("should  delete successful,but it fail!");
        }
        assertThat(parkingLotlist.size(),is(1));
    }
    @Test
    public void should_manage_delete_parkingLot_fail_given_a_wrong_parkingLot_id() {
        List<ParkingLot> parkingLotlist = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotlist);
        parkingBoy.addParkingLot("东南停车场",40);
        try{
            parkingBoy.deleteParkingLot("002");
            fail("should  delete fail,but it successful!!");
        }catch (DeleteWrongParkingLotId exception){

        }
    }

    @Test
    public void should_manage_query_parkingLot_successful_given_a_not_null_parkingLot() {
        List<ParkingLot> parkingLotlist = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotlist);
        parkingBoy.addParkingLot("东南停车场",40);
        parkingBoy.addParkingLot("东南停车场",40);
        parkingBoy.addParkingLot("东北停车场",20);
Assertions.assertThat(parkingBoy.queryParkingLotList().toString()).contains(("|停车场ID|名称|车位|已停车辆|剩余车位|\n" +
        "======================================\n" +
        "|001|东南停车场|40(个)|0(辆)|40(个)|\n" +
        "|002|东南停车场|40(个)|0(辆)|40(个)|\n" +
        "|003|东北停车场|20(个)|0(辆)|20(个)|\n" +
        "总车位：100(个)\n" +
        "停车总量：0（辆）\n" +
        "总剩余车位：100(个)"));
    }

    @Test
    public void should_manage_query_a_right_message_given_a_not_null_parkingLot_park_again_() {
        List<ParkingLot> parkingLotlist = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotlist);
        parkingBoy.addParkingLot("东南停车场",2);
        parkingBoy.addParkingLot("东南停车场",2);
        parkingBoy.addParkingLot("东北停车场",2);
        parkingBoy.park(new Car("粤C888"));
        parkingBoy.park(new Car("粤C111"));
        parkingBoy.park(new Car("粤C222"));
        parkingBoy.park(new Car("粤C333"));
        Assertions.assertThat(parkingBoy.queryParkingLotList().toString()).contains("|停车场ID|名称|车位|已停车辆|剩余车位|\n" +
                "======================================\n" +
                "|001|东南停车场|2(个)|2(辆)|0(个)|\n" +
                "|002|东南停车场|2(个)|2(辆)|0(个)|\n" +
                "|003|东北停车场|2(个)|0(辆)|2(个)|\n" +
                "总车位：6(个)\n" +
                "停车总量：4（辆）\n" +
                "总剩余车位：2(个)");
    }

}
