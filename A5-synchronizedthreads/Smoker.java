import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Smoker implements Callable<Boolean>{
  private final Supplier items;
  private final String m_name;
  private final Tobacco m_tobacco;
  private final Matches m_match;
  private final Paper m_paper;
  
  public Smoker(Supplier s, String name, Matches m, Paper p, Tobacco t){
    items = s;
    m_name = name;
    m_tobacco = t;
    m_paper = p;
    m_match = m;
  }

  @Override
  public Boolean call(){
    while(true){
      try{
        //match guy
        if(m_match == null){
          if(m_paper.getAvailable()){
            m_paper.grab();
            if(m_tobacco.getAvailable()){
              m_tobacco.grab();
              System.out.printf("%s started smoking%n", m_name);
              Thread.sleep(1000);
              m_paper.releasePaper(m_paper);
              m_tobacco.releaseTobacco(m_tobacco);
              System.out.printf("%s stopped smoking%n", m_name);
            }
            else{
              m_paper.releasePaper(m_paper);
            }
          }
        }
        //paper guy
        if(m_paper == null){
          if(m_match.getAvailable()){
            m_match.grab();
            if(m_tobacco.getAvailable()){
              m_tobacco.grab();
              System.out.printf("%s started smoking%n", m_name);
              Thread.sleep(1000);
              m_match.releaseMatches(m_match);
              m_tobacco.releaseTobacco(m_tobacco);
              System.out.printf("%s stopped smoking%n", m_name);
            }
            else{
              m_match.releaseMatches(m_match);
            }
          }
        }
        //tobacco guy
        if(m_tobacco == null){
          if(m_match.getAvailable()){
            m_match.grab();
            if(m_paper.getAvailable()){
              m_paper.grab();
              System.out.printf("%s started smoking%n", m_name);
              Thread.sleep(1000);
              m_match.releaseMatches(m_match);
              m_paper.releasePaper(m_paper);
              System.out.printf("%s stopped smoking%n", m_name);
            }
            else{
              m_paper.releasePaper(m_paper);
            }
          }
        }
      }
      catch(InterruptedException ex){
        System.err.println(ex);
      }
    }
  }  
}