package com.androidwatcher.util;

import com.google.common.collect.ImmutableList;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.ArrayList;
import java.util.List;

public class LDAUtil {

    public static void main(String[] args) {
        List<List<Double>> x=new ArrayList<>();
        List<Double> data= ImmutableList.<Double>of(1D,2D);
        x.add(data);
        List<Double> data2= ImmutableList.<Double>of(1D,1D);
        x.add(data2);
        List<Double> data3= ImmutableList.<Double>of(2D,2D);
        x.add(data3);
        List<Integer> y=ImmutableList.<Integer>of(1,2,2);
        process(x,y);
    }


    public static void process(List<List<Double>> x,List<Integer> y){
        RealMatrix matrix = new Array2DRowRealMatrix(new double[x.size()][x.get(0).size()]);
        for(int i=0;i<x.size();i++){
            for(int j=0;j<x.get(i).size();j++){
                matrix.setEntry(i,j,x.get(i).get(j));
            }
        }

    }

}
