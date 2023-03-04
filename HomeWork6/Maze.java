package HomeWork6;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;



public class Maze {

  /* Реализивать волновой алгоритм */

    
    public static void main(String[] args) {

      MapGenerator map = new MapGenerator();
      System.out.println(new MapPrinter().matrixMaze(map.getMap()));
      //logger.info("Лабиринт\n" + new MapPrinter().matrixMaze(map.getMap()));
    
      WaveAlgorithm lee = new WaveAlgorithm(map.getMap());    
      lee.Colorize(new Piont2D(3, 3));  // Стартовая точка    
      System.out.println(new MapPrinter().matrixMaze(map.getMap()));
        
      lee.getRoad(new Piont2D(10, 10)); // Точка выхода
      System.out.println(new MapPrinter().matrixMaze(map.getMap()));

      }
    
}



///////////////////////////////////////


class Piont2D {
    int x, y;
  
    public Piont2D(int x, int y) {
        this.x = x;
        this.y = y;
    }
  
    public int getX() {
        return x;
    }
  
    public int getY() {
        return y;
    }
  
    @Override
    public String toString() {
        return String.format("x: %d  y: %d", x, y);
    }
}




class MapGenerator {
    int[][] map;

    public MapGenerator() {
    int[][] map = {
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 00, 00, 00, -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
        { -1, 00, 00, 00, 00, 00, 00, -1, 00, 00, 00, 00, 00, 00, -1 },
        { -1, 00, 00, 00, -1, 00, 00, -1, 00, 00, 00, 00, 00, 00, -1 },
        { -1, 00, 00, 00, -1, 00, -1, -1, -1, -1, 00, 00, 00, 00, -1 },
        { -1, 00, 00, 00, -1, 00, -1, 00, 00, -1, 00, 00, 00, 00, -1 },
        { -1, -1, -1, 00, -1, 00, -1, 00, 00, -1, 00, 00, 00, 00, -1 },
        { -1, 00, 00, 00, -1, 00, -1, 00, 00, -1, -1, -1, 00, 00, -1 },
        { -1, 00, 00, 00, -1, 00, 00, 00, 00, -1, 00, 00, 00, 00, -1 },
        { -1, 00, 00, 00, -1, 00, 00, 00, 00, -1, 00, 00, 00, 00, -1 },
        { -1, 00, 00, 00, -1, -1, -1, -1, -1, -1, 00, 00, 00, 00, -1 },
        { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
        { -1, 00, 00, 00, -1, -1, -1, -1, -1, -1, -1, 00, 00, 00, -1 },
        { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }
    };

    this.map = map;
  }

  public int[][] getMap() {
    return map;
  }

  public void setStart(Piont2D pos) {
    map[pos.x][pos.y] = -2;  
  }

  public void setExit(Piont2D pos) {
    map[pos.x][pos.y] = -3;

  }
}



class MapPrinter {
    public MapPrinter() {
    }
  
    public String matrixMaze(int[][] map) {
      StringBuilder sb = new StringBuilder(); 
        
      for (int row = 0; row < map.length; row++) {
        for (int col = 0; col < map[row].length; col++) {
          sb.append(String.format("%6d", map[row][col]));
        }
        sb.append("\n");
      }
      for (int i = 0; i < 3; i++) {
        sb.append("\n");
      }
      return sb.toString();
    }
  

    
public String mapColor(int[][] map) {
    StringBuilder sb = new StringBuilder();
  
    for (int row = 0; row < map.length; row++) {
    for (int col = 0; col < map[row].length; col++) {
        switch (map[row][col]) {
        case 0:
            sb.append("░░░");
            break;
        case -1:
            sb.append("▓▓▓");
            break;
        case -2:
            sb.append("R");
            break;
        case -3:
            sb.append("E");
            break;
        default:
            break;
        }
    }
    sb.append("\n");
    }
    for (int i = 0; i < 3; i++) {
        sb.append("\n");
    }
    return sb.toString();
    }
}


class WaveAlgorithm {
    int[][] map;

  public WaveAlgorithm(int[][] map) {
    this.map = map;
  }
  
