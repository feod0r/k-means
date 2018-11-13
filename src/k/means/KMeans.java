/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k.means;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author feodor
 */
public class KMeans {
    
    private static int n = 2; // количество центроидов
    
    private static ArrayList<Point> field = new ArrayList<Point>();
    private static ArrayList<Point> mass = new ArrayList<Point>();
    
    private static void example(){
        field.add(new Point(1,3));
        field.add(new Point(3,3));
        field.add(new Point(4,3));
        field.add(new Point(5,3));
        field.add(new Point(1,2));
        field.add(new Point(4,2));
        field.add(new Point(1,1));
        field.add(new Point(2,1));
    }
    private static void run(){
        dots();
    }
    private static void dots(){ //выбираем случайно центроиды
        Random random = new Random();
        int done = 0;
        if (n>=field.size()) {
            System.out.println("Неверный размер");
        }
        while (done<n) {     
            int pos = random.nextInt(field.size());
            Point work = field.get(pos);
            if (!work.mass){
                done+=1;
                field.get(random.nextInt(field.size())).mass = true;
                mass.add(field.get(pos));
            }
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        example();
        run();
        for (Point i:field) {
            System.out.println(i);
        }
        System.out.println("центроиды");
        for (Point i:mass) {
            System.out.println(i);
        }
    }
    
}
