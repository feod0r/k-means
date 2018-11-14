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
    
    private static int n = 100; // количество центроидов
    private static int k = 10000; //количество точек
    
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
    private static void random(){
        for (int i = 0; i < k; i++) {
            Random random = new Random();
            field.add(new Point(random.nextDouble()*10,random.nextDouble()*10));
        }
    }
    private static void run(){
        dots();
        double lastIter = 0;
        for (int i = 0; i < 100000; i++) {
            iter();
            recalc();
            System.out.println(String.format("Iter %d %10.3f", i, error() ));
            if(lastIter != error()){
                lastIter = error();
            } else {
                break;
            }
        }
        
    }
    private static void dots(){ //выбираем случайно центроиды
        Random random = new Random();
        int done = 0;
        if (n>=field.size()) {
            System.out.println("Неверный размер");
        }
//        mass.add(field.get(field.size()-1));
//        mass.add(field.get(field.size()-2));
        
        while (done<n) {     
            int pos = random.nextInt(field.size());
            Point work = field.get(pos);
            if (!work.mass){
                done+=1;
                field.get(random.nextInt(field.size())).mass = true;
                mass.add(new Point(field.get(pos)));
                mass.get(mass.size()-1).mass = true;
            }
        }
    }
    private static void iter(){
        for (Point i:field) {
            int ind = 0;
            double[] ranges = new double[mass.size()];
            for (Point j:mass ) {
                ranges[ind] = i.range(j);
                ind++;
            }
            i.mrange = ranges[min(ranges)];
            i.cluster = min(ranges);
//            for (int j = 0; j < ranges.length; j++) {
//                System.out.print(String.format("%10.4f ", ranges[j]));
//            }
//            System.out.print(String.format("%3d ", min(ranges) + 1));
//            System.out.println();
        }
//        System.out.println();
    }
    private static void recalc(){
        for (int i = 0; i < mass.size(); i++) {
            double x = 0;
            double y = 0;
            int count = 0;
            for(Point j:field){
                if(j.cluster == i){
                    x += j.x;
                    y += j.y;
                    count++;
                }
            }
            mass.get(i).x = x/count;
            mass.get(i).y = y/count;
        }
    }
    
    private static int min(double[] inp){
        int indOfMin = 0;
        for (int i = 0; i < inp.length; i++) {
            if (inp[i]<inp[indOfMin]) {
                indOfMin = i;
            }
        }
        return(indOfMin);
    }
    
    private static double error(){
        double count = 0;
        for (Point i:field) {
            count+=Math.pow(i.mrange,2);
//            System.out.println(count);
        }
        return(count);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        random();
//        example();
        run();
        if(k<500){
            for (Point i:field) {
                System.out.println(i);
            }
        }

        System.out.println("центроиды");
        for (Point i:mass) {
            System.out.println(i);
        }
        
    }
    
}
