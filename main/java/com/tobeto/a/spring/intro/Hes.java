package com.tobeto.a.spring.intro;

import com.tobeto.a.spring.intro.entities.Car;
import com.tobeto.a.spring.intro.entities.Rental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Hes {

    public static void main(String[] args) {

        LocalDate date1=LocalDate.now();
        LocalDate date2=date1.plusDays(1);

        System.out.println(date1.isAfter(date2));

    }



    public static boolean isReservable(LocalDate date1, LocalDate date2, List<LocalDate> dateList) {
        List<LocalDate> newDateList = new ArrayList<>();
        boolean isReserved = false;
        long daysBetween = ChronoUnit.DAYS.between(date1, date2);
        for (int i = 0; i < daysBetween+1; i++) {
            LocalDate newDate = date1.plusDays(i);
            newDateList.add(newDate);
        }

        for (int i = 0; i < dateList.size(); i++) {
            for (int j = 0; j < newDateList.size(); j++) {
                if (newDateList.get(j).isEqual(dateList.get(i))) {
                    isReserved = true;
                    break;
                }
            }
        }

        if (isReserved) {
            System.out.println("Can't reserve");
            return false;
        } else {
            System.out.println("Can be reserved");
            return true;
        }
    }

    public static List<LocalDate> collectReservationDays(LocalDate date1, LocalDate date2){

        List<LocalDate> newDateList1 = new ArrayList<>();

        long daysBetween = ChronoUnit.DAYS.between(date1, date2);
        for (int i = 0; i < daysBetween+1; i++) {
            LocalDate newDate = date1.plusDays(i);
            newDateList1.add(newDate);
        }
        return  newDateList1;
    }

    public static boolean isReservable(LocalDate rentalDate, LocalDate returnDate, String startCarSupplier, String finishCarSupplier, Car car) {


        Rental rental1=null;
        Rental rental2=null;
        boolean isSuitable=false;



        List<Rental> rentals=car.getRentals();
        if(rentals.size()==0)return true;

        Collections.sort(rentals, Comparator.comparing(Rental::getRentalDate));



            for (int i = 0; i <rentals.size() ; i++) {

                if(rentals.get(i).getRentalDate().isAfter(rentalDate) &&rentals.get(i).getReturnDate().isBefore(returnDate)){
                    rental1=car.getRentals().get(i);
                    rental2=car.getRentals().get(i);

                    break;
                }
                if(i==rentals.size()-2 && rentals.get(i).getReturnDate().isAfter(returnDate) ){

                    rental2=rentals.get(i);
                    break;
                }
            }
            if(rental1.equals(null)){if (rental2.getFinishCarSupplier().equals(startCarSupplier))isSuitable=true;}
            else {
                if (rental1.getFinishCarSupplier().equals(startCarSupplier) &&rental2.getStartCarSupplier().equals(finishCarSupplier) )isSuitable=true;
            }

            return isSuitable;



    }
}
