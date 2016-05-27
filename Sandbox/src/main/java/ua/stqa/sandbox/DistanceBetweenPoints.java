package ua.stqa.sandbox;

/**
 * Created by юля on 26.05.2016.
 */
public class DistanceBetweenPoints {

  public static void main (String[] args){


    Point p1 = new Point(3,3);
    Point p2 = new Point(5,8);

    System.out.println("Результат работы функции:");
    System.out.println("  Расстояние между двумя точками с координатами (" + p1.x + "," + p1.y + ") и (" + p2.x + "," + p2.y + ") равно " + distance(p1, p2));

    System.out.println("");
    System.out.println("Результат работы метода класса:");
    System.out.println("  Расстояние между двумя точками с координатами (" + p1.x + "," + p1.y + ") и (" + p2.x + "," + p2.y + ") равно " + p1.distance( p2));


  }
  public static double distance (Point p1, Point p2){
    return Math.sqrt((p2.x-p1.x)*(p2.x-p1.x) + (p2.y-p1.y)*(p2.y-p1.y));

  }
}