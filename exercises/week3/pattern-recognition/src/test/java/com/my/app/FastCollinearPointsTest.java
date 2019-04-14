package com.my.app;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FastCollinearPointsTest {

    private static final String TEST_INPUT_FILE_PATH = "/collinear/input50.txt";

    @Test
    public void testInputFile() throws Exception{
        Path inputFilePath = Paths.get(this.getClass().getResource(TEST_INPUT_FILE_PATH).toURI());
        int n = Integer.parseInt(Files.lines(inputFilePath).limit(1).map(String::trim).findFirst().get());
        Point[] points = Files.lines(inputFilePath).skip(1).map(line -> {
            String[] args = Arrays
                    .stream(line.trim().split(" "))
                    .map(String::trim).filter(s -> !s.isEmpty())
                    .collect(Collectors.toList()).toArray(new String[2]);
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);
            return new Point(x,y);
        }).collect(Collectors.toList()).toArray(new Point[n]);

        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);

        LineSegment[] segments = fastCollinearPoints.segments();

        for(int i = 0; i < segments.length; ++i){
            System.out.println(segments[i]);
        }
    }

}
