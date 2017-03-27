
public class Fork{
        private boolean isAvailable;
        
        public Fork(){
            isAvailable = true;
        }
        
        public synchronized boolean getAvailable(){
            return isAvailable;
        }
        
        public synchronized void setTaken(boolean state){
            isAvailable = state;
        }
    }