package ru.stqa.pft.sandbox;

public class Point {
  //Класс Point для представления точек на двумерной плоскости.
  //Объекты этого класса содержат два атрибута, которые соответствуют координатам точки на плоскости.
  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  //метод для вычисления расстояния между точками
  public double distance(Point point2) {
    return Math.sqrt((Math.pow((point2.x - this.x), 2)) + (Math.pow((point2.y - this.y), 2)));
  }

}
