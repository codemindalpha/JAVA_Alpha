package CF009_RACECOND__CWE362;

import java.util.concurrent.locks.ReentrantLock;

public class RACECOND_BAD {
    private ReentrantLock mutex = new ReentrantLock();
    public void main(String[] args) {
        this.runThread();
    }
    public void runThread(){
        try {
            mutex.lock();
        } finally {
            mutex.unlock();
        }
    }
}
