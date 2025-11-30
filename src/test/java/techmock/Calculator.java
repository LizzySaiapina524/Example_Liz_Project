package techmock;

import java.util.Scanner; // класс для чтения ввода с клавиатуры

public class Calculator {
    public static void main(String[] args) {        //точка входа программы.
        Scanner s = new Scanner(System.in);         // создаём объект для чтения с консоли

        System.out.print("a: ");
        double a = s.nextDouble();                  // читаем число, оператор и второе число.

        System.out.print("op (+ - * /): ");
        char op = s.next().charAt(0);

        System.out.print("b: ");
        double b = s.nextDouble();                 // читаем число, оператор и второе число.

        double r;
        switch (op) {                              //выбираем операцию и вычисляем результат; проверяем деление на ноль.
            case '+': r = a + b; break;
            case '-': r = a - b; break;
            case '*': r = a * b; break;
            case '/': r = (b == 0) ? Double.NaN : a / b; break;
            default: System.out.println("Unknown operator"); s.close(); return;
        }

        System.out.println("= " + r);               //печатаем результат
        s.close();                                  // закрываем сканер
    }

    public static class Calculator2 {
        private double lastResult;         // поле для хранения последнего результата
        private String name;               // поле с именем калькулятора

        // Конструктор: инициализирует name и lastResult
        public Calculator2(String name, double initialValue) {
            this.name = name;             // this.name — поле, name — параметр
            this.lastResult = initialValue;
        }

        // Ещё один (пустой) конструктор по умолчанию
        public Calculator2() {
            this("Default", 0.0);         // вызывает другой конструктор
        }

        public double add(double a, double b) {
            lastResult = a + b;
            return lastResult;
        }

        public double getLastResult() {
            return lastResult;
        }
    }

}

