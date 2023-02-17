package HomeWork.HomeWork2;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;
import java.util.Random;
import java.util.Arrays;


public class Task2 {
    
    private static Random random = new Random();
    private static Scanner input = new Scanner(System.in);
    private static Logger logger = Logger.getLogger(Task2.class.getName());


    public static void main(String[] args) throws IOException{

        logger.setLevel(Level.INFO);
        FileHandler fh = new FileHandler(".\\Task2.xml");
        logger.addHandler(fh);
        XMLFormatter xml = new XMLFormatter();
        fh.setFormatter(xml);



           
        int n = inputNumber("\nВведите количество элементов в массиве: ");
        //logger.info("Введено количество элементов в массиве: " + n);
        int[] arr = createArray(n); // пустой массив заданного размера

        int minValue = 0;  // минимальное значение элемента
        int maxValue = 10; // максимальное значение элемента

        fillArray(arr, minValue, maxValue);
        //printArrayInt("Исходный массив %d\n\n", arr);


        bubbleSort(arr);
        //printArrayInt("Отсортированный массив %d\n\n", arr);
   }




    // Ввод числа int
    public static int inputNumber(String text) {
        System.out.print(text);
        return input.nextInt();
    }

    // Пустой массив заданного размера
    public static int[] createArray(int size) {
        return new int[size];
    }

    // Рандомное заполнение массива элементами от min до max
    public static void fillArray(int[] arr, int min, int max) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(min, max);
        }
        logger.info("Исходный массив " + Arrays.toString(arr));
    }

    // Печать массива 
    public static void printArrayInt(String note, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    // Метод сортировки пузырьком
    public static int[] bubbleSort(int[] sortArr) {

        // внешний цикл for, который перебирает каждый элемент массива
        for (int i = 0; i < sortArr.length - 1; i++) {
            // внутренний цикл for начинается с первого элемента массива до предпоследнего индекса
            for(int j = 0; j < sortArr.length - i - 1; j++) {
                // проверяем, больше ли элемент слева элемента справа или нет
                if(sortArr[j + 1] < sortArr[j]) {
                    // Если элемент слева больше, он меняется местами с правым элементом, через переменную temp
                    int temp = sortArr[j];
                    sortArr[j] = sortArr[j + 1];
                    sortArr[j + 1] = temp;
                    logger.info("Итерация " + i + "/" + j + "  " + Arrays.toString(sortArr));                   
                }
            }
        }

        logger.info("Отсортированный массив " + Arrays.toString(sortArr));
        return sortArr;
    }

}
    

