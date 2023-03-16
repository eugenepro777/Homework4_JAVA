package Homework4;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Task2 {
    /* Реализуйте очередь с помощью LinkedList со следующими методами:
- enqueue() — помещает элемент в конец очереди,
- dequeue() — возвращает первый элемент из очереди и удаляет его,
- first() — возвращает первый элемент из очереди, не удаляя. */
    public static void main(String[] args) {

        Deque<Integer> sourceQueue = fillQueueRandom();
        System.out.println("Сформирована очередь:");
        printQueue(sourceQueue);
        System.out.print("Чтобы добавить элемент ");
        System.out.println("Очередь с добавленным элементом: ");
        printQueue(enque(sourceQueue));
        System.out.printf("Удаляем первый элемент: %s\n", dequeue(sourceQueue));
        System.out.println("Новая очередь:");
        printQueue(sourceQueue);
        System.out.printf("Первый элемент в нашей очереди: %s", first(sourceQueue));


//        reverseList(sourceArray);
        //System.out.println("Перевёрнутый список:");
        //printList(sourceQue);

    }

    public static Deque<Integer> fillQueueRandom() {
        System.out.print("Задайте размер очереди: ");
        Scanner scanner = new Scanner(System.in);
        Integer size = scanner.nextInt();
        Deque<Integer> list = new LinkedList<Integer>(Collections.singleton(size));
        for (int i = 1; i < size; i++) {
            Integer randNumber = (int) (Math.random() * 30);
            list.add(randNumber);
        }
        return list;
    }

    public static void printQueue(Deque<Integer> list) {
        for (Integer item :
                list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static Deque<Integer> enque(Deque<Integer> list) {
        System.out.print("введите число для добавления в очередь: ");
        Scanner scan = new Scanner(System.in);
        Integer item = scan.nextInt();
        list.addLast(item);
        return list;
    }

    public static Integer dequeue(Deque<Integer> list) {
        Integer item = list.pollFirst();
        return item;
    }

    public static Integer first(Deque<Integer> list) {
        return list.peekFirst();
    }


}
