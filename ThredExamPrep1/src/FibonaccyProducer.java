
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
public class FibonaccyProducer implements Runnable
{
    
    private static long fib(long n)
    {
        if ((n==0) || (n == 1))
          {
            return n;
          }
        else
          {
            return (fib(n-1)+ fib(n-2));
            
          }
    }
    
    ArrayBlockingQueue<Long> s1;
    ArrayBlockingQueue<Long> s2;
    AtomicInteger count;

  public FibonaccyProducer(ArrayBlockingQueue<Long> s1 , ArrayBlockingQueue<Long> s2, AtomicInteger count) {
    this.s1 = s1;
    this.s2 = s2;
    this.count = count;
  }

    @Override
    public void run()
    {
        boolean moreNumbersToFetch = true;
        while(moreNumbersToFetch)
          {
            Long num = s1.poll();
            
            if (num == null)
              {
                moreNumbersToFetch = false;
                count.decrementAndGet();
              }
            else
              {
                try
                  {
                    s2.put(fib(num));
                  } catch (InterruptedException ex)
                  {
                    Logger.getLogger(FibonaccyProducer.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
          }
        
        
        for (long s : s1)
          {
            try
              {
                s2.put(fib(s));
              } catch (InterruptedException ex)
              {
                Logger.getLogger(FibonaccyProducer.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
    }
    
}
