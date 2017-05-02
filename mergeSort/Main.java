import java.util.*;
import java.util.concurrent.*;
import java.security.SecureRandom;

public class Main{
  List<List<number>> list = new ArrayList<List<number>>();
  List<number> fin = new ArrayList<number>();

  public static void main(String args[]) throws Exception{
    Main main = new Main();
    main.go();
    main.run();
  }

  public void go(){
    SecureRandom rand = new SecureRandom();
    Scanner in = new Scanner(System.in);
    System.out.println("Enter amount of numbers (powers of 2)");

    int x = in.nextInt();
    for (int i = 0; i < x; i++){
      int t = rand.nextInt(50) + 1;
      List<number> temp = new ArrayList<number>();
      temp.add(new number(t));
      list.add(temp);
    }
  }

  public void run() throws Exception{
    List<Callable<List<number>>> tasks = new ArrayList<Callable<List<number>>>();
    while(list.size() > 0){
      if(list.size() > 1){
        Callable<List<number>> task = new number(list.get(0), list.get(1));
        tasks.add(task);
        if(list.get(0).get(0).getNum() < list.get(1).get(0).getNum()){
          list.remove(0);
        } 
        else{
          list.remove(1);
        }
      } 
      else{
        Callable<List<number>> task = new number(list.get(0));
        tasks.add(task);
        list.remove(0);
      }
    }

    ExecutorService executor = Executors.newFixedThreadPool(4);
    List<Future<List<number>>> future = executor.invokeAll(tasks);
    for (Future<List<number>> f : future){
      System.out.print("List contains: ");
      for(int i = 0; i < f.get().size(); i++){
        System.out.printf("%s ", f.get().get(i).getNum());
      }
      System.out.println();
    }
    executor.shutdown();
  }

  public class number implements Callable<List<number>>{
    private List<number> l1 = new ArrayList<number>();
    private List<number> l2 = new ArrayList<number>();
    private List<number> finList = new ArrayList<number>();
    private int num;
  
    public number(List<number> n, List<number> m){
      l1 = n;
      l2 = m;
    }
    
    public number(List<number> n){
      l1 = n;
      l2.add(new number(100));
    }

    public number(int n){
      num = n;
    }

    public void addNum(number n){
      finList.add(n);
    }

    public int getNum(){
      return num;
    }

    @Override
    public List<number> call(){
      System.out.printf("Working on comparing: %s & %s%n", l1.get(0).getNum(), l2.get(0).getNum());
      if(l1.get(0).getNum() < l2.get(0).getNum()){
        fin.add(l1.get(0));
      }
      else{
        fin.add(l2.get(0));
      }
      
      return (List<number>)fin;
    }
  }
}