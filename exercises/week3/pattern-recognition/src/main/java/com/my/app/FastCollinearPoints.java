package com.my.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FastCollinearPoints {

    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] points)   {

        if(points == null){
            throw new IllegalArgumentException();
        }

        for(int i = 0; i < points.length; ++i){
            if(points[i] == null){
                throw new IllegalArgumentException();
            }
        }

        Point[] pointsCopy = Arrays.copyOf(points,points.length);

        Arrays.sort(pointsCopy);

        for(int i = 0; i < pointsCopy.length-1; ++i){
            Point p1 = pointsCopy[i];
            Point p2 = pointsCopy[i+1];

            if(p1.equals(p2)){
                throw new IllegalArgumentException();
            }
        }

        List<LineSegment> segmentList = getSegmentList(pointsCopy);

        this.segments = segmentList.toArray(new LineSegment[segmentList.size()]);
    }

    private List<LineSegment> getSegmentList(Point[] points){

        List<LineSegment> segmentList = new ArrayList<>();

        Map<Double,Set<Integer>> map = new HashMap<>();

        for(int i = 0; i < points.length-2; ++i){

            Point p = points[i];
            List<Integer> collinearPointIndex = new ArrayList<>();
            List<Integer> tempPointIndex = new ArrayList<>();
            Arrays.sort(points,p.slopeOrder());

            for(int j = i+1, count = 2; j < points.length-1; ++j){

                if(tempPointIndex.isEmpty()){
                    tempPointIndex.add(j);
                }

                double presentSlope = p.slopeTo(points[j]);
                double nextSlope = p.slopeTo(points[j+1]);

                if(presentSlope == nextSlope){
                    tempPointIndex.add(j+1);
                    count++;
                }

                if(presentSlope != nextSlope || j == points.length-2){
                    if(count > 3){
                        boolean alreadyProcessed = (map.containsKey(Double.valueOf(presentSlope)) && map.get(presentSlope).contains(i));
                        if(!alreadyProcessed){
                            int lastIndex = tempPointIndex.get(tempPointIndex.size()-1);
                            LineSegment lineSegment = new LineSegment(p,points[lastIndex]);
                            segmentList.add(lineSegment);
                            Set<Integer> valueSet = map.containsKey(Double.valueOf(presentSlope)) ? map.get(presentSlope) : new HashSet();
                            valueSet.addAll(tempPointIndex);
                            valueSet.add(i);
                            map.put(Double.valueOf(presentSlope),valueSet);
                            collinearPointIndex.addAll(tempPointIndex);
                        }
                    }
                    tempPointIndex.clear();
                    count = 2;
                }
            }
        }

        return segmentList;
    }

    public int numberOfSegments() {
        return this.segments.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(this.segments,this.segments.length);
    }
}
