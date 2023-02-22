package HomeWork.HomeWork4;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;



public class Task1_1 {

    /* +Реализовать алгоритм пирамидальной сортировки (HeapSort) */

    private static Logger logger = Logger.getLogger(Task1_1.class.getName());

    public static void main(String[] args) throws IOException {
        
        
        logger.setUseParentHandlers(false); // logger без консоли
        logger.setLevel(Level.INFO);
        FileHandler fhLogger = new FileHandler(".\\HeapSort.txt");
        logger.addHandler(fhLogger);
        SimpleFormatter txt = new SimpleFormatter();
        fhLogger.setFormatter(txt);


        int n = inputNumber("\nВведите количество элементов в массиве: ");
        int[] array = createArray(n);           // пустой массив заданного размера (n)
        fillArrayRand(array);                   // рандомно заполняем массив
        printArrayInt("Исходный массив", array);
        HeapSort(array);
        printArrayInt("Отсортированный массив", array);



    }

    public static void HeapSort(int[] arr){
         // подготовка массива
        logger.info("Создали кучу"); 
        for (int i = arr.length / 2 - 1; i >= 0; i--){ // создаем кучу    
            downMax(i, arr.length, arr);
        }


        // Сама сортировка
        // проходим с конца массива до начала
        logger.info("Сортировка");
        for(int i = arr.length - 1; i > 0; i--){
            // перемещаем элемент из нулевого индекса в конец массива
            swap(0, i, arr);
            //продолжаем перемещать на нулевой индекс максимальный массива  
            downMax(0, i, arr);      
        }
    }

    
    // поик индекса максимального элемента
    public static void downMax(int root, int size, int[] arr){   // на входе корневой элемент root -> индекс максимального элемента массива, size -> размер массива (сколько всего элементов) 
            /*
                      x
                    /   \         
                   /     \
                  L       R
            */
            
            logger.info("Поик индекса максимального элемента ");
            int x = root;          // переменная для хранения индекса максимального элемента
            int L = root * 2 + 1;  // индекс дочернего левого элемента
            int R = root * 2 + 2;  // индекс дочернего правого элемента
            logger.info("Индексы кучи   х = " + x + ";  R = " + R + ";  L= " + L + ";  size = " + size);
            
            // сравнивваем значение элементов массива: с идексом L и с идексом R c с идексом x  
            // с учетом изменения размера кучи
            if(L < size && arr[x] < arr[L]) {
                x = L;  
            }
            
            
            if(R < size && arr[x] < arr[R]) {
                x = R;                
            }
            
            if(x == root){
                logger.info("х == root  --> " + arr[x] + " = " + arr[root]); 
                return;  // если элемент массива с идексом x == root, проверка окончена
            }
                // если нет, меняем значение x на root
            swap(x, root, arr);  // меняем корневой элемент root на найденый максимальный
            downMax(x, size, arr);  // вызов рекурсивного метода для х
    }


    // метод замены элементов в массиве местами
    public static void swap(int i, int j, int[] arr){ 
        int x = arr[i];
        arr[i] = arr[j];
        arr[j] = x;
        logger.info("Меняем местами " + arr[i] + "  и  " + arr[j]);
    }





    /* Вспомогательные методы */

    private static Random random = new Random();
    private static Scanner input = new Scanner(System.in);


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
    

