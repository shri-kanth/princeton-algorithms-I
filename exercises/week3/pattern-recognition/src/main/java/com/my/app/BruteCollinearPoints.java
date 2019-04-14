package com.my.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private final LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {

        if(points == null){
            throw new IllegalArgumentException();
        }

        for(int i = 0; i < points.length; ++i){
            if(points[i] == null){
                throw new IllegalArgumentException();
            }
        }

        Point[] pointArray = Arrays.copyOf(points,points.length);

        Arrays.sort(pointArray);

        for(int i = 0; i < pointArray.length-1; ++i){
            Point p1 = pointArray[i];
            Point p2 = pointArray[i+1];

            if(p1.equals(p2)){
                throw new IllegalArgumentException();
            }
        }

        List<LineSegment> segmentList = new ArrayList<>();

        for(int i = 0; i < pointArray.length-3; ++i){
            for(int j = i+1; j < pointArray.length-2; ++j){
                for(int k = j+1; k < pointArray.length-1; ++k){
                    for(int l = k+1; l < pointArray.length; ++l){

                        Point p1 = pointArray[i];
                        Point p2 = pointArray[j];
                        Point p3 = pointArray[k];
                        Point p4 = pointArray[l];

                        double slope1 = p1.slopeTo(p2);
                        double slope2 = p1.slopeTo(p3);
                        double slope3 = p1.slopeTo(p4);

                        if(slope1 == slope2 && slope2 == slope3){
                            segmentList.add(new LineSegment(p1,p4));
                        }
                    }
                }
            }
        }

        this.segments = segmentList.toArray(new LineSegment[segmentList.size()]);
    }

    public int numberOfSegments() {
        return this.segments.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(this.segments,this.segments.length);
    }
}

