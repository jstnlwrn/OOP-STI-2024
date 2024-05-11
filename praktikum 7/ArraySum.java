/**
 * Jangan lupa tambahkan kata kunci yang dibutuhkan
 */ 

public class ArraySum {
    // nWorkers menyatakan jumlah maksimum threads yang tersedia
    private int nWorkers;
    // array untuk menampung array masukan
    private Integer[] arr;
    /**
     * Tambahkan atribut kelas yang dibutuhkan
     */ 
    private int sum = 0;  
    /**
     * Konstruktor
     * Inisialisasi atribut kelas
     */
    ArraySum(int nWorkers, Integer[] arr) {
        this.nWorkers = nWorkers;
        this.arr = arr;    
    }

    /**
     * Implementasi
     * method sum akan membuat sejumlah thread dan memetakan array masukan secara merata ke semua threads yang dapat dibuat
     */
    public int sum() throws InterruptedException {
        int length = arr.length;
        int range = length / nWorkers;
    
        Thread[] threads = new Thread[nWorkers];
        for (int i = 0; i < nWorkers; i++) {
            final int start = i * range;
            final int end = (i == nWorkers - 1) ? length : start + range;
    
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    int partialSum = partialSum(start, end);
                    synchronized (this) {
                        ArraySum.this.sum += partialSum;
                    }
                }
            });
    
            threads[i].start();
        }
    
        for (Thread thread : threads) {
            thread.join();
        }
    
        return sum;
    }

    /**
     * Implementasi
     * method partialSum akan melakukan penjumlahan elemen-elemen array pada index `start` sampai `end-1`
     */
    protected int partialSum(int start, int end) {
        int sum = 0;

        for (int i = start; i < end; i++) {
            sum += arr[i];
        }

        return sum;
    }
}
