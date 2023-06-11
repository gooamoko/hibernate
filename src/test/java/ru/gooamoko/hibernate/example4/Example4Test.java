package ru.gooamoko.hibernate.example4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gooamoko.hibernate.example4.entity.Bus;
import ru.gooamoko.hibernate.example4.entity.Truck;
import ru.gooamoko.hibernate.example4.entity.Vehicle;
import ru.gooamoko.hibernate.example4.repository.BusRepository;
import ru.gooamoko.hibernate.example4.repository.TruckRepository;
import ru.gooamoko.hibernate.example4.repository.VehicleRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Example4Test {
    private final VehicleRepository vehicleRepository;
    private final BusRepository busRepository;
    private final TruckRepository truckRepository;

    @Autowired
    public Example4Test(VehicleRepository vehicleRepository, BusRepository busRepository, TruckRepository truckRepository) {
        this.vehicleRepository = vehicleRepository;
        this.busRepository = busRepository;
        this.truckRepository = truckRepository;
    }

    @AfterEach
    public void cleanup() {
        busRepository.deleteAll();
        truckRepository.deleteAll();
        vehicleRepository.deleteAll();
    }


    @Test
    public void testBusRepository() {
        List<Bus> buses = busRepository.findAll();
        assertTrue(buses.isEmpty());

        Bus testBus = new Bus();
        testBus.setVinCode("TEST_BUS_VINCODE");
        testBus.setSitesCount(40);

        testBus = busRepository.save(testBus);
        assertNotNull(testBus.getId());

        buses = busRepository.findAll();
        assertEquals(1, buses.size());

        List<Vehicle> vehicles = vehicleRepository.findAll();
        assertEquals(1, vehicles.size());
    }

    @Test
    public void testTruckRepository() {
        List<Truck> trucks = truckRepository.findAll();
        assertTrue(trucks.isEmpty());

        Truck testTruck = new Truck();
        testTruck.setVinCode("TEST_TRUCK_VINCODE");
        testTruck.setMaxWeight(5600);

        testTruck = truckRepository.save(testTruck);
        assertNotNull(testTruck.getId());

        trucks = truckRepository.findAll();
        assertEquals(1, trucks.size());

        List<Vehicle> vehicles = vehicleRepository.findAll();
        assertEquals(1, vehicles.size());
    }

    @Test
    public void testVehicleRepository() {
        Truck testTruck = new Truck();
        testTruck.setVinCode("TEST_BUS_VINCODE");
        testTruck.setMaxWeight(5600);
        truckRepository.save(testTruck);

        Bus testBus = new Bus();
        testBus.setVinCode("TEST_BUS_VINCODE");
        testBus.setSitesCount(40);
        busRepository.save(testBus);

        Vehicle testVegicle = new Vehicle();
        testVegicle.setVinCode("TEST_VINCODE");
        vehicleRepository.save(testVegicle);

        List<Vehicle> vehicles = vehicleRepository.findAll();
        assertEquals(3, vehicles.size());
    }
}