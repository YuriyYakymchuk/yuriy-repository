package com.yuriy.main;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import com.yuriy.model.Cat;
import com.yuriy.model.PetType;

/**
 * Created by yyakymchuk on 1/27/2017.
 */
public class GuavaMain {

	private static Function<String, Integer> STRING_TO_LENGTH = String::length;
	private static Function<String, String> TRANSFORM_FROM_STRING_TO_AAAA = s -> "AAA";

	private static Predicate<String> PREDICATE_AND = Predicates.and(Predicates.notNull(), s -> s.length() >= 1, s -> s.contains("a"));
	private static Predicate<String> PREDICATE_OR = Predicates.or(Predicates.notNull(), s -> s.length() >= 1, s -> s.contains("a"));
	private static Predicate<String> PREDICATE_NOT_IN = Predicates.not(Predicates.in(Lists.newArrayList("A", "Ca")));
	private static Predicate<Integer> PREDICATE_INTEGER_BIGGER_THAN_ONE = integer -> integer > 1;
	private static Predicate<String> PREDICATE__AND_COMPOSE = Predicates.and(Predicates.notNull(),
		Predicates.compose(PREDICATE_INTEGER_BIGGER_THAN_ONE, STRING_TO_LENGTH));
	private static Predicate<String> PREDICATE_COMPOSE = Predicates.compose(Predicates.and(integer -> integer > 1), s -> s.length());

    public static void main(String[] args) {

        functionalParadigm();
    }

	private static void functionalParadigm() {
		final Multimap<Integer, String> months = Multimaps.index(Lists.newArrayList("Jen", "Feb", "March"), STRING_TO_LENGTH);
		System.out.println(months);
		System.out.println("Transformed map: " + Multimaps.transformValues(months, TRANSFORM_FROM_STRING_TO_AAAA));
		List<String> list = Lists.newArrayList("A", "Ba", "Ca", null);
		System.out.println(list);
		System.out.println("Filtered list: " + Iterables.filter(list, PREDICATE__AND_COMPOSE));
	}

	private static void primitiveUtils() {
		List<Integer> ints = Ints.asList(1,2,3,4,5);
		System.out.println(ints);
		String joinedList = Ints.join("+", Ints.toArray(ints));
		System.out.println(joinedList);
	}
    private static void maps() {
        Iterable<String> strings = Sets.newHashSet("A", "Bb", "C", "January");
        ImmutableListMultimap<Integer,String> map = Multimaps.index(strings, new Function<String, Integer>() {
            public Integer apply(final String v) {
                return v.length();
            }
        });
        final ImmutableListMultimap<Integer,String> multimap = Multimaps.index(strings, String::length);
        ImmutableListMultimap<Integer,String> multimap1 = Multimaps.index(strings, s -> s.length());
        System.out.println(map);
        System.out.println(multimap1.inverse());
        System.out.println("Filtered keys" + Multimaps.filterKeys(multimap1, integer -> integer > 1));
        System.out.println("Filtered values" + Multimaps.filterValues(multimap1, string -> string.length() == 1));
        List<String> months = Lists.newArrayList("January", "February");
        Map<Integer, String> mapMonths = Maps.uniqueIndex(months, s -> s.length());
        System.out.println("Filtered map: " + Maps.filterKeys(mapMonths, multimap::containsKey));
        System.out.println("Transformed values: " + Multimaps.transformValues(multimap, s -> s.hashCode()));
	    Set<String> toys = Sets.newHashSet("Car", "Book");
	    toys.retainAll(Lists.newArrayList("Car"));
	    System.out.println("Retained toys: " + toys);
    }

    private static void iterable() {
        Iterable<Integer> ints = Lists.newArrayList(Iterables.concat(Ints.asList(1,2,2), Ints.asList(3,4,5,1)));
        List<String> strings = Lists.newArrayList("A");
        System.out.println(ints);
        System.out.println(String.format("Last element: %d", Iterables.getLast(ints)));
        System.out.println("Fourth element: " + Iterables.get(ints,3));
        System.out.println("Filtered ints: " + Iterables.filter(ints, integer -> integer != 2));
        System.out.println("Transformed ints: " + Iterables.transform(ints, integer -> 34.0));
        System.out.println("\nPartitioned ints: " + Iterables.partition(ints, 3));
        System.out.println(strings);
        System.out.println("Only element: " + Iterables.getOnlyElement(strings, "D"));

    }

    private static void collectionUtils() {
        Cat cat1 = new Cat(3.0, "Vasya", PetType.CAT);
        Cat cat2 = new Cat(4.0, "Ya", PetType.CAT);
        List<Cat> cats = Lists.newArrayListWithExpectedSize(2);
        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat1);
        System.out.println(cats);
    }

    private static void immutableCollections() {
        ImmutableSet<String> months = ImmutableSet.of("January", "Fabruary", "March");
        System.out.println(months);
        ImmutableSet<String> petTypes = ImmutableSet.<String>builder()
                .add("CAT", "DOG")
                .add("PIG")
                .build();
        System.out.println(petTypes);
    }
    private static void ordering() {
        Cat cat1 = new Cat(3.0, "Vasya", PetType.CAT);
        Cat cat2 = new Cat(4.0, "Ya", PetType.CAT);
        List<Cat> cats = new ArrayList<>();
        cats.add(cat1);
        cats.add(cat2);
        Ordering<Cat> catOrdering = Ordering.natural().nullsFirst().onResultOf( cat -> cat.getWeight());

        System.out.println(catOrdering.sortedCopy(cats));
        System.out.println(catOrdering.max(cats));
    }

    private static void commonObjectUtils() {
        Cat cat1 = new Cat(3.0, "Vasya", PetType.CAT);
        Cat cat2 = new Cat(3.0, null, PetType.CAT);
        System.out.println(cat1.equals(cat2));
        System.out.println(cat1);
        System.out.println(cat1.compareTo(cat2));
    }

    private static void preConditions() {
        checkArgument(2>3, "Expected %s > %s", 2, 3);
        checkElementIndex(2, 11, "Index is invalid");
    }

    private static void optionalTry() {
        Optional<Integer> optional = Optional.fromNullable(null);
        System.out.println(optional.or(2));
    }
}
