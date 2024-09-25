/*

We extend the DIVIDE and CONQUER approach to solving our problem 
using the forkjoin framework in java.

We create a pool object of type ForJoinPool
The pool.invoke starts a thread of our type SumTask
The pool.invoke needs to be shutdown() after completion.

The compute() method in the SumTask class is similar to 
the earlier example with threads -> start()-> run() -> join;

*/
package Examples.forkjoinExample;

import java.util.concurrent.*;

public class ForkJoinExample {
    public static void main(String[] args) {
        int[] array = arrayGenerator(1000);
        
        ForkJoinPool pool = new ForkJoinPool();
       
        long startTime = System.nanoTime();
        
        int sum = pool.invoke(new SumTask(array));
        
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

class SumTask extends RecursiveTask<Integer> {
    private int[] array;
    private int start;
    private int end;
    
    public SumTask(int[] array) {
        this(array, 0, array.length);
    }
    
    public SumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }
    
    @Override
    protected Integer compute() {

//--------> VARIABLE sequential THRESHOLD

        int THRESHOLD = 4;
        
//--------> VARIABLE THRESHOLD

        if (end - start <= THRESHOLD) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } 
        else {
            int mid = start + (end - start) / 2;
            
            SumTask left = new SumTask(array, start, mid);
            SumTask right = new SumTask(array, mid, end);
            
            
            left.fork();
            
            int rightSum = right.compute();
            
            int leftSum = left.join();
            
            return leftSum + rightSum;
        }
    }
    

}
