public abstract class Smoker implements Callable<Boolean>{
    protected final Semaphore m_tobacco;
    protected final Semaphore m_paper;
    protected final Semaphore m_match;
    protected final Semaphore m_supply;
    
    public Smoker(Semaphore supply, Semaphore p, Semaphore m, Semaphore t){
        m_supply = supply;
        m_paper = p;
        m_match = m;
        m_tobacco = t;
    }
    
    public Boolean call();
}