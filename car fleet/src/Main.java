import java.util.*;

@Author(name = "Luka", email = "luka.kuzyk@student.tuke.sk") //ŠTRUKTURALNY VZOR 1:

// Composite (ServiceInfo-> Service-> Car) => ŠTRRUKTURALNY VZOR 2

//Návrhové vzory: Singelton (ServiceInfo), Factory(CarFactory);

public class Main {
    public static void main(String[] args) {

        // vytvoríme manažéra, ktorý bude zodpovedný za všetky funkcie flotily.
        Manager manager = new Manager("Luka", "Kuzyk", 19);

        // vypíšeme základné informácie o manažérovi
        manager.getManagerInfo();

        //vytvoríme servisnú knižku, ktorá obsahuje:
        // 1) kľúč - dátum
        // 2) hodnotu - popis servisnej udalosti a počet kilometrov;
        Map<String,ServiceInfo> ServiceBookCar1 = new LinkedHashMap<>();
        ServiceBookCar1.put("2017/03", new ServiceInfo("System diagnostics", 10000));
        ServiceBookCar1.put("2018/03", new ServiceInfo("Oil change", 20000));
        ServiceBookCar1.put("2019/03", new ServiceInfo("Car repair", 30000));
        ServiceBookCar1.put("2020/03", new ServiceInfo("Pneumatics change", 40000));
        ServiceBookCar1.put("2021/03", new ServiceInfo("Oil change", 50000));
        ServiceBookCar1.put("2021/09", new ServiceInfo("Minor diagnostics", 60000));
        ServiceBookCar1.put("2023/03", new ServiceInfo("Oil change", 70000));

        //Teraz pomocou modulu CarFactory pridáme do flotily ojazdené vozidlo.
        //Zapíšeme všetky potrebné údaje + servisnú knižku, ktorá bola vytvorená vyššie
        Car car1 = CarFactory.createCar("used", "BMW","M4", "red",
                "sedan", 'B',2016, 74319, 7, 26, ServiceBookCar1);

        //vytvorme vodiča.
        //Keďže je rovnako ako manažér odvodený od Person, v oboch prípadoch je potrebné uviesť name, surname a age.
        //Okrem toho musí vodič zadať údaje o svojom vodičskom preukaze (categoria, čislo a platnost).
        Driver driver = new Driver("Ištvan", "Rychlost", 44,
                'B', "RM8293812", "17/03/2025");

        //pridajme do vozidla vodiča
        manager.addDriverToCar(driver, car1);

        //Teraz vytvoríme ešte jedného vodiča s rovnakými údajmi.
        Driver driver2 = driver;

        //Skúsme teraz pridať nového vodiča do vozidla, ktoré už vodiča má.
        //Funkcia vypíše chybu, pretože toto auto už má jazdca.
        manager.addDriverToCar(driver2, car1);

        // Vypíšeme všetky základné informácie o vozidle
        // a všetky odporúčania, ktoré tento informačný systém poskytuje:
        // V prvých dvoch riadkoch sú uvedené technické informácie o aute.
        // V treťom riadku sa zobrazuje možný dojazd na základe aktuálneho množstva a spotreby paliva.
        // Štvrtý riadok zobrazuje informácie o tom, kedy je potrebné ísť na servisnú diagnostiku:
        //      Keďže trieda Car má konštantu SERVICE_TIME = 10000(km),
        //      znamená to, že servisná diagnostika by sa mala vykonávať každých 10 tisíc km.
        //      V riadku sa vezme aktuálny počet najazdených kilometrov vozidla a odpočítajú sa km,
        //      ktoré boli zaznamenané pri poslednej návšteve servisu. Potom sa odpočíta od SERVICE_TIME.
        //      A získa odporúčanie pre najbližšie servisné odporúčanie.
        // V piatom riadku sa na základe počtu najazdených kilometrov zobrazuje aktuálny stav vozidla.
        // Na konci sa v prípade existujúceho vodiča zobrazí jeho meno, priezvisko, vek,
        // údaje o vodičskom preukaze a počet kilometrov, ktoré tento vodič najazdil v rámci našich vozidiel.
        manager.carInfo(car1);

        //Funkcia vypíše všetky údaje zo servisnej knižky vozidla
        manager.readServiceBook(car1);

        //Každý objekt odvodený od Person môže riadiť a tankovať auto. (Manazer, Driver)
        //Pri dopĺňaní paliva do vozidla sa palivo doplní do objektu vozidla.
        //Kontroluje sa len to, či množstvo paliva nie je záporné
        // alebo väčšie ako samotná nádrž mínus skutočné množstvo paliva.
        //Na konci uvidíte, koľko paliva bolo pridané.

        //Počas jazdy si vždy môžete skontrolovať, či máte dostatok paliva na cestu.
        // Ak počas cesty prejde polovica kilometrov od poslednej servisnej prehliadky (SERVICE_TIME/2=5000 km),
        // spotreba paliva sa zvýši o 10 % (PERCENT_OF_CONSUMPTION_CHANGE) až do ďalšej servisnej prehliadky.
        //Prejdená vzdialenosť sa pripíše na účet vodiča.
        //Na konci uvidíte, koľko kilometrov bolo počas tejto cesty najazdených,
        // aktuálny počet kilometrov vozidla, aktuálne množstvo a spotrebu paliva.
        try {
            driver.tankCar(44, car1);
            driver.driveCar(800, car1);
            manager.tankCar(60, car1);
            manager.driveCar(800, car1);
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

        //vypíšeme údaje o vozidle po jazde (1800 km)
        //spotreba bola 7 l/100km a stala 7.7 l/100km lebo ot posldeneho servisa prejazdilo sa viac ako 5000 km.
        manager.carInfo(car1);

        //funkcia zaznamená novú servisnú diagnostiku do servisnej knižky
        //potom je možné vidieť, ako sa zmenili údaje o aute.
        // po service spotreba auta sa snižila do 7 l/100km
        manager.makeServiceDiagnostics("2023/05", "Full diagnostics", car1);

        // vypíšeme údaje o poslednom servise vozidla
        manager.readLastService(car1);

        //odstránime vodiča z vozidla.
        manager.removeDriverFromCar(car1);



        Map<String,ServiceInfo> ServiceBookCar2 = new LinkedHashMap<>();
        ServiceBookCar2.put("2023/05", ServiceInfo.getServiceInfo());
        Car car2 = CarFactory.createCar("new", "Ford","Focus", "black",
                "combi", 'B',2022, 1102, 6, 30, ServiceBookCar2);

        manager.carInfo(car2);
    }
}


//        Car car3 = new Car("Mercedes","E-class", "white",
//                "sedan", 'B',2019, 25000, 7, 30,202107);
//
//        Car car4 = new Car("Toyota","Supra", "Orange",
//                "sedan", 'B',2022, 16000, 6, 18,202301);

//        car1.makeServiceDiagnostics("2023/06", "Full diagnostics", car1.getMileage(), car1);
//        ServiceBookCar1.put("2017/05", ServiceInfo.getServiceInfo());
