package Examples.threadExample;
/*

Use the DIVIDE and CONQUER approach to solving our problem.
Obtain the max number of cores available from the system.
Create this many threads.
Each thread divides the work determined by the THRESHOLD recursively
into two worker threads at each level

set the THRESHOLD to 10; 
This creates many threads increating the complexity.

*/
class SumThread extends java.lang.Thread {

    int lo; // arguments
    int hi;
    int[] arr;

    int ans = 0; // result 
    
    SumThread(int[] a, int l, int h) { 
        lo=l; hi=h; arr=a;
    }

    @Override
    public void run(){ // override
        //THRESHOLD is 
        if(hi - lo < 100){
            for(int i=lo; i < hi; i++)
             ans += arr[i];
        }
        else{
            SumThread left = new SumThread(arr,lo,(hi+lo)/2);
            SumThread right= new SumThread(arr,(hi+lo)/2,hi);
            left.start();
            right.start();
            
            try {
                left.join(); // Wait for the thread to finish
                right.join();
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted while waiting.");
            }

            ans = left.ans + right.ans;
            System.out.println("["+lo+"] to ["+(hi-1) + "] = "+ans);
        }
    }
}

class ThreadExample6 
{
    static int Threads;
    public static void main(String[] args) 
    {
        
        /////////////////////////////////////////////////////////////////
        Threads = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processors (cores): " + Threads);
        /////////////////////////////////////////////////////////////////
        
        
        int [] a = arrayGenerator(1000);
        
        long startTime = System.nanoTime();
        
        int sum = sequentialSum(a);
        
        long endTime = System.nanoTime();
        
        
        System.out.println("Sequential Sum = " + sum);
        System.out.println("Sequential Time (ns)= " + (endTime-startTime));
        
        startTime = System.nanoTime();
        
        /////////////////////////////////////////////////////////////////
        SumThread st = new SumThread(a,0,a.length);
        st.run();
        /////////////////////////////////////////////////////////////////
        
        sum = st.ans;
        
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
            array[i] = (int)(Math.random() * 100);
            
        }
        return array;
    }
    
    
    
    public static int parallelSum(int[] arr){ // can be a static method
        int len = arr.length;
        int ans = 0;
        
        ////////////////////////////////////////////
        // we use DIVIDE and CONQUER approach
        ////////////////////////////////////////////
        
        SumThread[] ts = new SumThread[Threads];
        
        for(int i=0; i < Threads; i++) // do parallel computations
        {
            ts[i] = new SumThread(arr,i*len/Threads,(i+1)*len/Threads);
            ts[i].start(); // start not run
        }
        for(int i=0; i < Threads; i++) // combine results
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

    

   