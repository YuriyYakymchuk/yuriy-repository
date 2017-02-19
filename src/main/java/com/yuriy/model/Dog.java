package com.yuriy.model;

/**
 * Created by yyakymchuk on 1/17/2017.
 */
public class Dog extends Pet {

    public Dog(PetType type) {
        this.type = type;
    }

    public Dog(double weight, String name, PetType type) {
        super(weight, name);
        this.type = type;
    }

    private PetType type;

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString() + "\n Dog{" +
                "type=" + type +
                '}';
    }
}
