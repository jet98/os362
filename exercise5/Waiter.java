
public class Waiter {
	public synchronized boolean isAvailable(Fork a, Fork b){
		if(a.getAvailable() == true && b.getAvailable() == true){
			return true;
		}
		else{
			return false;
		}
	}
}
