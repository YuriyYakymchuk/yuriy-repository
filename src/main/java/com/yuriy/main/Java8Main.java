package com.yuriy.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Sets;

/**
 * Created by yyakymchuk on 2/14/2017.
 */
public class Java8Main {

	private static final String FILE_PATH = "C:\\Temp\\Sample.txt";

	private static Predicate<Integer> BIGGER_THAN_THREE = Predicates.and(Predicates.notNull(), integer -> integer > 3);
	private static Predicate<String> LENGTH_BIGGER_THAN_THREE = Predicates.compose(integer -> integer > 3, s -> s.length());

	public static void main(String[] args) {
		wordOccurrences();
	}

	private static void wordOccurrences() {
		try {
			final long startTime = System.currentTimeMillis();
			final BufferedReader bufferedReader  = Files.newBufferedReader(Paths.get(FILE_PATH));
			final String fileContent = bufferedReader.lines().collect(Collectors.joining(" ")).replace(".", "");
			System.out.println(fileContent);
			final long wordCount = Stream.of(fileContent)
				.map(s -> s.split(" "))
				.flatMap(Arrays::stream)
				.count();
			System.out.println("\nWords count: " + wordCount);
			final Map<String, Long> wordOccurance = Stream.of(fileContent)
				.map(s -> s.split(" "))
				.flatMap(Arrays::stream)
				.collect(Collectors.groupingBy(s -> s, Collectors.counting()));
			wordOccurance.entrySet()
				.stream()
				.sorted(Map.Entry.<String, Long>comparingByValue().reversed())
				.filter(stringLongEntry -> stringLongEntry.getValue() > 1)
				.forEach(System.out::println);
			System.out.println("\nTime execution: " + (System.currentTimeMillis() - startTime));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void streams() {
		Set<String> setMonths = Sets.newHashSet("January", "Fabr", "March");
		System.out.println(setMonths);
		List<Integer> ids = setMonths.stream().filter(s -> s.length() > 3).sorted((o1, o2) -> o2.compareToIgnoreCase(o1)).map(s -> s.length()).collect(Collectors.toList());
		System.out.println(ids);
		setMonths.stream().map(String::length).collect(Collectors.toSet()).forEach(System.out::println);
		System.out.println("Max: " + setMonths.stream().map(String::length).reduce(1, Integer::max));
		System.out.println("Sum: " + setMonths.stream().mapToInt(String::length).sum());
	}

	private static void filterCollection() {
		Set<String> setMonths = Sets.newHashSet("Jan", "Fab", "March");
		Multimap<Integer, String> months = Multimaps.index(setMonths, s -> s != null ? s.length() : null);
		System.out.println(months);
		System.out.println(Multimaps.filterKeys(months, BIGGER_THAN_THREE));
		System.out.println(Multimaps.filterValues(months, LENGTH_BIGGER_THAN_THREE));
	}

	private static void compareStrings(String s1, String s2) {
		Comparator<String> comparator = (string1, string2) -> {
			if(StringUtils.isNotEmpty(string1)) {
				return string1.compareTo(string2);
			} else {
				System.out.println(String.format("S1 is null"));
				return 2;
			}
		};
		switch (comparator.compare(s1,s2)) {
			case 1:
				System.out.println("S1 is bigger");
				break;
			case 0:
				System.out.println("They are equal");
				break;
			case -1:
				System.out.println("S1 is less");
				break;
			default:
				System.out.println("Something went wrong");
		}
	}
}
