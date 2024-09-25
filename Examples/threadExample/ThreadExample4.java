package Examples.threadExample;
/*
We observe the cost of parallelism in terms of time.
Uses an arrayGenerator method that creates a large array of random integers
First we compute the sum sequentially and record time.

We create 4 threads. 
Call start to start the thread and end with a join.
We donot observe inconsistent results anymore. However, we note that the 
sequential time is significantly lower than parallel time. Why?

*/
class SumThread extends java.lang.Thread {

  int lo; // arguments
  int hi;
  int[] arr;

  int ans = 0; // result 
    
  SumThread(int[] a, int l, int h) { 
    lo=l; hi=h; arr=a;
  }

  public void run() { //override must have this type
    for(int i=lo; i < hi; i++)
      ans += arr[i];
      System.out.println("["+lo+"] to ["+(hi-1) + "] = "+ans);
  }
}


class ThreadExample4 
{
    public static void main(String[] args) 
    {
        int [] a = arrayGenerator(1000);
        
        long startTime = System.nanoTime();
        
        int sum = sequentialSum(a);
        
        long endTime = System.nanoTime();
        
        
        System.out.println("Sequential Sum = " + sum);
        System.out.println("Sequential Time (ns)= " + (endTime-startTime));
        
        startTime = System.nanoTime();
        
        sum = parallelSum(a);
        
        endTime = System.nanoTime();        
        System.out.println("Parallel Sum = " + sum);
        System.out.println("Parallel Time (ns)= " + (endTime-startTime));
                
        
    }
    public static int sequentialSum(int[] arr){ // can be a static method
        int len = arr.length;
        int ans = 0;
        for(int i=0;i<len;i++)
            ans += arr[i];
        
        return ans;
    }
    public static int [] arrayGenerator(int size)
    {
        int [] array = new int[size];
        for(int i=0;i<size;i++)
        {
            array[i] = (int)(Math.random() * 1000);
            
        }
        return array;
    }
    
    
    
    public static int parallelSum(int[] arr){ // can be a static method
        int len = arr.length;
        int ans = 0;
        
        SumThread[] ts = new SumThread[4];
        
        for(int i=0; i < 4; i++) // do parallel computations
        {
            ts[i] = new SumThread(arr,i*len/4,(i+1)*len/4);
            ts[i].start(); // start not run
        }
        for(int i=0; i < 4; i++) // combine results
        {
            try {
                ts[i].join(); // Wait for the thread to finish
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted while waiting.");
            }
            ans += ts[i].ans;
        }
        
        return ans;
      }

}

    

   