package Homework4;

import Homework2.Task4;

import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Task3 {

    /*
     *  Калькулятор c логированием.
     *       В калькулятор добавьте возможность отменить последнюю операцию.
     * */
    public static void main(String[] args) throws IOException {
        Scanner scanIn = new Scanner(System.in);
        Deque<Double> data_list = new LinkedList<>();

        boolean inputFlag = true, activation = true;
        double dataFirst = 0.0;
        String action;
        while (activation) {
            if (inputFlag) {
                dataFirst = inputData(scanIn);
                inputFlag = false;
            }
            action = inputAction(scanIn);
            if (action.equals("esc")) {
                break;
            }
            if (action.equals("undo")) {
                if (!data_list.isEmpty()) {
                    undoAction(data_list);
                    dataFirst = data_list.getLast();
                } else {
                    System.out.println("Результат отсутствует. Попробуйте заново.");
                    dataFirst = inputData(scanIn);
                }
                action = inputAction(scanIn);
            }
            double dataSecond = inputData(scanIn);
            double result = calculus(dataFirst, dataSecond, action);
            dataFirst = result;
            addItem(data_list, result);
            System.out.printf("Результат = %.2f\n", result);

        }
        scanIn.close();

    }

    public static double inputData(Scanner scan) {
        System.out.print("Введите число: ");
        double number = 0.0;
        try {
            number = scan.nextDouble();
        } catch (Exception e) {
            System.out.println("Неверный ввод. Повторите ввод");
            scan.nextLine();
            inputData(scan);
        }
        return number;
    }

    public static String inputAction(Scanner scanner) {
        System.out.print("Задайте действие:\n '+', '-', '*', '**', '/' или 'undo' - для отмены действия, 'esc' - для выхода: ");

        return scanner.next().toLowerCase();
    }

    public static void undoAction(Deque<Double> list) {
        if (!list.isEmpty()) {
            list.removeLast();
            System.out.println("Последняя операция отменена");
            if (list.isEmpty()) {
                list.addLast(0.0);
            }
            System.out.println("Вычисляем результат " + list.getLast());
        }
    }

    public static void addItem(Deque<Double> list, double item) {
        list.addLast(item);
    }

    public static double pow(double value, double pow) {
        if (pow == 1) {
            return value;
        } else {
            return value * pow(value, pow - 1);
        }
    }

    public static double calculus(double number1, double number2, String action) throws IOException{
        Logger logger = Logger.getLogger(Task4.class.getName());
        FileHandler fh = new FileHandler("calculate_log.txt", true);
        SimpleFormatter sFormat = new SimpleFormatter();
        fh.setFormatter(sFormat);
        logger.addHandler(fh);
        logger.setUseParentHandlers(false);
        double result = 0.0;
        switch (action) {
            case "+" -> {
                logger.info("addition".formatted(action));
                result = number1 + number2;
                logger.info("first number: %s".formatted(String.valueOf(number1)));
                logger.info("second number: %s".formatted(String.valueOf(number2)));
                logger.info("the_result_of_addition = %s".formatted(String.valueOf(result)));
                break;
            }
            case "-" -> {
                logger.info("subtraction".formatted(action));
                result = number1 - number2;
                logger.info("first number: %s".formatted(String.valueOf(number1)));
                logger.info("second number: %s".formatted(String.valueOf(number2)));
                logger.info("the_result_of_subtraction = %s".formatted(String.valueOf(result)));
                break;
            }
            case "*" -> {
                logger.info("multiplication".formatted(action));
                result = number1 * number2;
                logger.info("first number: %s".formatted(String.valueOf(number1)));
                logger.info("second number: %s".formatted(String.valueOf(number2)));
                logger.info("the_result_of_multiplication = %s".formatted(String.valueOf(result)));
                break;
            }
            case "**" -> {
                logger.info("exponentiation".formatted(action));
                result = pow(number1, number2);
                logger.info("first number: %s".formatted(String.valueOf(number1)));
                logger.info("second number: %s".formatted(String.valueOf(number2)));
                logger.info("the_result_of_exponentiation = %s".formatted(String.valueOf(result)));
                break;
            }
            case "/" -> {
                if (number2 == 0) {
                    System.out.println("Деление на ноль!");
                    logger.warning("division by zero");
                }
                else {
                    logger.info("division".formatted(action));
                    result = number1 / number2;
                    logger.info("first number: %s".formatted(String.valueOf(number1)));
                    logger.info("second number: %s".formatted(String.valueOf(number2)));
                    logger.info("the_result_of_division = %s".formatted(String.valueOf(result)));
                }
                break;
            }
            default -> {
                System.out.println("Ошибка при вводе");
                logger.warning("input error");
            }
        }
        return result;
    }
}
