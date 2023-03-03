package HomeWork5;


import java.util.LinkedList;
import java.util.Queue;

/*
 *          Реализовать задание и печать карты для волнового алгоритма
 */

public class Maze {
    
    public static void main(String[] args) {

        MapGenerator map = new MapGenerator();
        System.out.println("\n" + new MapPrinter().matrixMaze(map.getMap()));

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

    //  -1 Стена, препятствие
    //   0 свободные ячейки
    public MapGenerator() {
    int[][] map = {
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 },
        { -1, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, -1 },
        { -1, 0, 0, 0, -1, 0, 0, -1, 0, 0, 0, 0, 0, 0, -1 },
        { -1, 0, 0, 0, -1, 0, -1, -1, -1, -1, 0, 0, 0, 0, -1 },
        { -1, 0, 0, 0, -1, 0, -1, 0, 0, -1, 0, 0, 0, 0, -1 },
        { -1, -1, -1, 0, -1, 0, -1, 0, 0, -1, 0, 0, 0, 0, -1 },
        { -1, 0, 0, 0, -1, 0, -1, 0, 0, -1, -1, -1, 0, 0, -1 },
        { -1, 0, 0, 0, -1, 0, 0, 0, 0, -1, 0, 0, 0, 0, -1 },
        { -1, 0, 0, 0, -1, 0, 0, 0, 0, -1, 0, 0, 0, 0, -1 },
        { -1, 0, 0, 0, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, -1 },
        { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 },
        { -1, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, -1 },
        { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 },
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
          sb.append(String.format("%5d", map[row][col]));
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

    while (queue.size() != 0) {        // пока очередь не пуста
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
}
