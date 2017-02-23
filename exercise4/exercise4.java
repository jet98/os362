import java.io.*;
import java.util.*;

public class exercise4
{
  // can still easily be run 'stand alone' in worker mode...
  public static void main(String args[]) throws Exception
  {
    exercise4 obj = new exercise4();
    if (args.length == 0)
      obj.runMaster();
    else
      obj.runWorker();
  }

  private void runMaster() throws Exception
  {
    System.out.println();
    List<Process> list = new ArrayList<Process>();
    for (int i = 0; i < 10; i++)
    {
      System.out.printf("Starting process %d ...%n",i);
      Process p = new ProcessBuilder("java", "FindPrimes", "worker").start();
      int start = (i*100);
      int end = start + 100;
      sendParameters(p, start, end);
      list.add(p);
    }

    for (Process p : list)
    {
      getPrimes(p);
    }
  }

  private void runWorker() throws Exception
  {
    Scanner scan = new Scanner(System.in);
    int start = scan.nextInt();
    int end = scan.nextInt();

    for (int i = start; i < end; i++)
    {
      if (i == 0 || i == 1)
      {
        continue;
      }
      else
      {
          Thread.sleep(50); // slow things down a bit...
          boolean prime = true;
          for (int j = 2; j < i; j++)
          {
              if (i%j == 0)
              {
                  prime = false;
                  break;
              }
          }
          if (prime)
          {
            System.out.printf("Found prime %d ...%n", i);
          }
      }
    }
  }

  private void sendParameters(Process p, int start, int end) throws Exception
  {
    System.out.printf("Sending parameters for range %d-%d to process ...%n",start,end);
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(p.getOutputStream(), "UTF-8"));
    pw.println(start);
    pw.println(end);
    pw.flush();
  }

  private void getPrimes(Process p) throws Exception
  {
    p.waitFor();
    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
    String line = br.readLine();
    while (line != null)
    {
      System.out.println(line);
      line = br.readLine();
    }
  }
}
