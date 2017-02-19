package com.yuriy.model;


import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * Created by yyakymchuk on 1/17/2017.
 */
public class Cat extends Pet implements Comparable<Cat>{

    private PetType type;

    public Cat(PetType type) {
        this.type = type;
    }

    public Cat(double weight, String name, PetType type) {
        super(weight, name);
        this.type = type;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    @Override
    public int compareTo(Cat o) {
        return ComparisonChain.start()
                .compare(this.getWeight(), o.getWeight())
                .compare(this.getName(), o.getName())
                .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cat)) return false;

        Cat cat = (Cat) o;

        return Objects.equal(this.getWeight(), cat.getWeight()) && Objects.equal(this.getName(), cat.getName());
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("Cat: ")
                .add("Weight", getWeight())
                .add("Name", getName())
                .toString();
    }
}
