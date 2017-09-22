
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
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
public class FibonaccyConsumer implements Runnable
{
    
    
    ArrayBlockingQueue<Long> s;
    long sum = 0;
    AtomicInteger count;

  public FibonaccyConsumer(ArrayBlockingQueue<Long> numbersProduced , AtomicInteger count) {
    this.s = numbersProduced;
    this.count = count;
  }


  @Override
  public void run() 
  {
      
    while(count.intValue()>0)
      {
        try
          {
            Long item = s.poll(10, TimeUnit.SECONDS);
            sum += item;
              System.out.println(item);
               System.out.println("SUM " +this.sum);
              
          } catch (InterruptedException ex)
          {
            Logger.getLogger(FibonaccyConsumer.class.getName()).log(Level.SEVERE, null, ex);
          }
        
      }
    
    
  }
  
  public void getSum()
  {
     
      
  }

    
}
