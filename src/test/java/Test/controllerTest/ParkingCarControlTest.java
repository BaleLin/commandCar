package Test.controllerTest;

import Control.PakingCarControl.*;
import modle.Car;
import modle.ParkingBoy;
import modle.Receipt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Request;
import view.Response;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

class ParkingCarControlTest {


    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;

    @BeforeEach
    void setUp() {
        request = mock(Request.class);
        response = mock(Response.class);
        parkingBoy = mock(ParkingBoy.class);

    }

    @Test
    public void should_send_correct_page_info_when_parking_lot_not_full() {

        //given
       GotoParkingController controller = new GotoParkingController(request, response, parkingBoy);
        when(parkingBoy.isFull()).thenReturn(false);

        //when
        String forwardPath = controller.process();

        //then
        verify(response).send("请输入车牌号:");
        assertThat(forwardPath, not(containsString("forward:")));
    }
    @Test
    public void should_print_main_page(){
        //given
        PakingCarMainController controller = new PakingCarMainController(request, response, parkingBoy);
        //when
        controller.process();

        //then
        verify(response).send("1. 停车\n" +
                "2. 取车 \n" +
                "请输入您要进行的操作：");
    }
    @Test
    public void should_send_wrong_info_and_forward_main_page_when_parking_lot_is_full() {
        //given
        when(parkingBoy.isFull()).thenReturn(true);
        GotoParkingController controller = new GotoParkingController(request, response, parkingBoy);
        //when
        String forwardPath = controller.process();

        //then
        verify(response).send("车库已满，无法停车！");
        assertThat(forwardPath, containsString("forward:"));
    }

    @Test
    public void should_print_unpark_page() {
        //given
        //when
        GoToPickUpController controller = new GoToPickUpController(request, response, parkingBoy);
        String forwardPath = controller.process();

        //then
        verify(response).send("请输入小票编号：");
        assertThat(forwardPath, not(containsString("forward:")));
    }

    @Test
    public void should_park_successfully(){
        //given
        String ticketStr = UUID.randomUUID().toString();
        ParkingController controller = new ParkingController(request, response, parkingBoy);
        Receipt ticket = new Receipt(ticketStr);
        when(parkingBoy.park(any(Car.class))).thenReturn(ticket);

        //when
        String forwardPath = controller.process();

        //then
        verify(response).send("停车成功，您的小票是：\n" +
                ticketStr);
        assertThat(forwardPath, is("forward:firstLevelMain"));

    }

    @Test
    public void should_print_pick_up_success_info_when_recipt_id_is_exist_in_parking_lot() {

        //given
        PickUpController controller = new PickUpController(request, response, parkingBoy);
        String ticketStr = UUID.randomUUID().toString();
        Receipt receipt = new Receipt(ticketStr);
        when(request.getCommand()).thenReturn(ticketStr);
        Car car = new Car("abc123");
        when(parkingBoy.unpark(receipt)).thenReturn(car);

        //when
        String forwardPath = controller.process();

        //then
        verify(response).send("车已取出，您的车牌号是: " + car.getcId());
        assertThat(forwardPath, is("forward:firstLevelMain"));
    }

    @Test
    public void should_sent_warong_msg_when_recipt_ID_is_not_exist_in_parking_lot() {

        //given
        PickUpController controller = new PickUpController(request, response, parkingBoy);
        when(request.getCommand()).thenReturn(UUID.randomUUID().toString());
        when(parkingBoy.unpark(any())).thenReturn(null);

        //when
        String forwardPath = controller.process();

        //then
        verify(response).send("非法小票，无法取出车，请查证后再输");
        assertThat(forwardPath, is("forward:firstLevelMain"));
    }
}