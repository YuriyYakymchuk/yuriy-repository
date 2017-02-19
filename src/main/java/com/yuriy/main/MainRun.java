package com.yuriy.main;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.yuriy.model.Cat;
import com.yuriy.model.Dog;
import com.yuriy.model.Pet;
import com.yuriy.model.PetType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yyakymchuk on 1/10/2017.
 */
public class MainRun {

    public static void main(String[] args) {
        Pet dog = new Dog(3.2, "Barsik", PetType.DOG);
        List<Pet> dogs = new ArrayList<Pet>();
        dogs.add(dog);
        dogs.add(new Dog(4.5, "Djeck", PetType.DOG));
        List<Cat> cats = Lists.transform(dogs, new Function<Pet, Cat>() {
            @Override
            public Cat apply(Pet pet) {
                Cat cat = new Cat(pet.getWeight(), pet.getName(), PetType.CAT);
                return cat;
            }
        });
        String joinedCats = Joiner.on("; ").join(dog.getName(), dog.getWeight());
        System.out.println(String.format("Dogs: %s", dogs));
        System.out.println(String.format("Cats: %s", cats));
        System.out.println(String.format("Joined cats: %s", joinedCats));
    }
}
