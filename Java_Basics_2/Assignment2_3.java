package Java_Basics_2;

interface Shape {
    public void calculateArea();

    public void display();
}

class Rectangle implements Shape {
    // top left point and bottom right point are all we need
    private int x1;
    private int y1;
    private int x4;
    private int y4;
    private double area;

    // Constructor
    public Rectangle(int x1, int y1, int x4, int y4) {
        this.x1 = x1;
        this.y1 = y1;
        this.x4 = x4;
        this.y4 = y4;
    }

    public void calculateArea() {
        this.area = Math.abs((x4 - x1) * (y4 - y1));
    }

    public void display() {
        System.out.println("Rectangle: ");
        System.out.println("(" + x1 + ", " + y1 + ")");
        System.out.println("(" + x4 + ", " + y1 + ")");
        System.out.println("(" + x1 + ", " + y4 + ")");
        System.out.println("(" + x4 + ", " + y4 + ")");
        System.out.println("Area: " + area);
        System.out.println();
    }
}

class Circle implements Shape {
    private int radius;
    private double area;

    // Constructor
    public Circle(int radius) {
        this.radius = radius;
    }

    public void calculateArea() {
        this.area = Math.PI * radius * radius;
    }

    public void display() {
        System.out.println("Circle: ");
        System.out.println("Radius: " + radius);
        System.out.println("Area: " + area);
        System.out.println();

    }

}

class Triangle implements Shape {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int x3;
    private int y3;
    private double area;

    // Constructor
    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public void calculateArea() {
        // Using Heron's Formula since we don't have the height
        this.area = (0.5) * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
    }

    public void display() {
        System.out.println("Triangle: ");
        System.out.println("(" + x1 + ", " + y1 + ")");
        System.out.println("(" + x2 + ", " + y2 + ")");
        System.out.println("(" + x3 + ", " + y3 + ")");
        System.out.println("Area: " + area);
        System.out.println();

    }
}

public class Assignment2_3 {
    public static void main(String[] args) {
        Rectangle rect = new Rectangle(10, 20, 40, 40);
        rect.calculateArea();
        rect.display();

        Circle circle = new Circle(5);
        circle.calculateArea();
        circle.display();

        Triangle triangle = new Triangle(-2, 3, -3, -1, 3, -2);
        triangle.calculateArea();
        triangle.display();
    }
}
