package ru.gooamoko.hibernate.example2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gooamoko.hibernate.example2.entity.Device;
import ru.gooamoko.hibernate.example2.entity.Vendor;
import ru.gooamoko.hibernate.example2.repository.DevicesRepository;
import ru.gooamoko.hibernate.example2.repository.VendorsRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class Example2Test {
    private final DevicesRepository devicesRepository;
    private final VendorsRepository vendorsRepository;

    @Autowired
    public Example2Test(DevicesRepository devicesRepository, VendorsRepository vendorsRepository) {
        this.devicesRepository = devicesRepository;
        this.vendorsRepository = vendorsRepository;
    }


    @AfterEach
    public void cleanup() {
        // Удаляем всё оборудование
        devicesRepository.deleteAll();

        // Удаляем всех производителей
        vendorsRepository.deleteAll();
    }


    @Test
    @DisplayName("Проверка работоспособности репозиториев и сущностей")
    public void testClassesAndRepositoriesWorks() {
        // Пробуем получить список всех производителей
        List<Vendor> vendors = vendorsRepository.findAll();
        assertNotNull(vendors);
        assertTrue(vendors.isEmpty());

        // Создаем и сохраняем производителя
        Vendor testVendor = new Vendor();
        testVendor.setName("Samsung");
        testVendor.setSite("http://www.samsung.com");
        testVendor = vendorsRepository.save(testVendor);
        assertNotNull(testVendor.getId());

        // Пробуем получить список всего оборудования
        List<Device> devices = devicesRepository.findAll();
        assertNotNull(devices);
        assertTrue(devices.isEmpty());

        //Создаем и сохраняем оборудование
        Device testDevice = new Device();
        testDevice.setName("Монитор");
        testDevice.setModel("SyncMaster 3Ne");
        testDevice.setVendor(testVendor);
        testDevice.setDamaged(false);
        testDevice.setDescription("14-ти дюймовый монитор. Всё еще работает!");
        testDevice.setInventoryNumber("1998-1243768");
        testDevice = devicesRepository.save(testDevice);
        assertNotNull(testDevice.getId());

        // Проверяем, что у нас теперь одно устройство
        devices = devicesRepository.findAll();
        assertEquals(1, devices.size());

        // Проверяем, что у нас один производитель
        vendors = vendorsRepository.findAll();
        assertEquals(1, vendors.size());
    }
}