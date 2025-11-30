package techmock;

public class App {
    public static void main(String[] args) {
        Calculator.Calculator2 c1 = new Calculator.Calculator2("MyCalc", 0.0); // правильно вызываем конструктор с параметрами
        double r = c1.add(3, 3);                       // r = 6.0
        System.out.println(c1.getLastResult());        // вывод 6.0

        Calculator.Calculator2 c2 = new Calculator.Calculator2();            // вызывает пустой конструктор -> Default
    }
}

