package ua.stqa.addressbook;

public class MyFirstProgram {
  public static void main(String[] args) {
    System.out.println("Hello, world!");
    double x1 = 3;
    double x2 = 5;
    double y1 = 3;
    double y2 = 8;



    System.out.println("Расстояние между двумя точками с координатами (" + x1 + "," + y1 + ") и (" + x2 + "," + y2 + ") равно " + distance(x1,y1,x2,y2));
    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(5,7);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

  }
  public static double distance (double x1, double y1, double x2, double y2){
    return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
  }
}