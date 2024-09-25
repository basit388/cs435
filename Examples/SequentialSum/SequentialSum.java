
package Examples.SequentialSum;

public class SequentialSum {
    
    public static void main(String [] args){
        
        int [] a = arrayGenerator(10000000);
        
        long startTime = System.nanoTime();
        
        int sum = sum(a);
        
        long endTime = System.nanoTime();
        System.out.println("Sum = " + sum);
        System.out.println("Time (ns)= " + (endTime-startTime));
        
        
    }
    public static int sum(int[] arr){ // can be a static method
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
    
}
