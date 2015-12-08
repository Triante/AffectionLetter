package jorgeandcompany.loveletter;

/**
 * Created by kd on 12/4/15.
 * From: http://cs.wellesley.edu/~ecom/lecture/ConsumerProducerRight.html
 */
public class Buffer {
        private int contents;
        private boolean empty = true;

    /**
     * Puts the value of a card into the buffer
     * @param i the value to be added
     * @throws InterruptedException
     */
        public synchronized void put(int i) throws InterruptedException {
            while (empty == false) { 	//wait till the buffer becomes empty
                try { wait(); }
                catch (InterruptedException e) {throw e;}
            }
            contents = i;
            empty = false;
            System.out.println("Producer: put..." + i);
            notify();
        }


    /**
     * Gets the value from the buffer.
     * @return the value from the buffer
     * @throws InterruptedException
     */
        public synchronized int get () throws InterruptedException {
            while (empty == true)  {	//wait till something appears in the buffer
                try { 	wait(); }
                catch (InterruptedException e) {throw e;}
            }
            empty = true;
            notify();
            int val = contents;
            System.out.println("Consumer: got..." + val);
            return val;
        }
    }
