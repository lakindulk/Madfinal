package com.example.loulydatingapp;

public class Packages {

    private double packageMonth = 2000.00;
    private double total;

    public double getPackgeTotal(int packID){

        switch(packID){

            case 1:
                 total = packageMonth ;
                 break;
            case 2:
                 total = (packageMonth - (packageMonth * 25.00 / 100.00)) * 6.00;
                 break;
            case 3:
                 total = (packageMonth - (packageMonth * 40.00 / 100.00)) * 12.00;
                 break;
        }
        return total;
    }

}
