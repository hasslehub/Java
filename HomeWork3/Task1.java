package HomeWork.HomeWork3;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Task1 {

    /* +Реализовать алгоритм сортировки слиянием */

    private static Logger logger = Logger.getLogger(Task1.class.getName());
    

    public static void main(String[] args) throws IOException{

        logger.setUseParentHandlers(false); // logger без консоли
        logger.setLevel(Level.INFO);
        FileHandler fhLogger = new FileHandler(".\\Mergelog.txt");
        logger.addHandler(fhLogger);
        SimpleFormatter txt = new SimpleFormatter();
        fhLogger.setFormatter(txt);


        int n = inputNumber("\nВведите количество элементов в массиве: ");
        int[] array = createArray(n);       // пустой массив заданного размера
        fillArrayRand(array);                   // рандомно заполняем массив
        printArrayInt("Исходный массив", array);
        printArrayInt("Отсортированный массив", sortArray(array));
        System.out.println();
    }


    public static int[] sortArray(int[] array) {
        if (array == null) {  //  выход из рекурсии проверяем на пустой массив
            logger.info("Массив закончился");
            return null;
        }

        if (array.length < 2) {  // проверка если в массиве 1 элемент
            logger.info("В массиве остался 1 элемент");
            return array;
        }

        int length_arr = array.length / 2; // делим длину исходного массива
        logger.info("Разбиваем " + Arrays.toString(array) +" на два массива");
        
        if (array.length % 2 != 0){  // если длина массива не четная
            length_arr += 1;
        }

        int[] arrayR = createArray(length_arr);       // новый массив размером правой части от исходного
        int[] arrayL = createArray(array.length / 2); // новый массив размером левой части от исходного
    
        fillArray(arrayL, array, 0, count++, " шаг. Массив с левой частью");            // заполняем массив с левой частью от исходного
        //printArrayInt("Левая часть массива", arrayL);  
        fillArray(arrayR, array, array.length / 2, count++, " шаг. Массив с правой частью");   // заполняем массив с правой частью от исходного
        //printArrayInt("Правая часть массива", arrayR);

        arrayL = sortArray(arrayL); // рекурсия деления массива
        arrayR = sortArray(arrayR); // рекурсия деления массива

        return mergeArray(arrayL, arrayR);  // метод возврата слияния двух отсортированных массивов
    }
    

    public static int[] mergeArray(int[] arrayL, int[] arrayR) {

        int[] arrayC = new int[arrayL.length + arrayR.length]; //создаем массив слияния двух отсортированных массивов
        int indexL = 0; 
        int indexR = 0;

        for (int i = 0; i < arrayC.length; i++) {
            if (indexL < arrayL.length && indexR < arrayR.length) {
                if (arrayL[indexL] < arrayR[indexR]) { // сравниваем значения элементов двух массивов
                    arrayC[i] = arrayL[indexL];      // записываем значения наименьшего элемента из двух массивов
                    logger.info("Сравниваем элемнты из 2-х массивов " + arrayL[indexL] + " " + arrayR[indexR]);
                    indexL++;
                } else {
                    arrayC[i] = arrayR[indexR];     // записываем значения наименьшего элемента из двух массивов
                    indexR++;
                }
                logger.info("Сохраняем " + arrayC[i] + " в " + Arrays.toString(arrayC));
            } 
            
            else if (indexL == arrayL.length && indexR < arrayR.length) {   // если левый массив закончился, а правый нет -> добавляем оставшийся элемент в массив
                arrayC[i] = arrayR[indexR];
                logger.info("Добавляем элемент из правого массива " + arrayR[indexR] + " в " + Arrays.toString(arrayC));
                indexR++;
            }

            else if (indexL < arrayL.length && indexR == arrayR.length) {   // если правый массив закончился, а левый нет -> добавляем оставшийся элемент в массив
                arrayC[i] = arrayL[indexL];
                logger.info("Добавляем элемент из левого массива " + arrayL[indexL] + " в " + Arrays.toString(arrayC));
                indexL++;
            }
        }
        return arrayC;
    }


   /* Вспомогательные методы */

    private static Random random = new Random();
    private static Scanner input = new Scanner(System.in);
    private static int count = 1;

    // Ввод числа int
    public static int inputNumber(String text) {
        System.out.print(text);
        return input.nextInt();        
    }

    // Пустой массив заданного размера
    public static int[] createArray(int size) {
        return new int[size];
    }


    // Рандомное заполнение массива элементами
    public static void fillArrayRand(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
    }

    // Заполнение массива элементами
    public static void fillArray(int[] arrOut,int[] arrIn, int index, int step, String txt) {
        for (int i = 0; i < arrOut.length; i++) {
            arrOut[i] = arrIn[index];
            index++;
        }
        logger.info(step + txt + Arrays.toString(arrOut));
    }
    
    // Печать массива 
    public static void printArrayInt(String note, int[] arr) {
        System.out.println();
        System.out.println(note);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");          
        }
        System.out.println();
        logger.info(Arrays.toString(arr));
    }
    
}
