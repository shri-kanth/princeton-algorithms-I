package com.my.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        this.segments = getSegments(pointsCopy);
    }

    private LineSegment[] getSegments(Point[] inputPoints){

        List<LineSegment> segmentList = new ArrayList<>();
        List<Point> endPointList = new ArrayList<>();
        List<Double> slopeList = new ArrayList<>();

        for(int i = 0; i < inputPoints.length-2; ++i){

            Point p = inputPoints[i];
            Point[] points = Arrays.copyOf(inputPoints,inputPoints.length);
            Arrays.sort(points,i+1,points.length,p.slopeOrder());

            for(int j = i+1, count = 2; j < points.length-1; ++j){

                double presentSlope = p.slopeTo(points[j]);
                double nextSlope = p.slopeTo(points[j+1]);

                if(presentSlope == nextSlope){
                    count++;
                }

                if(presentSlope != nextSlope || j == points.length-2){
                    if(count > 3){
                        Point endPoint = (presentSlope == nextSlope) ? points[j+1] : points[j];
                        boolean alreadyProcessed = false;
                        for(int k = 0; k < endPointList.size(); ++k){
                            if(endPoint.equals(endPointList.get(k))){
                                double slope = slopeList.get(k);
                                if(slope == presentSlope){
                                    alreadyProcessed = true;
                                    break;
                                }
                            }
                        }
                        if(!alreadyProcessed){
                            LineSegment lineSegment = new LineSegment(p,endPoint);
                            segmentList.add(lineSegment);
                            endPointList.add(endPoint);
                            slopeList.add(presentSlope);
                        }
                    }
                    count = 2;
                }
            }
        }

        return segmentList.toArray(new LineSegment[segmentList.size()]);
    }

    public int numberOfSegments() {
        return this.segments.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(this.segments,this.segments.length);
    }
}
