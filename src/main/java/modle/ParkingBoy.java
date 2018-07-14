package com.thoughtworks.tdd;

import modle.Car;
import modle.ParkingLot;
import modle.Receipt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import Exception.ParkLotException;

public class ParkingBoy {
     List<ParkingLot> parkingLotList;


    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public void setParkingLotList(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public Receipt park(Car car) throws ParkLotException {
        List<ParkingLot> collect = parkingLotList.stream()
                                                .filter(x -> !x.isFull())
                                                .collect(Collectors.toList());
        if (collect.size() == 0) {
            throw new ParkLotException("all parkingLot is full");
        }
        return collect.get(0).park(car);
    }

    public Car unpark(Receipt recipt) {
        for (ParkingLot parkingLot : parkingLotList) {
            Car car = parkingLot.unpark(recipt);
            if (car != null) {
                return car;
            }
        }
        throw new RuntimeException("receipt invalid");
    }

    public boolean isFull(){
        List<ParkingLot> collect = parkingLotList.stream()
                .filter(x -> !x.isFull())
                .collect(Collectors.toList());
        if (collect.size() == 0)
            return true;
        else
            return false;
    }
}
