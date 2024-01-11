package com.tobeto.pair3.services.concretes;

import com.tobeto.pair3.core.utils.mapper.ModelMapperManager;
import com.tobeto.pair3.core.utils.mapper.ModelMapperService;
import com.tobeto.pair3.entities.Car;
import com.tobeto.pair3.entities.Rental;
import com.tobeto.pair3.repositories.CarRepository;
import com.tobeto.pair3.services.abstracts.ColorService;
import com.tobeto.pair3.services.abstracts.ModelService;
import com.tobeto.pair3.services.abstracts.RentalService;
import com.tobeto.pair3.services.dtos.requests.RentableRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarManagerTest {

    private ModelMapperManager modelMapperService = new ModelMapperManager(new ModelMapper());

    @Mock
    private CarRepository carRepository;

    @Mock
    private ModelService modelService;

    @Mock
    private ColorService colorService;

    private CarManager carManager;

    @BeforeEach
    void setUp() {
        carManager = new CarManager(carRepository,modelService,colorService,modelMapperService,null);
    }

    @Test
    void testIsReservableWithNoExistingRentals() {
        // Create a RentableRequest with appropriate data for testing
        RentableRequest rentableRequest = new RentableRequest();
        rentableRequest.setPickupLocation("LocationA");
        rentableRequest.setDropoffLocation("LocationB");
        rentableRequest.setPickupDate(LocalDate.now().plusDays(1));
        rentableRequest.setDropoffDate(LocalDate.now().plusDays(5));

        // Create a Car with appropriate data for testing
        Car car = new Car();
        car.setCurrentOffice("LocationA");

        // Create an empty Rental list
        List<Rental> existingRentals = new ArrayList<>();

        // Set the list of existing rentals to the car
        car.setRentals(existingRentals);
        when(carRepository.findById(5)).thenReturn(Optional.of(car));

        // Assume your method is part of RentalService class
        boolean result = carManager.isReservable(rentableRequest, 5);

        // Assert the result based on your expectations
        assertTrue(result, "Car should be reservable for the given RentableRequest with no existing rentals");
    }

    @Test
    void testIsReservableWithDifferentPickupLocation() {
        // Create a RentableRequest with appropriate data for testing
        RentableRequest rentableRequest = new RentableRequest();
        rentableRequest.setPickupLocation("LocationA");
        rentableRequest.setDropoffLocation("LocationB");
        rentableRequest.setPickupDate(LocalDate.now().plusDays(1));
        rentableRequest.setDropoffDate(LocalDate.now().plusDays(5));

        // Create a Car with appropriate data for testing
        Car car = new Car();
        car.setCurrentOffice("LocationC"); // Change pickup location

        // Create a Rental list with existing rentals
        List<Rental> existingRentals = new ArrayList<>();
        // Add existing rentals to the list (you can add specific rentals as needed)

        // Set the list of existing rentals to the car
        car.setRentals(existingRentals);
        when(carRepository.findById(5)).thenReturn(Optional.of(car));

        // Assume your method is part of RentalService class
        boolean result = carManager.isReservable(rentableRequest, 5);

        // Assert the result based on your expectations
        assertFalse(result, "Car should not be reservable for the given RentableRequest with different pickup location");
    }

    @Test
    void testIsReservableWithOneExistingRental() {
        // Create a RentableRequest with appropriate data for testing
        RentableRequest rentableRequest = new RentableRequest();
        rentableRequest.setPickupLocation("LocationA");
        rentableRequest.setDropoffLocation("LocationB");
        rentableRequest.setPickupDate(LocalDate.now().plusDays(1));
        rentableRequest.setDropoffDate(LocalDate.now().plusDays(5));

        // Create a Car with appropriate data for testing
        Car car = new Car();
        car.setCurrentOffice("LocationB");

        // Create an existing rental for the car
        Rental existingRental = new Rental();
        existingRental.setStartDate(LocalDate.now().minusDays(2));
        existingRental.setReturnDate(LocalDate.now().minusDays(1));
        existingRental.setPickupLocation("LocationB");
        existingRental.setDropOffLocation("LocationA");

        // Create a list with the existing rental
        List<Rental> existingRentals = Collections.singletonList(existingRental);

        // Set the list of existing rentals to the car
        car.setRentals(existingRentals);
        when(carRepository.findById(5)).thenReturn(Optional.of(car));

        // Assume your method is part of RentalService class
        boolean result = carManager.isReservable(rentableRequest, 5);

        // Assert the result based on your expectations
        assertTrue(result, "Car should be reservable for the given RentableRequest with one existing rental");
    }

    @Test
    void testIsReservableWithOneExistingRentalAndDifferentLocation() {
        // Create a RentableRequest with appropriate data for testing
        RentableRequest rentableRequest = new RentableRequest();
        rentableRequest.setPickupLocation("LocationA");
        rentableRequest.setDropoffLocation("LocationB");
        rentableRequest.setPickupDate(LocalDate.now().plusDays(1));
        rentableRequest.setDropoffDate(LocalDate.now().plusDays(5));

        // Create a Car with appropriate data for testing
        Car car = new Car();


        // Create an existing rental for the car
        Rental existingRental = new Rental();
        existingRental.setStartDate(LocalDate.now().minusDays(2));
        existingRental.setReturnDate(LocalDate.now().minusDays(1));
        existingRental.setPickupLocation("LocationA");
        existingRental.setDropOffLocation("LocationB");

        // Create a list with the existing rental
        List<Rental> existingRentals = Collections.singletonList(existingRental);

        // Set the list of existing rentals to the car
        car.setRentals(existingRentals);
        when(carRepository.findById(5)).thenReturn(Optional.of(car));

        // Assume your method is part of RentalService class
        boolean result = carManager.isReservable(rentableRequest, 5);

        // Assert the result based on your expectations
        assertFalse(result, "Car should not be reservable for the given RentableRequest with one existing rental");
    }

    @Test
    void testIsReservableWithOverlappingDates() {
        // Create a RentableRequest with appropriate data for testing
        RentableRequest rentableRequest = new RentableRequest();
        rentableRequest.setPickupLocation("LocationA");
        rentableRequest.setDropoffLocation("LocationB");
        rentableRequest.setPickupDate(LocalDate.now().plusDays(1));
        rentableRequest.setDropoffDate(LocalDate.now().plusDays(5));

        // Create a Car with appropriate data for testing
        Car car = new Car();
        car.setCurrentOffice("LocationA");

        // Create an existing rental with overlapping dates
        Rental existingRental = new Rental();
        existingRental.setStartDate(LocalDate.now().minusDays(2));
        existingRental.setReturnDate(LocalDate.now().plusDays(2)); // Overlapping dates
        existingRental.setPickupLocation("LocationB");
        existingRental.setDropOffLocation("LocationA");

        // Create a list with the existing rental
        List<Rental> existingRentals = Collections.singletonList(existingRental);

        // Set the list of existing rentals to the car
        car.setRentals(existingRentals);
        when(carRepository.findById(5)).thenReturn(Optional.of(car));

        // Assume your method is part of RentalService class
        boolean result = carManager.isReservable(rentableRequest, 5);

        // Assert the result based on your expectations
        assertFalse(result, "Car should not be reservable for the given RentableRequest with overlapping dates");
    }

    @Test
    void testIsReservableWithExistingRentalsAndGap() {
        // Create a RentableRequest with appropriate data for testing
        RentableRequest rentableRequest = new RentableRequest();
        rentableRequest.setPickupLocation("LocationB");
        rentableRequest.setDropoffLocation("LocationA");
        rentableRequest.setPickupDate(LocalDate.now().plusDays(1));
        rentableRequest.setDropoffDate(LocalDate.now().plusDays(5));

        // Create a Car with appropriate data for testing
        Car car = new Car();


        // Create two existing rentals with a gap in between
        Rental existingRental1 = new Rental();
        existingRental1.setStartDate(LocalDate.now().minusDays(8));
        existingRental1.setReturnDate(LocalDate.now().minusDays(6));
        existingRental1.setPickupLocation("LocationA");
        existingRental1.setDropOffLocation("LocationB");

        Rental existingRental2 = new Rental();
        existingRental2.setStartDate(LocalDate.now().plusDays(6));
        existingRental2.setReturnDate(LocalDate.now().plusDays(8));
        existingRental2.setPickupLocation("LocationA");
        existingRental2.setDropOffLocation("LocationB");

        // Create a list with the existing rentals
        List<Rental> existingRentals = Arrays.asList(existingRental1, existingRental2);

        // Set the list of existing rentals to the car
        car.setRentals(existingRentals);
        when(carRepository.findById(5)).thenReturn(Optional.of(car));

        // Assume your method is part of RentalService class
        boolean result = carManager.isReservable(rentableRequest, 5);

        // Assert the result based on your expectations
        assertTrue(result, "Car should be reservable for the given RentableRequest with a gap between existing rentals");
    }

    @Test
    void testIsReservableWithExistingRentalsAndAfterwards() {
        // Create a RentableRequest with appropriate data for testing
        RentableRequest rentableRequest = new RentableRequest();
        rentableRequest.setPickupLocation("LocationA");
        rentableRequest.setDropoffLocation("LocationB");
        rentableRequest.setPickupDate(LocalDate.now().plusDays(8)); // After existing rentals
        rentableRequest.setDropoffDate(LocalDate.now().plusDays(12)); // After existing rentals

        // Create a Car with appropriate data for testing
        Car car = new Car();
        car.setCurrentOffice("LocationA");

        // Create two existing rentals
        Rental existingRental1 = new Rental();
        existingRental1.setStartDate(LocalDate.now().minusDays(6));
        existingRental1.setReturnDate(LocalDate.now().minusDays(5));
        existingRental1.setPickupLocation("LocationA");
        existingRental1.setDropOffLocation("LocationB");

        Rental existingRental2 = new Rental();
        existingRental2.setStartDate(LocalDate.now().plusDays(2));
        existingRental2.setReturnDate(LocalDate.now().plusDays(4));
        existingRental2.setPickupLocation("LocationB");
        existingRental2.setDropOffLocation("LocationA");

        // Create a list with the existing rentals
        List<Rental> existingRentals = Arrays.asList(existingRental1, existingRental2);

        // Set the list of existing rentals to the car
        car.setRentals(existingRentals);
        when(carRepository.findById(5)).thenReturn(Optional.of(car));

        // Assume your method is part of RentalService class
        boolean result = carManager.isReservable(rentableRequest, 5);

        // Assert the result based on your expectations
        assertTrue(result, "Car should be reservable for the given RentableRequest after existing rentals");
    }

    @Test
    void testIsReservableWithExistingRentalsAndBefore() {
        // Create a RentableRequest with appropriate data for testing
        RentableRequest rentableRequest = new RentableRequest();
        rentableRequest.setPickupLocation("LocationB");
        rentableRequest.setDropoffLocation("LocationA");
        rentableRequest.setPickupDate(LocalDate.now().plusDays(1)); // Before existing rentals
        rentableRequest.setDropoffDate(LocalDate.now().plusDays(2)); // Before existing rentals

        // Create a Car with appropriate data for testing
        Car car = new Car();
        car.setCurrentOffice("LocationB");

        // Create two existing rentals
        Rental existingRental1 = new Rental();
        existingRental1.setStartDate(LocalDate.now().plusDays(3));
        existingRental1.setReturnDate(LocalDate.now().plusDays(4));
        existingRental1.setPickupLocation("LocationA");
        existingRental1.setDropOffLocation("LocationB");

        Rental existingRental2 = new Rental();
        existingRental2.setStartDate(LocalDate.now().plusDays(6));
        existingRental2.setReturnDate(LocalDate.now().plusDays(7));
        existingRental2.setPickupLocation("LocationB");
        existingRental2.setDropOffLocation("LocationA");

        // Create a list with the existing rentals
        List<Rental> existingRentals = Arrays.asList(existingRental1, existingRental2);

        // Set the list of existing rentals to the car
        car.setRentals(existingRentals);
        when(carRepository.findById(5)).thenReturn(Optional.of(car));

        // Assume your method is part of RentalService class
        boolean result = carManager.isReservable(rentableRequest, 5);

        // Assert the result based on your expectations
        assertTrue(result, "Car should be reservable for the given RentableRequest before existing rentals");
    }

    @Test
    void testIsReservableWithOverlappingDatesBetweenExistingRentals() {
        // Create a RentableRequest with appropriate data for testing
        RentableRequest rentableRequest = new RentableRequest();
        rentableRequest.setPickupLocation("LocationA");
        rentableRequest.setDropoffLocation("LocationB");
        rentableRequest.setPickupDate(LocalDate.now().plusDays(3)); // Overlapping dates between existing rentals
        rentableRequest.setDropoffDate(LocalDate.now().plusDays(6)); // Overlapping dates between existing rentals

        // Create a Car with appropriate data for testing
        Car car = new Car();
        car.setCurrentOffice("LocationA");

        // Create two existing rentals with a gap in between
        Rental existingRental1 = new Rental();
        existingRental1.setStartDate(LocalDate.now().minusDays(6));
        existingRental1.setReturnDate(LocalDate.now().minusDays(5));
        existingRental1.setPickupLocation("LocationA");
        existingRental1.setDropOffLocation("LocationB");

        Rental existingRental2 = new Rental();
        existingRental2.setStartDate(LocalDate.now().plusDays(2));
        existingRental2.setReturnDate(LocalDate.now().plusDays(4));
        existingRental2.setPickupLocation("LocationA");
        existingRental2.setDropOffLocation("LocationB");

        // Create a list with the existing rentals
        List<Rental> existingRentals = Arrays.asList(existingRental1, existingRental2);

        // Set the list of existing rentals to the car
        car.setRentals(existingRentals);
        when(carRepository.findById(5)).thenReturn(Optional.of(car));

        // Assume your method is part of RentalService class
        boolean result = carManager.isReservable(rentableRequest, 5);

        // Assert the result based on your expectations
        assertFalse(result, "Car should not be reservable for the given RentableRequest with overlapping dates between existing rentals");
    }

    @Test
    void testIsReservableWithMismatchedLocations() {
        // Create a RentableRequest with appropriate data for testing
        RentableRequest rentableRequest = new RentableRequest();
        rentableRequest.setPickupLocation("LocationA");
        rentableRequest.setDropoffLocation("LocationC"); // Mismatched drop-off location
        rentableRequest.setPickupDate(LocalDate.now().plusDays(3));
        rentableRequest.setDropoffDate(LocalDate.now().plusDays(6));

        // Create a Car with appropriate data for testing
        Car car = new Car();
        car.setCurrentOffice("LocationA");

        // Create two existing rentals with matching locations
        Rental existingRental1 = new Rental();
        existingRental1.setStartDate(LocalDate.now().plusDays(2));
        existingRental1.setReturnDate(LocalDate.now().plusDays(4));
        existingRental1.setPickupLocation("LocationA");
        existingRental1.setDropOffLocation("LocationB");

        Rental existingRental2 = new Rental();
        existingRental2.setStartDate(LocalDate.now().plusDays(6));
        existingRental2.setReturnDate(LocalDate.now().plusDays(8));
        existingRental2.setPickupLocation("LocationB");
        existingRental2.setDropOffLocation("LocationA"); // Mismatched drop-off location

        // Create a list with the existing rentals
        List<Rental> existingRentals = Arrays.asList(existingRental1, existingRental2);

        // Set the list of existing rentals to the car
        car.setRentals(existingRentals);
        when(carRepository.findById(5)).thenReturn(Optional.of(car));

        // Assume your method is part of RentalService class
        boolean result = carManager.isReservable(rentableRequest, 5);

        // Assert the result based on your expectations
        assertFalse(result, "Car should not be reservable for the given RentableRequest with mismatched drop-off location");
    }

    @Test
    void testIsReservableWithMultipleExistingRentalsAndGap() {
        // Create a RentableRequest with appropriate data for testing
        RentableRequest rentableRequest = new RentableRequest();
        rentableRequest.setPickupLocation("LocationB");
        rentableRequest.setDropoffLocation("LocationB");
        rentableRequest.setPickupDate(LocalDate.now().plusDays(10)); // After existing rentals
        rentableRequest.setDropoffDate(LocalDate.now().plusDays(14)); // After existing rentals

        // Create a Car with appropriate data for testing
        Car car = new Car();
        car.setCurrentOffice("LocationA");

        // Create four existing rentals with gaps in between
        Rental existingRental1 = new Rental();
        existingRental1.setStartDate(LocalDate.now().minusDays(8));
        existingRental1.setReturnDate(LocalDate.now().minusDays(7));
        existingRental1.setPickupLocation("LocationA");
        existingRental1.setDropOffLocation("LocationB");

        Rental existingRental4 = new Rental();
        existingRental4.setStartDate(LocalDate.now().plusDays(15)); // After existing rentals
        existingRental4.setReturnDate(LocalDate.now().plusDays(17)); // After existing rentals
        existingRental4.setPickupLocation("LocationB");
        existingRental4.setDropOffLocation("LocationA");

        Rental existingRental2 = new Rental();
        existingRental2.setStartDate(LocalDate.now().plusDays(2));
        existingRental2.setReturnDate(LocalDate.now().plusDays(4));
        existingRental2.setPickupLocation("LocationB");
        existingRental2.setDropOffLocation("LocationA");

        Rental existingRental3 = new Rental();
        existingRental3.setStartDate(LocalDate.now().plusDays(6));
        existingRental3.setReturnDate(LocalDate.now().plusDays(7));
        existingRental3.setPickupLocation("LocationA");
        existingRental3.setDropOffLocation("LocationB");



        // Create a list with the existing rentals
        List<Rental> existingRentals = Arrays.asList(existingRental1, existingRental2, existingRental3, existingRental4);

        // Set the list of existing rentals to the car
        car.setRentals(existingRentals);
        when(carRepository.findById(5)).thenReturn(Optional.of(car));

        // Assume your method is part of RentalService class
        boolean result = carManager.isReservable(rentableRequest, 5);

        // Assert the result based on your expectations
        assertTrue(result, "Car should be reservable for the given RentableRequest with gaps between existing rentals");
    }









}