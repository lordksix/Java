package com.pasquel.lqexercises;


public class ExercisesShapes {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[4];

        shapes[0] = new Cube(5.0);
        shapes[1] = new Cube(12.0);

        shapes[2] = new Box(12.0);
        shapes[3] = new Box(3.0);

        for(Shape shape:shapes){shape.setColor("silver");}
        for(Shape shape:shapes){
            ThreeDimensional temp = (ThreeDimensional)shape;
            System.out.println(temp.getVolume());
        }
        TwoDimensional[] TwoD = new TwoDimensional[7];
        
        TwoD[0] = new Circle(7.0);
        TwoD[1] = new Rectangle(5.0, 4.0);
        TwoD[2] = new Square(5.67);

        TwoD[3] = new Rectangle(5.0, 4.0);
        TwoD[4] = new Rectangle(5.0, 5.0);

        TwoD[5] = new Square(5.0);
        TwoD[6] = new Square(8.0);

        for(TwoDimensional temp: TwoD){
            System.out.println(temp.getArea());
            System.out.println(temp.getPerimeter());
        }
    }
}
