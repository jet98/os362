import java.util.*;

public class MyFirstFitAlg implements ISwapper{
    LinkedList<MyProcess> process = new LinkedList<MyProcess>();
    private int start = 0;
    private int end = 0;
    
    @Override
    public void init(int memSize){
        process.add(new MyProcess(true, 0, memSize, -1));
    }
    
    @Override
    public boolean load(IProcess p, IMemory m) throws BlueScreenException{
        process.add(new MyProcess(false, start, end, p.getId()));
        for(MyProcess x : process){
            if(x.getEnd() - x.getStart() > p.getSize()){
                if(x.getHole()){
                    x.setStart(start + p.getSize() + 1);
                    x.setEnd(x.getEnd() - p.getSize());
                }
            }
        }
        if(start < (m.getSize() - p.getSize()) && end < m.getSize()){
            end = start + p.getSize();
            System.out.println(start);
            m.load(p, start, end);
            start = start + p.getSize() + 1;
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public void unload(IProcess p, IMemory m) throws BlueScreenException{
        m.unload(p);
        for(MyProcess x : process){
            if(p.getId() == x.getI()){
                start = x.getStart();
                x.setHole(true);
            }
        }
    }
    
    private class MyProcess{
        private int start;
        private int end;
        private boolean hole;
        private int id;
        
        public MyProcess(boolean h, int s, int e, int i){
            hole = h;
            start = s;
            end = e;
            id = i;
        }
        
        public boolean getHole(){
            return hole;
        }
        
        public int getStart(){
            return start;
        }
        
        public int getEnd(){
            return end;
        }
        
        public int getI(){
            return id;
        }
        
        public void setStart(int x){
            start = x;
        }
        
        public void setEnd(int x){
            end = x;
        }
        
        public void setHole(boolean x){
            hole = x;
        }
    }
}