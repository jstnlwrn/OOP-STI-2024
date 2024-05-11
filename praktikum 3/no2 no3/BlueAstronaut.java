import java.util.Arrays;
import java.lang.System;

public class BlueAstronaut extends Player implements Crewmate {
    // Atribut jumlah task yang harus diselesaikan (integer)
    private int numTasks;
    // Atribut kecepatan menyelesaikan task (integer, nonzero positive)
    private int taskSpeed;
    // Atribut untuk mengecek apakah task sudah selesai pertama kali
    private boolean firstTime = true;
    // Atribut DEFAULT_NUM_TASKS bertipe data int dengan nilai 6
    public static final int DEFAULT_NUM_TASKS = 6;
    // Atribut DEFAULT_TASK_SPEED bertipe data int dengan nilai 10
    public static final int DEFAULT_TASK_SPEED = 10;

    public BlueAstronaut(String name) {
        super(name, DEFAULT_SUS_LEVEL);
        this.numTasks = DEFAULT_NUM_TASKS;
        this.taskSpeed = DEFAULT_TASK_SPEED;
    }

    public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed) {
        // Panggil constructor dari superclass dengan parameter name dan susLevel
        // Isi variabel numTasks dan taskSpeed dengan parameter yang sesuai
        super(name, susLevel);
        this.numTasks = numTasks;
        this.taskSpeed = taskSpeed;
    }

    /*

    */
    public void emergencyMeeting() {
        if (this.isFrozen()) return;
        Player maxSus1 = null;
        Player maxSus2 = null;
        Player[] playersT = super.getPlayers();
        for (Player p : playersT) {
            if (p.isFrozen() || this.equals(p)) continue;
            if (maxSus1 == null || p.compareTo(maxSus1) >= 0) {
                maxSus2 = maxSus1;
                maxSus1 = p;
            }
        }
        if (maxSus1 != null && (maxSus2 == null || maxSus1.compareTo(maxSus2)>0)) {
            maxSus1.setFrozen(true);
        }
        super.gameOver();
    }

    /*
        * BlueAstronaut yang frozen tidak dapat menyelesaikan tugas.
        * Jika taskSpeed lebih besar dari 20, kurangi numTasks sebanyak 2. Jika tidak, kurangi 1 dari numTasks.
            * Jika numTasks turun di bawah 0, tetapkan menjadi 0
        * Setelah BlueAstronaut selesai dengan tugas mereka, yang berarti numTasks sama dengan 0 (hanya untuk pertama kali),
            * Cetak `"I have completed all my tasks"`
            * Kemudian kurangi susLevel BlueAstronaut sebesar 50% (dibulatkan ke bawah)
        * Tidak mengembalikan apapun.
    */
    public void completeTask() {
        if (this.isFrozen()) return;
        if (this.taskSpeed > 20) {
            this.numTasks -= 2;
        } else {
            this.numTasks -= 1;
        }
        if (this.numTasks < 0) {
            this.numTasks = 0;
        }
        if (this.numTasks == 0 && firstTime) {
            System.out.println("I have completed all my tasks");
            this.setSusLevel(this.getSusLevel() / 2);
            firstTime = false;
        }
    }

    /*
        * Dua BlueAstronaut sama jika mereka keduanya memiliki enama yang sama, frozn, susLevel, numTasks, dan taskSpeed
        * Mengembalikan sebuah boolean.
    */
    public boolean equals(Object o) {
        if (o instanceof BlueAstronaut && super.equals(o)){
            BlueAstronaut blue = (BlueAstronaut) o;
            return this.numTasks == blue.numTasks && this.taskSpeed == blue.taskSpeed;
        } else {
            return false;
        }
    }

    /*
        Mengembalikan String yang menggambarkan BlueAstronaut sebagai berikut:
        * `"My name is [name], and I have a `susLevel` of [susLevel]. I am currently (frozen / not frozen). I have [numTasks] left over."`
            * Jika susLevel lebih besar dari 15, kembalikan keluaran dalam huruf kapital semua. (Hint: toUppercase)
            * (Note: gantikan nilai dalam tanda kurung [] dengan nilai sebenarnya)
        * Anda harus menggunakan metode toString() dari kelas Player.
    */
    public String toString() {
        String temp = super.toString() + " I have " + this.numTasks + " left over.";
        if (this.getSusLevel() > 15) {
            temp = temp.toUpperCase();
        }
        return temp;
    }
}
