package com.yuriy.model;

/**
 * Created by yyakymchuk on 1/17/2017.
 */
public class Pet {

    private double weight;

    private String name;

    public Pet() {
    }

    public Pet(double weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\n Pet{" +
                "weight=" + weight +
                ", name='" + name + '\'' +
                '}';
    }
}
