package ru.stqa.pft.sandbox;

public class PointExec {
  public static void main(String[] args) {

    //Результат вычисления расстояния с помощью функции
    Point pointA = new Point(5, 6);
    Point pointB = new Point(10, 11);
    System.out.println("Расстояние между точками A" + strPoint(pointA) + " и B" + strPoint(pointB) + " = " + distance(pointA, pointB));

    //Результат вычисления расстояния с помощью метода класса Point
    //Пример 1
    pointA = new Point(1, 2);
    pointB = new Point(7, 8);
    System.out.println("Расстояние между точками A" + strPoint(pointA) + " и B" + strPoint(pointB) + " = " + pointA.distance(pointB));

    //Пример 2
    pointA = new Point(10, 1);
    pointB = new Point(4, 5);
    System.out.println("Расстояние между точками A" + strPoint(pointA) + " и B" + strPoint(pointB) + " = " + pointB.distance(pointA));

    //Пример 3
    pointA = new Point(10, 1);
    System.out.println("Расстояние между точками A" + strPoint(pointA) + " и B" + strPoint(pointA) + " = " + pointA.distance(pointA));
  }

  //функция, которая вычисляет расстояние между точками
  public static double distance(Point pA, Point pB) {
    return Math.sqrt(Math.pow((pB.x - pA.x), 2) + Math.pow((pB.y - pA.y), 2));
  }

  //вспомогательная функция для "красивого" вывода координат точки
  public static String strPoint(Point p) {
    return "(" + p.x + ";" + p.y + ")";
  }

}
