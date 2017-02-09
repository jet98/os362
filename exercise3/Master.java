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
    char start = 'a';
    String word = "dog";
    for(char alpha = start; alpha <= 'z';alpha++) {
        System.out.printf("Starting process for range %c ...%n",alpha);
        Process p = new ProcessBuilder("java", "Worker", alpha+"", word+"").start();
        list.add(p);
      }

    for (Process p : list)
    {
      getPrimes(p);
    }
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
