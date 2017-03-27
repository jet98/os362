public class Main{
    public static void main(String args[]){
        new Main.go();
    }
    
    public void go(){
        Semaphore supply = new Semaphore(0, true);
        Semaphore paper = new Semaphore(0, true);
        Semaphore match = new Semaphore(0, true);
        Semaphore tobacco = new Semaphore(0, true);
        List<Callable<Boolean>> threads = new ArrayList<Callable<Boolean>>();
        thread.add(new Matches(supply, match, tobacco);
        thread.add(new Tobacco(supply, paper, tobacco));
        thread.add(new Paper(supply, paper, match);
    }
}