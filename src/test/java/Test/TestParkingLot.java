package Test;



import modle.Car;
import modle.ParkingLot;
import modle.Receipt;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.not;

import static org.fest.assertions.api.Fail.fail;
import Exception.ParkLotException;
public class TestParkingLot {
    @Test
    public void should_park_successfully_given__partking_lot_is_not_full(){
        ParkingLot parkinglot =new ParkingLot(1);
        try{
            parkinglot.park(new Car("粤C8888"));
           }catch (ParkLotException exception){
            fail("Should park sucessfully,but no");
        }
    }

    @Test
    public void should_park_fail_given_partking_lot_is_full()  {
        ParkingLot parkinglot = new ParkingLot(0);
        try{
            parkinglot.park(new Car("粤C8888"));
            fail("should not park sucessfully");
        }catch (ParkLotException exception){

        }
    }

    @Test
    public void should_unpark_successfully_given_receipt_is_right(){

        Car myCar = new Car("粤C8888");
        ParkingLot parkingLot = new ParkingLot(1);
        Receipt recipt = parkingLot.park(myCar);
        Car car = parkingLot.unpark(recipt);
        assertThat((car), is(myCar));
    }
    @Test
    public void should_unpark_fail_given_receipt_is_worng(){
        ParkingLot parkingLot = new ParkingLot(1);
        Car mycar = new Car("粤C8888");
        parkingLot.park(mycar);
        Receipt otherReceipt = new Receipt(UUID.randomUUID().toString());
        Car car = parkingLot.unpark(otherReceipt);
        assertThat((car), not(mycar));
    }

    @Test
    public void should_unpark_successfully_given_receipt_is_right_and_receipt_object_is_different_but_receiptNumber_is_same(){

        Car myCar = new Car("粤C8888");
        ParkingLot parkingLot = new ParkingLot(1);
        Receipt receipt = parkingLot.park(myCar);
        Receipt different_object_reciprt = new Receipt(receipt.getReceiptNumber());//创建一个新的对象，但跟receipt的号码一样
        Car car = parkingLot.unpark(different_object_reciprt);
        assertThat((car), is(myCar));
    }

    @Test
    public void should_unpark_fail_given_receipt_is_right_and_receipt_object_is_different_but_receiptNumber_is_different(){

        Car myCar = new Car("粤C8888");
        ParkingLot parkingLot = new ParkingLot(1);
        Receipt receipt = parkingLot.park(myCar);
        Receipt different_object_reciprt = new Receipt(UUID.randomUUID().toString());//创建一个新的对象，但跟receipt的号码是不一样的
        Car car = parkingLot.unpark(different_object_reciprt);
        assertThat((car), not(myCar));
    }

    @Test
    public void should_park_be_false_given_Parking_lot_is_full(){
        ParkingLot parkingLot = new ParkingLot(0);
        assertThat(parkingLot.isFull(),is(true));
    }

    @Test
    public void should_park_be_ture_given_Parking_lot_is_no_full(){
        ParkingLot parkingLot = new ParkingLot(1);
        assertThat(parkingLot.isFull(),is(false));
    }



    @Test
    public  void should_park_be_Successfully_given_a_full_parking_take_out_car_and_park_car() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("粤C8888");
        Receipt receipt = parkingLot.park(car);
        parkingLot.unpark(receipt);
        try {
            parkingLot.park(new Car("粤C9999"));
        } catch (ParkLotException exception) {
            fail("sholud Successfully");
        }
    }
        @Test
        public  void should_park_be_fail_given_a__full_parking_take_out_car_and_take_out_again(){
            ParkingLot parkingLot = new ParkingLot(1);
            Car car = new Car("粤C8888");
            Receipt receipt = parkingLot.park(car);
            parkingLot.unpark(receipt);
            assertThat((parkingLot.unpark(receipt)),equalTo(null));
    }

    @Test
    public  void should_return_a_ture_number_given_park_two_car(){
        ParkingLot parkingLot = new ParkingLot("001","东南停车场",40,0,40);
        Car car = new Car("粤C8888");
        Car car2 = new Car("粤C6666");
         parkingLot.park(car);
         parkingLot.park(car2);
         int[] actualValue = {parkingLot.getTotalSpace(),parkingLot.getExistenceNumber(),parkingLot.getSize()};
         int[] expectedValue = {40,2,38};
         assertThat(actualValue,equalTo(expectedValue));

    }

    @Test
    public  void should_return_a_ture_number_given_park_two_car_and_then_unpark(){
        ParkingLot parkingLot = new ParkingLot("001","东南停车场",40,0,40);
        Car car = new Car("粤C8888");
        Car car2 = new Car("粤C6666");
        parkingLot.park(car);
        Receipt receipt = parkingLot.park(car2);
        parkingLot.unpark(receipt);
        int[] actualValue = {parkingLot.getTotalSpace(),parkingLot.getExistenceNumber(),parkingLot.getSize()};
        int[] expectedValue = {40,1,39};
        assertThat(actualValue,equalTo(expectedValue));

    }
}