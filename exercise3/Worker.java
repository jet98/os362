import java.io.*;

public class Worker
{
  // can easily be run 'stand alone'...
  public static void main(String args[]) throws Exception
  {
    new Worker().go(args);
  }

  private void go(String args[]) throws Exception
  {
    String start = args[0];
    String word = args[1];
    File file = new File(System.getProperty("user.dir") + "/files");
    File[] list = file.listFiles();
    for (File x : list)
    {
      if (!x.getName().startsWith(start))
      {
        continue;
      }
      else
      {
          Thread.sleep(50); // slow things down a bit...
          
          BufferedReader reader = new BufferedReader(new FileReader(x));
          String line = reader.readLine();
          while(line != null){
            if(line.contains(word)){
              System.out.printf("Found %s in %s%n", word, x.getName());
            }
            line = reader.readLine();
          }
      }
    }
  }
}
