package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class ParkTest {
    @Test
    public void should_return_username_and_weclome_when_remain_space_greater_than_zero_and_add_a_car() {
        //given
        ParkSpace parkSpace = new ParkSpace(10);
        Car car = new Car(1, "Terry");
        //when
        String result = parkSpace.addCar(car);
        //then
        //then
        assertThat(result, is("Terry,Welcome to spark!"));

    }

    @Test
    public void should_return_sorry_when_remain_space_less_than_zero_and_add_a_car() {
        //given
        ParkSpace parkSpace = new ParkSpace(1);
        Car carId_1 = new Car(1, "Terry");
        Car carId_2 = new Car(2, "Carol");
        //when
        parkSpace.addCar(carId_1);
        String result = parkSpace.addCar(carId_2);
        //then
        assertThat(result, is("Sorry,There is no vacant parking space here!"));

    }
    @Test
    public  void should_return_full_sub_when_pick_up_the_car(){
        //given
        ParkSpace parkSpace = new ParkSpace(10);
        Car car = new Car(1, "Terry");
        parkSpace.addCar(car);
        //when
        parkSpace.pickUpCar(car);
        int result = parkSpace.getNumofVacancy();
        //then
        assertThat(result, is(10));
    }

}
