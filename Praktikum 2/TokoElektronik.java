import java.util.Scanner;
import java.lang.System;

public class TokoElektronik {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        String brand;
        String model;
        int storagecapacity;
        int batterylife;
        String processor;
        int ram;
        boolean istouchscreen;
        int screensize;
        boolean hascellular;
        int type;
        Smartphone sp;
        Laptop lp;
        Tablet tb;
        int total=0;


        for(int i=0; i<n ;i++){
            type = input.nextInt();
            switch(type) {
            case 1:
                brand = input.next();
                model = input.next();
                storagecapacity = input.nextInt();
                batterylife = input.nextInt();
                sp = new Smartphone(brand, model, storagecapacity, batterylife);
                sp.displayDetails();
                total += sp.calculatePrice();
                System.out.printf("Price: %d\n", sp.calculatePrice());
                break;
            case 2:
                brand = input.next();
                model = input.next();
                processor = input.next();
                ram = input.nextInt();
                istouchscreen = (input.nextInt() != 0);
                lp = new Laptop(brand, model, processor, ram, istouchscreen);
                lp.displayDetails();    
                total += lp.calculatePrice();
                System.out.printf("Price: %d\n", lp.calculatePrice());
                break;
            case 3:
                brand = input.next();
                model = input.next();
                screensize = input.nextInt();
                hascellular = (input.nextInt() != 0);
                tb = new Tablet(brand, model, screensize, hascellular);
                tb.displayDetails();
                total += tb.calculatePrice();
                System.out.printf("Price: %d\n", tb.calculatePrice());
                break;
            default:
                break;
            }
        }
        System.out.printf("Total price: %d\n", total);
        input.close();
    }
}