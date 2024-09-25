package Examples.threadExample;
/*

Creates 4 threads. 
Call start to start the thread.
Each thread computes the sum. Finally we add the results of 4 sums.

We note inconsistent results when executed multiple times. Why???

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


class ThreadExample2 
{
    public static void main(String[] args) 
    {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
               11,12,13,14,15,16,17,18,19,20
              };
        int s = sum(array);
        System.out.println("Sum is " + s);
    }
    
    
    public static int sum(int[] arr){ // can be a static method
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
            
            ans += ts[i].ans;
        }
        
        return ans;
      }

}

    

   