package com.my.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private Point[] points;

    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points)   {

        if(points == null){
            throw new IllegalArgumentException();
        }

        this.points = points;

        Arrays.sort(this.points);

        Point p = points[0];

        Arrays.sort(this.points,p.slopeOrder());

        int count = 0;
        int start = -1;

        List<LineSegment> segmentList = new ArrayList<>();

        for(int i = 1; i < points.length-1; ++i){
            double presentSlope = p.slopeTo(points[i]);
            double nextSlope = p.slopeTo(points[i+1]);
            if(presentSlope == nextSlope){
                if(start == -1){
                    start = i;
                }
                count++;
                if(i == points.length-2){
                    if(count > 3){
                        LineSegment lineSegment = new LineSegment(points[start],points[i]);
                        segmentList.add(lineSegment);
                    }
                }
                continue;
            }else{
                if(count > 3){
                    LineSegment lineSegment = new LineSegment(points[start],points[i]);
                    segmentList.add(lineSegment);
                }
                start = -1;
                count = 0;
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
