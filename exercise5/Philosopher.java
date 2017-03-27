import java.util.concurrent.Callable;

public class Philosopher implements Callable<Boolean>{
        private Fork lFork;
        private Fork rFork;
        private String m_name;
        private boolean isEating;
        private Waiter m_waiter;
        public Philosopher(Fork l, Fork r, String name){
          lFork = l;
          rFork = r;
          m_name = name;
          isEating = true;
          m_waiter = new Waiter();
        }
        
        public Boolean call() throws InterruptedException{
            //check for fork
        	  if(m_waiter.isAvailable(lFork, rFork) == true){
                lFork.setTaken(false);
                rFork.setTaken(false);
                System.out.println(m_name + " is eating");
                Thread.sleep(1000);
                isEating = true;
                
            }
        	  else if(isEating == true){
        		  lFork.setTaken(true);
                  rFork.setTaken(true);
                  isEating = false;
        	  }
            else{
            	Thread.sleep(1000);
                System.out.println(m_name + " is hungry");
            }
        	return true;
        }
    }