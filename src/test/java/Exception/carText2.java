//package com.thoughtworks.tdd;
//
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.fest.assertions.api.Fail.fail;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.not;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//public class carText2 {
//
//    @Test
//    public void should_parkingboy_can_park_be_Successfully_given_partking_lot_is_not_full() {
//        ParkingLot parkingLot = mock(ParkingLot.class);
//        List<ParkingLot> parkingLotlist = new ArrayList<>();
//        parkingLotlist.add(parkingLot);
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLotlist);
//        try {
//            Car car = new Car();
//           when(parkingLot.isFull()).thenReturn(false);
//           when(parkingLot.park(car)).thenReturn(new Receipt());
//           parkingBoy.park(car);
//           verify(parkingLot).park(car);
//        } catch (ParkLotException exception) {
//            fail("Should park sucessfully");
//        }
//    }
//
//    @Test
//    public void should_parkingboy_should_park_be_Fail_given_partking_lot_is__full() {
//        ParkingLot parkingLot = mock(ParkingLot.class);
//        List<ParkingLot> parkingLotlist = new ArrayList<>();
//        parkingLotlist.add(parkingLot);
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLotlist);
//        Car car = new Car();
//        try {
//            when(parkingLot.isFull()).thenReturn(true);
//            when(parkingLot.park(car)).thenReturn(new Receipt());
//            parkingBoy.park(car);
//            verify(parkingLot).park(car);
//            fail("Should no park,but sucessfully");
//        } catch (ParkLotException exception) {
//
//        }
//    }
//
//
//    @Test
//    public void should_parkingboy_can_park_Successfully_given_more_partking_lot_is_not_full() {
//        ParkingLot parkingLot1 = mock(ParkingLot.class);
//        ParkingLot parkingLot2 = mock(ParkingLot.class);
//        List<ParkingLot> parkingLotlist = new ArrayList<>();
//        parkingLotlist.add(parkingLot1);
//        parkingLotlist.add(parkingLot2);
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLotlist);
//        Car car1 = new Car();
//        Car car2 = new Car();
//        try {
//            when(parkingLot1.isFull()).thenReturn(false);
//            when(parkingLot2.isFull()).thenReturn(false);
//               when(parkingLot1.park(car1)).thenReturn(new Receipt());
//            when(parkingLot2.park(car2)).thenReturn(new Receipt());
//            parkingBoy.park(car1);
//            parkingBoy.park(car2);
//        } catch (ParkLotException exception) {
//            fail("Should park sucessfully");
//        }
//    }
//
//
//    @Test
//    public void should_park_successfully_when_call_park_again_given_a_full_parking_lot_take_out_of_car() {
//        ParkingLot parkingLot = mock(ParkingLot.class);
//        List<ParkingLot> parkingLotlist = new ArrayList<>();
//        parkingLotlist.add(parkingLot);
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLotlist);
//        Car car = new Car();
//        Receipt receipt = new Receipt();
//        when(parkingLot.park(car)).thenReturn(receipt);
//        Receipt thisreceipt = parkingBoy.park(car);
//        when(parkingLot.unpark(thisreceipt)).thenReturn(car);
//        parkingBoy.unpark(thisreceipt);
//        try {
//            Receipt secondReceipt = new Receipt();
//            Car car2 = new Car();
//             when(parkingLot.park(car2)).thenReturn(secondReceipt);
//           parkingBoy.park(car2);
//            verify(parkingLot).park(car);
//           verify(parkingLot).park(car2);
//        } catch (ParkLotException e) {
//            fail("should park successfully");
//        }
//    }
//    @Test
//    public void should_parkingboy_can_park_order_when__given_more_partking_lot__is_not_full() {
//        ParkingLot parkingLot1 = new ParkingLot(1);
//        ParkingLot parkingLot2 = new ParkingLot(1);
//        List<ParkingLot> parkingLotlist = new ArrayList<>();
//        parkingLotlist.add(parkingLot1);
//        parkingLotlist.add(parkingLot2);
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLotlist);
//        Car car1 = new Car();
//        Receipt recipt1 = parkingBoy.park(car1);
//        assertThat(parkingLotlist.get(0).unpark(recipt1), is(car1));
//
//    }
//
//
//}
