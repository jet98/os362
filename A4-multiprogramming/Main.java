
import java.util.*;
import java.util.concurrent.*;
import java.io.*;

public class Main {

  public static void main(String[] args) throws Exception {
    new Main().go();
  }

  private void go() throws Exception {
    Scanner in = new Scanner(System.in);
    // System.out.println("Please enter file 1:");
    // String file1 = in.nextLine();
    // System.out.println("Please enter file 2:");
    // String file2 = in.nextLine();
    final matrix a = new matrix("./A1.txt");
    final matrix b = new matrix("./B1.txt");
    assert (a.getRows() == b.getCols());
    final matrix c = new matrix(a.getRows(), b.getCols());
    int pool = 6;
    List<Callable<Boolean>> list = new ArrayList<Callable<Boolean>>();

    System.out.printf("Creating threads for c...%n");
    Callable<Boolean> task = new Callable<Boolean>() {
      @Override
      public Boolean call() {
        for (int i = 0; i < a.getRows(); i++) {
          for (int j = 0; j < b.getCols(); j++) {
            int temp = 0;
            for (int k = 0; k < a.getCols(); k++) {
              try {
                Thread.sleep(1000);
              } catch (InterruptedException ie) {
                System.err.println(ie);
              }
              temp += a.getCell(i, k) * b.getCell(k, j);
            }
            System.out.print(temp + "\t");
            c.setCell(i, j, temp);
          }
          System.out.println();
        }

        return true;
      }
    };
    list.add(task);

    ExecutorService executor = Executors.newFixedThreadPool(pool);
    List<Future<Boolean>> future = executor.invokeAll(list);
     for (Future<Boolean> f : future){
     	if (!f.get())
     	  throw new Exception("Thread completion error!");
     }
     
    System.out.println("Matrix total = " + c.getCellTotal());
    executor.shutdown();
  }

  public class matrix {

    private String m_file;
    private int m_rows;
    private int m_cols;
    private int[][] m_mat;

    public matrix(String f) {
      m_file = f;
      File file = new File(f);
      int row = 0;
      int col = 0;
      try {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try {
          String line = reader.readLine();
          while (line != null) {
            String temp[] = line.split(",");
            col = temp.length;
            row++;
            line = reader.readLine();
          }
          m_rows = row;
          m_cols = col;
        } catch (IOException ex) {
          new Error(ex);
        }
      } catch (FileNotFoundException fnf) {
        new Error(fnf);
      }

      m_mat = new int[m_cols][m_rows];

      try {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try {
          String line = reader.readLine();
          int s = 0;
          while (line != null) {
            String temp[] = line.split(",");
            for (int i = 0; i < m_cols; i++) {
              m_mat[i][s] = Integer.parseInt(temp[i].trim());
            }
            s++;
            line = reader.readLine();
          }
        } catch (IOException ex) {
          new Error(ex);
        }
      } catch (FileNotFoundException fnf) {
        new Error(fnf);
      }
    }

    public matrix(int r, int c) {
      m_cols = c;
      m_rows = r;
      m_mat = new int[m_cols][m_rows];
      for (int i = 0; i < m_rows; i++) {
        for (int j = 0; j < m_cols; j++) {
          m_mat[j][i] = 0;
        }
      }
    }

    public int getRows() {
      return m_rows;
    }

    public int getCols() {
      return m_cols;
    }

    public int getCell(int r, int c) {
      return m_mat[c][r];
    }

    public void setCell(int r, int c, int value) {
      m_mat[c][r] = value;
    }

    public int getCellTotal() {
      int sum = 0;
      for (int i = 0; i < m_rows; i++) {
        for (int j = 0; j < m_cols; j++) {
          sum += m_mat[j][i];
        }
      }
      return sum;
    }

    @Override
    public String toString() {
      return Integer.toString(getCellTotal());
    }
  }
}
