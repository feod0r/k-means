/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k.means;

/**
 *
 * @author feodor
 */
public class Point {
    double x;
    double y;
    
    int cluster;
    
    boolean mass;
    
    double mrange;
    
    Point(){
        x = 0;
        y = 0;   
        cluster = 0;
        mrange = 0;
    }
    
    Point(double inx, double iny){
        x = inx;
        y = iny;
        cluster = 0;
        mrange = 0;
    }

    Point(Point get) {
        x = get.x;
        y = get.y;
        
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public double range(Point inp){
        double lenx = inp.x - x;
        double leny = inp.y - y;
        return(Math.sqrt( Math.pow(lenx, 2) + Math.pow(leny, 2) ));
    }
    @Override
    public String toString(){
        if (mass){
            return(String.format("%10.4f %10.4f", x, y));
        } else {
            return(String.format("%10.4f %10.4f %3d  range: %4.2f", x, y, cluster, mrange));
        }
    }
}
