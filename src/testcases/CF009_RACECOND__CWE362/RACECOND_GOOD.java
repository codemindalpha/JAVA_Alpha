package CF009_RACECOND__CWE362;

import java.util.concurrent.locks.ReentrantLock;

public class RACECOND_GOOD {
    private ReentrantLock mutex = new ReentrantLock();
    public void main(String[] args) {
        this.runThread();
    }
    public int runThread(){
        try {
            mutex.lock();
            return this.runThread();
        } finally {
            mutex.unlock();
        }
    }
}
