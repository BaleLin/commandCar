package Control;

import modle.Car;
import modle.ParkingLot;
import modle.Receipt;
import view.Request;
import view.Response;
import modle.ParkingBoy;
import Exception.DeleteWrongParkingLotId;
import Exception.deleteFailException;
public class Control {
    private ParkingBoy parkingBoy;
    private Response response;

    public Control(ParkingBoy parkingBoy, Response response) {
        this.parkingBoy = parkingBoy;
        this.response = response;
    }

    public ParkingBoy getParkingBoy() {
        return parkingBoy;
    }

    public Response getResponse() {
        return response;
    }


    public void toMainPage() {
        response.send("1. 停车服务\n" +
                "2. 停车场管理 \n" +
                "请输入您要进入的页面：");
    }

    public String toSecondParkMenumainPage() {
        response.send("1. 停车\n" +
                "2. 取车 \n" +
                "请输入您要进入的页面：");
        return "secondParkMenumain";
    }
    public String toSecondServiceMenumainPage() {
        response.send("1. 查看停车场详情\n" +
                "2. 添加停车场 \n" +
                "3. 删除停车场 \n" +
                "请输入您要进入的页面：");
        return "secondServiceMenumain";
    }

    public String handleFirstMenuParkOrder(Request request) {
        String command = request.getParameter();
        switch (command) {
            case "1":
                return toSecondParkMenumainPage();
            case "2":
                return toSecondServiceMenumainPage();
            default:
                response.send("非法指令，请查证后再输");
                return "main";
        }
    }
    public String handleSecondParkMenumain(Request request) {
        String command = request.getParameter();
        switch (command) {
            case "1":
                return toParkCarPage();
            case "2":
                return toUnParkPage();
            default:
                response.send("非法指令，请查证后再输");
                return "main";
        }
    }
    public String handleSecondServiceMenumain(Request request) {
        String command = request.getParameter();
        switch (command) {
            case "1":
                return queryPakingLot();
            case "2":
                return toAddPakingLotPage();
            case "3":
                return toDeletePakingLotPage();
            default:
                response.send("非法指令，请查证后再输");
                return "main";
        }
    }

    private String toUnParkPage() {
        response.send("请输入小票编号：");
        return "unPark";
    }

    private String toParkCarPage() {
        if (parkingBoy.isFull()) {
            response.send("车已停满，请晚点再来");
            return "main";
        } else {
            response.send("请输入车牌号:");
            return "park";
        }
    }
    private String toAddPakingLotPage() {
            response.send("请输入你套添加的停车场信息（格式为：名称，车位）:");
            return "addPakingLot";
    }
    private String toDeletePakingLotPage() {
        response.send("请输入需要删除的被管理停车场ID:");
        return "deletePakingLot";
    }
    public String parkCar(Request request) {
        String command = request.getParameter();
        Receipt receipt = parkingBoy.park(new Car(command));
        response.send("停车成功，您的小票是：\n" + receipt.getReceiptNumber());
        return "main";
    }
    public String unParkCar(Request request) {
        String command = request.getParameter();
        Car car = parkingBoy.unpark(new Receipt(command));
        if (car == null) {
            response.send("非法小票，无法取出车，请查证后再输");
        } else {
            response.send("车已取出，您的车牌号是: " + car.getcId());
        }
        return "main";
    }

    public String addPakingLot(Request request) {
        String command = request.getParameter();
        if(command!=null){
            String[] tempDate = command.split("，");
            parkingBoy.addParkingLot(tempDate[0],Integer.parseInt(tempDate[1]));
            response.send("停车场添加成功！");
            return "main";
        }
        return "main";
    }
    public String deletePakingLot(Request request) {
        String command = request.getParameter();
        if(command!=null){
            try {
                parkingBoy.deleteParkingLot(command);
                response.send("停车场删除成功！");
            }
           catch (DeleteWrongParkingLotId ex1){
               response.send("停车场添加失败，原因：此停车场不存在！");
            }catch (deleteFailException ex2){
                response.send("并没有停车场可供删除，删除失败");
            }
            return "main";
        }
        return "main";
    }


    public String queryPakingLot() {
        response.send(""+parkingBoy.queryParkingLotList());
        return "main";
    }
//    public String addParkCar(Request request) {
//        String command = request.getParameter();
//        Receipt receipt = parkingBoy.addParkingLot();
//        response.send("停车成功，您的小票是：\n" + receipt.getReceiptNumber());
//        return "main";
//    }
}


