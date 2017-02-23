import java.io.*;
import java.util.*;
import java.security.SecureRandom;

public class MapReduce
{
  private List<Integer> big = new ArrayList<Integer>();
  // can still easily be run 'stand alone' in worker mode...
  public static void main(String args[]) throws Exception
  {
    MapReduce obj = new MapReduce();
    if (args.length == 0)
      obj.runMaster();
    else
      obj.runWorker();
  }

  private void runMaster() throws Exception
  {
    System.out.println();
    SecureRandom rand = new SecureRandom();
    Scanner in = new Scanner(System.in);
    List<Process> list = new ArrayList<Process>();
    System.out.println("Enter the size of grid. ex 20 = 20 x 20");
    int m = in.nextInt();
    if(m%2 != 0){
        m = m + 1;
    }
    int groups = 4;
    int refX = rand.nextInt(m);
    int refY = rand.nextInt(m);
    for(int i = 0; i < groups; i++) {
      System.out.printf("Starting process for map %d ...%n", i+1);
      Process p = new ProcessBuilder("java", "MapReduce", refX+"", refY+"", (m/groups)+"").start();
      sendParameters(p, refX, refY, (m/groups));
      list.add(p);
    }

    List<String> x = new ArrayList<String>();
    for (Process p : list)
    {
      x.add(getClosest(p));
    }
    
    String[] a = x.get(0).split(",");
    int closestX = Integer.parseInt(a[2]);
    int closestY = Integer.parseInt(a[3]);
    for(int i = 1; i < x.size(); i++){
      if((Integer.parseInt(a[0]) > closestX && closestX < Integer.parseInt(a[2])) || (Integer.parseInt(a[0]) < closestX && closestX > Integer.parseInt(a[2])))
          if((Integer.parseInt(a[1]) > closestY && closestY < Integer.parseInt(a[3])) || (Integer.parseInt(a[1]) < closestY && closestY > Integer.parseInt(a[3]))){
            closestX = Integer.parseInt(a[2]);
            closestY = Integer.parseInt(a[3]);
          }
      a = x.get(i).split(",");
    }
    System.out.println();
    System.out.printf("Closest point to %s,%s is %d,%d\n", a[0], a[1], closestX, closestY);
  }

  private void runWorker() throws Exception
  {
    Scanner scan = new Scanner(System.in);
    int refX = scan.nextInt();
    int refY = scan.nextInt();
    int m = scan.nextInt();
    if(m%2 != 0){
        m = m + 1;
    }
    int[][]  ary = new int[10][10];
    //maps m points  row = x,y,x,y...
    for(int i = 0; i < ary.length; i++){
      SecureRandom rand = new SecureRandom();
      for(int j = 0; j < ary.length; j++){
        int r = rand.nextInt(m);
        ary[i][j] = r;
            
        // prints test to verify closest point
        //
        // System.out.print(r);
        // if(j%2 != 0)
        //     System.out.println();
        // else
        //     System.out.print(",");
      }
      // System.out.println();
    }
    int tempX = 0; 
    int tempY = 0; 
    int closestX = ary[0][0]; //input x
    int closestY = ary[0][1]; //input y
    
    for (int i = 0; i < ary.length; i++){
      for(int j = 0; j < ary.length; j+=2){
        tempX = ary[i][j];
        tempY = ary[i][j+1];
        if((refX > closestX && closestX < tempX) || (refX < closestX && closestX > tempX))
          if((refY > closestY && closestY < tempY) || (refY < closestY && closestY > tempY)){
            closestX = tempX;
            closestY = tempY;
          }
      }
    }
    Thread.sleep(50); // slow things down a bit...
    
    System.out.printf("%d,%d,%d,%d\n", refX, refY, closestX, closestY);
  }
  
  private void sendParameters(Process p, int refX, int refY, int groupBy) throws Exception
  {
    System.out.printf("Sending %d parameters for %d,%d to process ...%n", groupBy, refX, refY);
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(p.getOutputStream(), "UTF-8"));
    pw.println(refX);
    pw.println(refY);
    pw.println(groupBy);
    pw.flush();
  }

  private String getClosest(Process p) throws Exception
  {
    p.waitFor();
    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
    String line = br.readLine();
    String[] l = line.split(",");
      System.out.printf("Closest point to %s,%s is %s,%s\n", l[0], l[1], l[2], l[3]);
      return line;
  }
}