  // заполняем карту(закрашиваем)
  public void Colorize(Piont2D startPoint) {
    Queue<Piont2D> queue = new LinkedList<Piont2D>();
    System.out.println("Начало пути:  " + startPoint.toString() + "\n");
    queue.add(startPoint);               // поставили в очередь ячейку старта 
    map[startPoint.x][startPoint.y] = 1; // меняем значение 0(свободная) на 1

    while (queue.size() != 0) {          // пока очередь не пуста
        Piont2D p = queue.remove();      // извлекаем 1-ю одередь

      if (map[p.x - 1][p.y] == 0) {            // если можно идти вверх
        queue.add(new Piont2D(p.x - 1, p.y));  // добавляем ячейку в очередь
        map[p.x - 1][p.y] = map[p.x][p.y] + 1; // увеличиваем значение ячейки на + 1
      }

      if (map[p.x][p.y + 1] == 0) {             // если можно идти вправо
        queue.add(new Piont2D(p.x, p.y + 1));   // добавляем ячейку в очередь
        map[p.x][p.y + 1] = map[p.x][p.y] + 1;  // увеличиваем значение ячейки на + 1
      }

      if (map[p.x + 1][p.y] == 0) {             // если можно идти вниз
        queue.add(new Piont2D(p.x + 1, p.y));   // добавляем ячейку в очередь
        map[p.x + 1][p.y] = map[p.x][p.y] + 1;  // увеличиваем значение ячейки на + 1
      }

      if (map[p.x][p.y - 1] == 0) {             // если можно идти влево
        queue.add(new Piont2D(p.x, p.y - 1));   // добавляем ячейку в очередь
        map[p.x][p.y - 1] = map[p.x][p.y] + 1;  // увеличиваем значение ячейки на + 1
      }
    }
  }

  public ArrayList<Piont2D> getRoad(Piont2D exit) {
    ArrayList<Piont2D> road = new ArrayList<>();
    road.add(exit);
    int max = map[exit.x][exit.y]; // записываем значение ячейки выхода как максимальное
    int step = 2;                  // кол-во шагов (учтем ячейку входа и выхода)
    map[exit.x][exit.y] = -3;      // меняем значение ячейки выхода на -3
    System.out.println("Точка выхода:  " + exit.toString() + "\n");

    while(max != 1){
        if (map[exit.x - 1][exit.y] == max - 1) {            // если можно идти вверх
            road.add(new Piont2D(exit.x - 1, exit.y));       // добавили индексы в список
            map[exit.x - 1][exit.y] = -3;                    // записываем в ячейку значение -3
            exit = new Piont2D(exit.x - 1, exit.y);          // меняем идекс для следующего поиска
            max--;
          }
    
          if (map[exit.x][exit.y + 1] == max - 1) {          // если можно идти вправо
            road.add(new Piont2D(exit.x, exit.y + 1));       // добавили индексы в список
            map[exit.x][exit.y + 1] = -3;                    // записываем в ячейку значение -3
            exit = new Piont2D(exit.x, exit.y + 1);          // меняем идекс для следующего поиска
            max--;
          }
    
          if (map[exit.x + 1][exit.y] == max - 1) {          // если можно идти вниз
            road.add(new Piont2D(exit.x + 1, exit.y));       // добавили индексы в список
            map[exit.x + 1][exit.y] = -3;                    // записываем в ячейку значение -3
            exit = new Piont2D(exit.x + 1, exit.y);          // меняем идекс для следующего поиска
            max--;
          }
    
          if (map[exit.x][exit.y - 1] == max - 1) {          // если можно идти влево
            road.add(new Piont2D(exit.x, exit.y - 1));       // добавили индексы в список
            map[exit.x][exit.y - 1] = -3;                    // записываем в ячейку значение -3
            exit = new Piont2D(exit.x, exit.y - 1);          // меняем идекс для следующего поиска
            max--;
          }
        step++;
        
    }
    System.out.println("Количество шагов:  " + step + "\n");
    for(int i = road.size() - 1; i >= 0; i--) {
        System.out.println((road.size() - i) + " -> " + road.get(i));
    }
    System.out.println();
    return road; 

    
  }
  
}
