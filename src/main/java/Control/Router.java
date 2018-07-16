package Control;
import view.Request;
import view.Response;

public class Router {
    private String currentPage;
    private Control controller;

    public Router(String currentPage, Control controller) {
        this.currentPage = currentPage;
        this.controller = controller;
    }

    public void handleRequest(Request request) {
        switch (currentPage) {
            case "main":
                currentPage = controller.handleFirstMenuParkOrder(request);
                break;
                case "park":
                currentPage = controller.parkCar(request);
                break;
                case "unPark":
                currentPage = controller.unParkCar(request);
                break;
               case "secondParkMenumain":
                currentPage = controller.handleSecondParkMenumain(request);
                break;
            case "addPakingLot":
                currentPage = controller.addPakingLot(request);
                break;
            case "deletePakingLot":
                currentPage = controller.deletePakingLot(request);
                break;
              case "secondServiceMenumain":
                currentPage = controller.handleSecondServiceMenumain(request);
                break;
        }
    }

    public void toPage() {
        if(currentPage.equals("main")){
            controller.toMainPage();
        }
    }
}
