package Test.controllerTest;
import Control.PakingCarControl.*;
import Control.PakingLotController.*;
import modle.Car;
import modle.ParkingBoy;
import modle.Receipt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Request;
import view.Response;
import Exception.* ;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
public class ParkingLotControllerTest {

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
    public void should_send_correct_page_info_when_add_parking_lot() {

        //given
        GotoAddParkingLotController controller = new GotoAddParkingLotController(request, response, parkingBoy);

        //when
        String forwardPath = controller.process();

        //then
        verify(response).send("请输入你套添加的停车场信息（格式为：名称，车位）:");
        assertThat(forwardPath, not(containsString("forward:")));
    }

    @Test
    public void should_send_correct_page_info_when_delete_parking_lot() {

        //given
        GotoDeleteParkingLotController controller = new GotoDeleteParkingLotController(request, response, parkingBoy);

        //when
        String forwardPath = controller.process();

        //then
        verify(response).send("请输入需要删除的被管理停车场ID:");
        assertThat(forwardPath, not(containsString("forward:")));
    }

    @Test
    public void should_add_parking_lot_successfully(){
        //given
        AddParkingLotController controller = new AddParkingLotController(request, response, parkingBoy);
        //when
        when(request.getCommand()).thenReturn("东南停车场，20");
        String forwardPath = controller.process();
        //then
        verify(response).send("停车场添加成功！");
        assertThat(forwardPath, is("forward:firstLevelMain"));
      }

    @Test
    public void should_delete_parking_lot_successfully(){
        //given
        DeleteParkingLotController controller = new DeleteParkingLotController(request, response, parkingBoy);
        //when
        when(request.getCommand()).thenReturn("001");
        when(parkingBoy.deleteParkingLot("001")).thenReturn(true);
        String forwardPath = controller.process();
        //then
        verify(response).send("停车场删除成功！");
        assertThat(forwardPath, is("forward:firstLevelMain"));
    }

    @Test
    public void should_delete_parking_lot_fail_given_a_worng_pakingLot_id(){
        //given
        DeleteParkingLotController controller = new DeleteParkingLotController(request, response, parkingBoy);
        //when
        when(request.getCommand()).thenReturn("123");
        when(parkingBoy.deleteParkingLot("123")).thenThrow(new DeleteWrongParkingLotId("have not this ParkingLot"));
        String forwardPath = controller.process();
        //then
        verify(response).send("停车场添加失败，原因：此停车场不存在！");
        assertThat(forwardPath, is("forward:firstLevelMain"));

    }
    @Test
    public void should_delete_parking_lot_fail_given_pakingLot_has_null(){
        //given
        DeleteParkingLotController controller = new DeleteParkingLotController(request, response, parkingBoy);
        //when
        when(request.getCommand()).thenReturn("123");
        when(parkingBoy.deleteParkingLot("123")).thenThrow(new deleteFailException("have not parkingLot to delete"));
        String forwardPath = controller.process();
        //then
        verify(response).send("并没有停车场可供删除，删除失败！");
        assertThat(forwardPath, is("forward:firstLevelMain"));
    }


}
