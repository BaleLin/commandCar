package modle;

import java.util.Objects;

public class Car {
    String cId;

    public Car(String cId) {
        this.cId = cId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(cId, car.cId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cId);
    }
}
