package com.sfmd.algorithm.leetCode.find;


public class Square {

    public double square(double num) {
        if (num < 0) {
            return num;
        }
        double bigger = Math.max(1, num);
        double litter = Math.min(1, num);
        while (true) {
            if (Math.abs(litter - bigger) < 0.00000001) {
                return bigger;
            }
            double tmp = (bigger + litter) / 2;
            if (tmp * tmp < num) {
                litter = tmp;
            } else {
                bigger = tmp;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Square().square(0.0000000000000000000001));

        System.out.println(new Square().square(0.25));
        System.out.println(new Square().square(0.5));
        System.out.println(new Square().square(1));
        System.out.println(new Square().square(1.01));
        System.out.println(new Square().square(2));
        System.out.println(new Square().square(4));
    }

}
