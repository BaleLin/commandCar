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
        SetUpParkingboy setUpParkingboy = new SetUpParkingboy();
        Request request = mock(Request.class);
        Control control = new Control(new ParkingBoy(setUpParkingboy.getParkingLotList()),new Response(),request);
       //when
        when(request.inputOprateInstructions()).thenReturn(1);
        when(request.inputCarId()).thenReturn("粤C8888");
        //then
        control.startOprate(1);
        verify(request).inputOprateInstructions();
        verify(request).inputCarId();
        Assertions.assertThat(systemOut()).contains("停车成功，您的小票是：\n您的取票号码是:");
    }
    @Test
    public void should_return_successful_message_given_the_order_is_2_and_the_receipt_is_error(){
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
}
