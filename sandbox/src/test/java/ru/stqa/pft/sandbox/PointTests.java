package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDisctance(){
    Point pointA;
    Point pointB;
    //Тест 1
    pointA = new Point(5, 6);
    pointB = new Point(10, 11);
    //assert pointA.distance(pointB) == 7.0710678118654755;
    Assert.assertEquals((pointA.distance(pointB)),7.0710678118654755);
    //Тест 2
    pointA = new Point(0, 0);
    pointB = new Point(2, 2);
    Assert.assertEquals((pointA.distance(pointB)),2.8284271247461903);

  }
}
