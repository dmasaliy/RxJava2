package com.example.rxjava2;

public class Car {
    private String brand;
    private int HP;

    public Car(String brand, int HP) {
        this.brand = brand;
        this.HP = HP;
    }

    public String getBrand() {
        return brand;
    }

    public int getHP() {
        return HP;
    }
}
