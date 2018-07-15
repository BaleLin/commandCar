package Test;

import Control.Control;
import com.thoughtworks.tdd.ParkingBoy;
import modle.Car;
import modle.ParkingLot;
import modle.Receipt;
import org.fest.assertions.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import setUpValue.SetUpParkingboy;
import view.Request;
import view.Response;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.UUID;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.fest.assertions.api.Fail.fail;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestControl {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private String systemOut() {
        return outContent.toString();
    }

    @BeforeEach
    public void setup() {
       System.setOut(new PrintStream(outContent));
    }
    @Test
    public void should_ParkingLotList_return_4_length_given_apply_ParkingLotList(){
        SetUpParkingboy setUpParkingboy = new SetUpParkingboy();
        Control control = new Control(new ParkingBoy(setUpParkingboy.getParkingLotList()),new Response(),new Request());
        int length = control.getParkingBoy().getParkingLotList().size();
        assertThat(length,CoreMatchers.is(4));
    }

    @Test
    public void should_return_successful_message_given_the_order_is_1(){
        //give
        Request request = mock(Request.class);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        Control control = new Control(parkingBoy,new Response(),request);
        String stringReceipt = UUID.randomUUID().toString();
        Receipt receipt = new Receipt(stringReceipt);
        Car car = new Car("粤C8888");
        //when
        when(request.inputOprateInstructions()).thenReturn(1);
        when(request.inputCarId()).thenReturn("粤C8888");
        when(parkingBoy.park(car)).thenReturn(receipt);
        //then
        control.startOprate(1);
        verify(request).inputOprateInstructions();
        verify(request).inputCarId();
        verify(parkingBoy).park(car);
        Assertions.assertThat(systemOut()).contains("停车成功，您的小票是：\n您的取票号码是:"+stringReceipt);
    }
    @Test
    public void should_return_successful_message_given_the_order_is_2_and_the_receipt_is_error(){
        //given
        SetUpParkingboy setUpParkingboy = new SetUpParkingboy();
        Request request = mock(Request.class);
        Control control = new Control(new ParkingBoy(setUpParkingboy.getParkingLotList()),new Response(),request);
        when(request.inputOprateInstructions()).thenReturn(2);
        when(request.inputReceiptNumber()).thenReturn(UUID.randomUUID().toString());
         control.startOprate(1);
        verify(request).inputOprateInstructions();
        verify(request).inputReceiptNumber();
        Assertions.assertThat(systemOut()).contains("非法小票，无法取出车，请查证后再输");
    }

    @Test
    public void should_return_successful_message_given_the_order_is_1_and_2_and_the_receipt_is_right(){
        //give
        Request request = mock(Request.class);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        Control control = new Control(parkingBoy,new Response(),request);
        String stringReceipt = UUID.randomUUID().toString();
        Receipt receipt = new Receipt(stringReceipt);
        Car car = new Car("粤C8888");
        //when
        when(request.inputOprateInstructions()).thenReturn(1,2);
        when(request.inputCarId()).thenReturn("粤C8888");
        when(parkingBoy.park(car)).thenReturn(receipt);
        when(request.inputReceiptNumber()).thenReturn(stringReceipt);
        when(parkingBoy.unpark(receipt)).thenReturn(car);
        //then
        control.startOprate(2);
        verify(request).inputCarId();
        verify(request).inputReceiptNumber();
        verify(parkingBoy).park(car);
        verify(parkingBoy).unpark(receipt);
        Assertions.assertThat(systemOut()).contains("停车成功，您的小票是：\n您的取票号码是:"+stringReceipt);
        Assertions.assertThat(systemOut()).contains("车已取出，您的车牌号是:粤C8888");
    }

    @Test
    public void should_return_successful_message_given_the_order_is_1_and_2_and_1_when_the_receipt_is_right(){
        //give
        Request request = mock(Request.class);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        Control control = new Control(parkingBoy,new Response(),request);
        String stringReceipt = UUID.randomUUID().toString();
        Receipt receipt = new Receipt(stringReceipt);
        Car car = new Car("粤C8888");
        //when
        when(request.inputOprateInstructions()).thenReturn(1,2,1);
        when(request.inputCarId()).thenReturn("粤C8888","粤C8888");
        when(parkingBoy.park(car)).thenReturn(receipt,receipt);
        when(request.inputReceiptNumber()).thenReturn(stringReceipt,stringReceipt);
        when(parkingBoy.unpark(receipt)).thenReturn(car);
        //then
        control.startOprate(3);
        verify(parkingBoy).unpark(receipt);
        Assertions.assertThat(systemOut()).contains("停车成功，您的小票是：\n您的取票号码是:"+stringReceipt);
        Assertions.assertThat(systemOut()).contains("车已取出，您的车牌号是:粤C8888");
    }
}
