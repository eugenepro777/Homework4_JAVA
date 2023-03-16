package Homework4;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Task1 {

    /*  Пусть дан LinkedList с несколькими элементами.
    Реализуйте метод, который вернёт «перевёрнутый» список.*/
    public static void main(String[] args) {
        LinkedList<Integer> sourceList = fillListRandom();
        System.out.println("Исходный список:");
        printList(sourceList);
        reverseList(sourceList);
        System.out.println("Перевёрнутый список:");
        printList(sourceList);

    }

    public static LinkedList<Integer> fillListRandom() {
        System.out.print("Задайте размер списка: ");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        LinkedList<Integer> list = new LinkedList<Integer>(Collections.singleton(size));
        for (int i = 1; i < size; i++) {
            Integer randNumber = (int) (Math.random() * 30);
            list.add(0, randNumber);
        }
        return list;
    }

    public static void printList(LinkedList<Integer> list) {
        for (Integer item :
                list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static void reverseList(LinkedList<Integer> list) {
        int fullSize = list.size();
        int halfSize = list.size() / 2;
        for (int i = 0; i < halfSize; i++) {
            Integer temp = list.get(i);
            list.set(i, list.get(fullSize - i - 1));
            list.set(fullSize - i - 1, temp);
        }
    }
}
