package com.my.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private Point[] points;

    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {

        if(points == null){
            throw new IllegalArgumentException();
        }

        this.points = points;

        Arrays.sort(this.points);

        List<LineSegment> segmentList = new ArrayList<>();

        for(int i = 0; i < points.length-3; ++i){
            for(int j = i+1; j < points.length-2; ++j){
                for(int k = j+1; k < points.length-1; ++k){
                    for(int l = k+1; l < points.length; ++l){

                        Point p1 = points[i];
                        Point p2 = points[j];
                        Point p3 = points[k];
                        Point p4 = points[l];

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
        return this.segments;
    }
}

