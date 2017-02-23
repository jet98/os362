public class Worker
{
  // can easily be run 'stand alone'...
  public static void main(String args[]) throws Exception
  {
    new Worker().go(args);
  }

  private void go(String args[]) throws Exception
  {
    double start = Double.parseDouble(args[0]);
    double end = start + 10;
    double step = Double.parseDouble(args[1]);
    double[] x = new double[2];
    x[0] = start;
    x[1] = (((-1 * Math.pow(start, 4)) + (20 * Math.pow(start, 3))) / 500) + 24;
    double temp = 0;
    for(double i = start; i < end; i+=step){
      temp = (((-1 * Math.pow(i, 4)) + (20 * Math.pow(i, 3))) / 500) + 24;
      if(temp > x[1]){
        x[0] = i;
        x[1] = temp;
      }
    }
    
    System.out.printf("%.2f, %.2f\n", x[0], x[1]);
  }
}
