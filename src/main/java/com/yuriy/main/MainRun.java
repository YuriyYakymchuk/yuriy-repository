package com.yuriy.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Created by yyakymchuk on 1/10/2017.
 */
public class MainRun {

    private final static String FILE_PATH = "D:\\Trane(Emerald)\\Logs\\ProcessedLogs\\";
    private final static String FILE_NAME = "13-14-output-50.json";

    public static void main(String[] args) throws IOException {
		listsIntersaction();
    }

	private static void listsIntersaction() {
		List<String> list_one = Lists.newArrayList("One", "Two", "Three");
		List<String> list_two = Lists.newArrayList("Two");
		List<String> intersected = list_one.stream().filter(s -> list_two.contains(s)).collect(Collectors.toList());
		List<String> notPresent = list_one.stream().filter(s -> !list_two.contains(s)).collect(Collectors.toList());
		System.out.println(intersected);
		System.out.println(notPresent);
	}

	private static void printResults() throws IOException{
		final BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(FILE_PATH + FILE_NAME));
		final BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(FILE_PATH + "formatted_output_13-14.json"));
		final Map<String, List<String>> objectTypeProperties = Maps.newTreeMap();
		final List<String> objectTypes = Lists.newArrayList();
		Stream<String> lines = bufferedReader.lines();
		lines.forEach(s1 -> {

			if (!Pattern.matches("\\s*@\\S+\\s\\d+", s1)) {
				if (!Objects.equal("Groups", s1) && !s1.contains("timestamp")) {
					objectTypeProperties.put(s1, Lists.newArrayList());
					objectTypes.add(s1);
				}
			} else {
				s1 = s1.substring(0, s1.lastIndexOf(":"));
				objectTypeProperties.get(objectTypes.get(objectTypes.size() - 1)).add(s1);
			}
		});

		objectTypeProperties.forEach((s, strings) -> {
			try {
				bufferedWriter.write(s + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			Collections.sort(strings);
			strings.forEach(s1 -> {
				try {
					bufferedWriter.write(" "+s1+"\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		});

		bufferedReader.close();
		bufferedWriter.close();
	}
}
