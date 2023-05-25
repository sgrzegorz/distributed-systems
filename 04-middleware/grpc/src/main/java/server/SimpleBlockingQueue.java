package server;


import gen.Weather.Data;

class SimpleBlockingQueue {
    final Data[] items = new Data[100];
    int putptr, takeptr, count;

    public synchronized void put(Data x) throws InterruptedException {
        while (count == items.length)
            wait();
        items[putptr] = x;
        if (++putptr == items.length) putptr = 0;
        ++count;
        notifyAll();
    }

    public synchronized Data take() throws InterruptedException {
        while (count == 0)
            wait();
        Data x = items[takeptr];
        if (++takeptr == items.length) takeptr = 0;
        --count;
        notifyAll();
        return x;
    }
}
