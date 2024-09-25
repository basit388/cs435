package threadExample;

class C extends java.lang.Thread {
    int i;
    C(int i) { 
        this.i = i; 
    }
    public void run() {
        
        System.out.println("Thread " + i + " says hi");
        System.out.println("Thread " + i + " says bye");
    }
}

class ThreadExample 
{
    public static void main(String[] args) 
    {
        for(int i=1; i <= 5; ++i) {
            C c = new C(i);
            c.start();
        }
    }
}

    

   