package HomeWork.HomeWork1;

import java.util.Scanner;

public class Task1 {

    // Написать программу вычисления n-ого треугольного числа
    // https://ru.wikipedia.org/wiki/Треугольное_число
    // T1 = 1   T2 = 1 + 2    T3 = 1 + 2 + 3    T4 = 1 + 2 + 3 + 4
    // Tn = n(n + 1)/2


    public static void main(String[] args) {
        int n = inputNumber("\nВведите число: ");
	    System.out.printf("Треугольное число для %d = %d\n\n", n, triangularNumber(n));
    }  

    


    // Ввод числа int
    private static Scanner input = new Scanner(System.in);
    public static int inputNumber(String text) {      
        System.out.print(text);
        return input.nextInt();
    }
    


    // Рекурсия вычисления n-ого треугольного числа
	public static int triangularNumber(int x){
		//return (x * (x + 1)) / 2;
        if (x == 1) {
            return 1;
        }
        return triangularNumber(x - 1) + x;
	}
}
