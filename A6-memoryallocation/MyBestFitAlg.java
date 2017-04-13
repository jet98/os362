import java.util.*;

public class MyBestFitAlg implements ISwapper{
    private int m_memSize;
    private int start = 0;
    private int end;
    private int process = 0;
    LinkedList<IProcess> m_process = new LinkedList<IProcess>();
    LinkedList<Integer> m_memory = new LinkedList<Integer>();
    
    @Override
    public void init(int memSize){
        m_memSize = memSize;
    }
    
    @Override
    public boolean load(IProcess p, IMemory m) throws BlueScreenException{
        m_process.add(p);
        m_memory.add(start);
        end = start + p.getSize();
        if(start < m.getSize() && end < m.getSize()){
            m.load(p, start, end);
            start = start + p.getSize() + 1;
            m_memSize = m_memSize - p.getSize();
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public void unload(IProcess p, IMemory m) throws BlueScreenException{
        m.unload(p);
        int i = 0;
        m_memSize = m_memSize + p.getSize();
        for(IProcess x : m_process){
            if(x.getId() == p.getId()){
                start = m_memory.get(i);
            }
            else{
                i++;
            }
        }
    }
}