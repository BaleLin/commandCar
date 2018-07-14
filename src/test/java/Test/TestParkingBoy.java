package Test;

import modle.Car;
import modle.ParkingLot;
import modle.Receipt;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.fest.assertions.api.Fail.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import Exception.ParkLotException;
import com.thoughtworks.tdd.ParkingBoy;
public class TestParkingBoy {

    //一个不满车场能够停车的情况
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

    //一个车场能够取车的情况
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
     //多个停车场都不满的情况下停车的情况
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

    //测试多个停车场取车的情况
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

    //测试停车取车再停车的情况
    @Test
    public void should_park_successfully_when_call_park_again_given_a_full_parking_lot_take_out_of_car() {
        ParkingLot parkingLot = mock(ParkingLot.class);
        List<ParkingLot> parkingLotlist = new ArrayList<>();
        parkingLotlist.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotlist);
        //停第一辆车
        Car car1 = new Car("粤C8888");
        Receipt receipt1 = new Receipt(UUID.randomUUID().toString());
        when(parkingLot.isFull()).thenReturn(false);
        when(parkingLot.park(car1)).thenReturn(receipt1);
        parkingBoy.park(car1);
        //取第一辆车
        when(parkingLot.unpark(receipt1)).thenReturn(car1);
        parkingBoy.unpark(receipt1);
        try {
            //存第二辆车
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

    //测试顺序
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


}
