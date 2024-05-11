/**
 * DelayedOutput.java 
 * mengeluarkan suatu string setelah delay sekian milidetik.
 * @author 18222006 Justin Lawrance
 */
public class DelayedOutput {
  public static void printDelayed(int delayMillisec, String output) {
      // TODO print output setelah di delay selama delayMillisec
      // PENTING: gunakan threading agar tidak blocking

    Thread thread = new Thread(new Runnable() {
    @Override
      public void run() {
        try{
            Thread.sleep(delayMillisec);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        System.out.println(output);
      }
    });
    thread.start();
  }
}