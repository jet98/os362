import java.io.*;
import java.util.*;

public class Master
{
  public static void main(String args[]) throws Exception
  {
    new Master().go();
  }

  private void go() throws Exception
  {
    List<Process> list = new ArrayList<Process>();
    Scanner in = new Scanner(System.in);
    double start = -20;
    double step = .1;
    for (double i = 0; i < 6; i++)
    {
      Process p = new ProcessBuilder("java", "Worker", start+"", step+"").start();
      list.add(p);
      start += 10;
    }

    List<String> best = new ArrayList<String>();
    for (Process p : list)
    {
      best.add(getValues(p));
    }
    
    String[] a = best.get(0).split(", ");
    double bestX = Double.parseDouble(a[0]);
    double bestY = Double.parseDouble(a[1]);
    double x = 0;
    double y = 0;
    for(int i = 1; i < best.size(); i++){
      a = best.get(i).split(", ");
      x = Double.parseDouble(a[0]);
      y = Double.parseDouble(a[1]);
      if(y > bestY){
        bestX = x;
        bestY = y;
      }
    }
    System.out.println("Best point is (" + bestX + ", " + bestY + ")");
  }

  private String getValues(Process p) throws Exception
  {
    p.waitFor();
    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
    String line = br.readLine();
    System.out.println("Process found (" + line + ")");
    return line;
  }
}
