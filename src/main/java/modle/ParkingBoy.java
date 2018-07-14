//package com.thoughtworks.tdd;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public class ParkingBoy {
//     List<ParkingLot> parkingLotList;
//
//
//    public ParkingBoy(List<ParkingLot> parkingLotList) {
//        this.parkingLotList = parkingLotList;
//    }
//
//    public List<ParkingLot> getParkingLotList() {
//        return parkingLotList;
//    }
//
//    public void setParkingLotList(List<ParkingLot> parkingLotList) {
//        this.parkingLotList = parkingLotList;
//    }
//
//    public Receipt park(Car car) throws ParkLotException {
//        List<ParkingLot> collect = parkingLotList.stream()
//                                                .filter(x -> !x.isFull())
//                                                .collect(Collectors.toList());
//        if (collect.size() == 0) {
//            throw new ParkLotException("all parkingLot is full");
//        }
//        return collect.get(0).park(car);
//    }
//
//    public Car unpark(Receipt recipt) {
//        for (ParkingLot parkingLot : parkingLotList) {
//            Car car = parkingLot.unpark(recipt);
//            if (car != null) {
//                return car;
//            }
//        }
//        throw new RuntimeException("receipt invalid");
//    }
//
//    public boolean isFull() {
//        List<ParkingLot> collect = parkingLotList.stream()
//                                                .filter(x -> !x.isFull())
//                                                .collect(Collectors.toList());
//        return !(collect.size() > 0);
//    }
//}
