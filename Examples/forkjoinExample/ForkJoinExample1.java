/*

We extend the previous example of using the forkjoin framework in java.

This time we determine the available number of threads before creating the pool.
We also query the number of threads used for the tasks.

*/
package Examples.forkjoinExample;
import java.util.concurrent.*;


public class ForkJoinExample1 {
    public static void main(String[] args) {
        int[] array = arrayGenerator(1000);
        
        int processors = Runtime.getRuntime().availableProcessors();
        
        System.out.println("Number of processors/cores: " + processors);
        System.out.println("Available Free memory:" + Runtime.getRuntime().freeMemory());
        
        
        //Create a pool with number of threads = processors
        ForkJoinPool pool = new ForkJoinPool(processors);
       
        System.out.println("getParallelism "+pool.getParallelism());
        long startTime = System.nanoTime();
        
        int sum = pool.invoke(new SumTask(array));
        
        System.out.println("getPoolSize= "+pool.getPoolSize());
       
        pool.shutdown();
        
        long endTime = System.nanoTime();
        
        System.out.println("Sum = " + sum);
        System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");
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
}

class SumTaskA extends RecursiveTask<Integer> {
    private int[] array;
    private int start;
    private int end;
    
    public SumTaskA(int[] array) {
        this(array, 0, array.length);
    }
    
    public SumTaskA(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }
    
    @Override
    protected Integer compute() {
        int THRESHOLD = 4;
        if (end - start <= THRESHOLD) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } 
        else {
            int mid = start + (end - start) / 2;
            
            SumTaskA left = new SumTaskA(array, start, mid);
            SumTaskA right = new SumTaskA(array, mid, end);
            
            
            left.fork();
            
            int rightSum = right.compute();
            
            int leftSum = left.join();
            
            return leftSum + rightSum;
        }
    }
}
