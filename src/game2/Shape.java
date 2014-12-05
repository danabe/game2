/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2;

/**
 *
 * @author aberg2
 */
public class Shape {
    public double area ()
    {
        return 0;   // Since this is just a generic "Shape" we will assume the area as zero.
                    // The actual area of a shape must be overridden by a subclass, as we see below.
                    // You will learn later that there is a way to force a subclass to override a method,
                    // but for this simple example we will ignore that.
    }
 
    

}


class myRectangle extends Shape {
    myRectangle (double x0, double y0, double width, double height) {
        this.x0 = x0;
        this.y0 = y0; 
        this.width =  width;
        this.height = height;
        
    }
    private double width, height, x0, y0; 
    
    public double area () {                     // dynamic method
 
           return width*height;
    }
    
    public double getX0() {
        return x0;
    }
    public double getX1() {
        return x0+width;
    }
    public double getY0() {
        return y0;
    }
    public double getY1() {
        return y0+height;
    }
    
    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    
}
