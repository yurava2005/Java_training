package ua.stqa.addressbook;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by юля on 28.05.2016.
 */
public class SquareTests {

  @Test
  public void testArea() {
    Square s = new Square(5);
    Assert.assertEquals(s.area(), 25.0);
  }

  @Test
  public void testDistance1() {
    Point p1 = new Point(3,5);
    Point p2 = new Point(3,5);
    Assert.assertEquals(p1.distance(p2), 0.0);
  }

  @Test
  public void testDistance2() {
    Point p1 = new Point(2,2);
    Point p2 = new Point(-2,-2);
    Assert.assertTrue(Math.abs(p1.distance(p2) - 5.657) < 0.001 );
  }

  @Test
  public void testDistance3() {
    Point p1 = new Point(0,0);
    Point p2 = new Point(0,0);
    Assert.assertEquals(p1.distance(p2), 0.0);
  }

  @Test
  public void testDistance4() {
    Point p1 = new Point(0,0);
    Point p2 = new Point(0,2);
    Assert.assertEquals(p1.distance(p2), 2.0);
  }

  @Test
  public void testDistance5() {
    Point p1 = new Point(0,0);
    Point p2 = new Point(-3,0);
    Assert.assertEquals(p1.distance(p2), 3.0);
  }
}

