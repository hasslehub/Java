package HomeWork.HomeWork2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;




public class Task1 {

    // Дана строка sql-запроса "select * from students where ". Сформируйте часть WHERE этого запроса, 
    // используя StringBuilder. Данные для фильтрации приведены ниже в виде json строки.
    // Если значение null, то параметр не должен попадать в запрос.
    // Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}


    public static String[] filter = {"name", "country", "city", "age"};
    public static String request = "select * from students where ";


    public static void main(String[] args) throws IOException {
      String list = ReadLineFromFile(); 
      String[] person = dataPerson(list, filter);
      String newRequest = makeRequest(person, filter);
      System.out.println(newRequest);
    }




    // Считывание строк из файла
    public static String ReadLineFromFile() throws IOException {
        String path = ".\\data.json";
        StringBuilder strBuild = new StringBuilder();
        BufferedReader bufReader = new BufferedReader(new FileReader(path));
        for(;;){
            String line = bufReader.readLine();
            if(line == null){
                bufReader.close();
                break;
            }    
            strBuild.append(line.replace("{", "").replace(" ", "").replace("}", ""));            
        }
        return strBuild.toString(); // "name":"Ivanov","country":"Russia","city":"Moscow","age":"null"
    }



    // Массив судента
    public static String[] dataPerson(String mixData, String[] mask){
        String[] temp = mixData.split(",");
        String[] data = new String[temp.length];
        for(int i = 0; i < mask.length; i++){          
               data[i] = temp[i].replaceAll(mask[i], "").replaceAll(":", "").replaceAll("\"", "");             
        }
        return data;  // IvanovRussiaMoscownull
    }

    // Формируем sql-запрос
    public static String makeRequest(String[] data, String[] mask) {
        StringBuilder result = new StringBuilder(request);
        for (int i = 0; i < mask.length; i++) {
            if(!data[i].equals("null")) {
                result.append(mask[i]);
                result.append(" = ");
                result.append(data[i]);                
                if((i + 1) < mask.length - 1){
                    result.append(" and ");
                }
            }
        }
        return result.toString();  //select * from students where name = Ivanov and country = Russia and city = Moscow
    }
}
