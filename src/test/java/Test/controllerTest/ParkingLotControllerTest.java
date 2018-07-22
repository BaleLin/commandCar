package Test.controllerTest;
import Control.PakingCarControl.*;
import Control.PakingLotController.GotoAddParkingLotController;
import Control.PakingLotController.GotoDeleteParkingLotController;
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

}
