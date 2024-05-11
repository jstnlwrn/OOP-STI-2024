import java.util.Arrays;
import java.lang.System;

// Lengkapi definisi class
public class RedAstronaut extends Player implements Impostor {
    // Atribut skill bertipe data String
    private String skill;
    // Atribut DEFAULT_SKILL bertipe data String dengan nilai "experienced"
    public static final String DEFAULT_SKILL = "experienced";

    public RedAstronaut(String name) {
        super(name, DEFAULT_SUS_LEVEL);
        this.skill = DEFAULT_SKILL;
    }

    public RedAstronaut(String name, int susLevel, String skill) {
        // Panggil constructor dari superclass dengan parameter name dan susLevel
        // Isi variabel skill dengan parameter skill yang sudah diubah menjadi lowercase
        super(name, susLevel);
        this.skill = skill.toLowerCase();
    }

    /*
        * Player yang frozen tidak bisa melakukan emergency meeting.
        * Mengadakan meeting dan memilih untuk mengeluarkan (freeze) Player yang paling mencurigakan, hanya mempertimbangkan Player yang tidak frozen
            * Player dengan susLevel tertinggi (yang BUKAN Impostor yang saat ini yang memanggil pertemuan) akan dituduh sebagai Impostor dan akan dikeluarkan
            * Jika dua pemain memiliki susLevel tertinggi yang sama, tidak ada pemain yang akan dikeluarkan.
            * **Hint**: Tidak perlu mengiterasi seluruh array.
        * Pastikan untuk mengubah variabel frozen dari pemain menjadi true saat mengeluarkan pemain (jangan memanggil metode "freeze"!)
        * Di akhir pemungutan suara, periksa apakah permainan telah berakhir menggunakan metode yang disediakan di Player.java
        * Tidak mengembalikan apapun
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
        * Mengimplementasikan metode yang disediakan di antarmuka Impostor.
        * Tidak mungkin untuk melakukan freeze Impostor lain, dan Impostor yang frozen tidak dapat mencoba untuk melakukan freeze. Jika Player yang diberikan Impostor, metode harus berakhir. Melakukan freeze pada Player yang sudah beku juga tidak berpengaruh apa-apa.
        * Freeze berhasil jika susLevel RedAstronaut lebih rendah dari Player tersebut
        * Jika freeze tidak berhasil, susLevel RedAstronaut berlipat ganda (kalikan susLevel saat ini dengan 2)
        * Ingat untuk mengubah nilai boolean frozen untuk Crewmate sesuai kebutuhan.
        * Setelah percobaan freeze, periksa apakah permainan telah berakhir menggunakan metode yang disediakan di Player.java
        * Tidak mengembalikan apapun
    */
    public void freeze(Player p) {
        if (p instanceof Impostor || this.isFrozen() || p.isFrozen()) return;
        else{
            if (this.getSusLevel() < p.getSusLevel()) {
                p.setFrozen(true);
            } else {
                this.setSusLevel(this.getSusLevel() * 2);
            }
        }
        super.gameOver();
    }

    /*
        * Tidak mungkin menyabotase Impostor lain, dan Impostor yang frozen tidak dapat menyabotase. Menyabotase Player yang frozen tidak akan berpengaruh apa-apa.
        * Jika susLevel Impostor di bawah 20, mereka meningkatkan susLevel Crewmate sebesar 50%
        * Jika tidak, mereka hanya bisa meningkatkan susLevel Crewmate sebesar 25%
        * (Catatan: Dalam kedua kasus, susLevel Crewmate dibulatkan ke bawah ke nilai int terdekat)
        * Tidak mengembalikan apapun
    */
    public void sabotage(Player p) {
        if (p instanceof Impostor || this.isFrozen() || p.isFrozen()) return;
        else{
            if (this.getSusLevel() < 20) {
                p.setSusLevel(p.getSusLevel() + (int) Math.round(p.getSusLevel() * 0.5));
            } else {
                p.setSusLevel(p.getSusLevel() + (int) Math.round(p.getSusLevel() * 0.25));
            }
        }
    }

    /*
        * Dua Red sama jika mereka keduanya memiliki nama, frozen, susLevel, dan skill yang sama
        * Mengembalikan sebuah boolean
    */
    public boolean equals(Object o) {
        if (o instanceof RedAstronaut && super.equals(o)){
            RedAstronaut red = (RedAstronaut) o;
            return this.skill.equals(red.skill);
        } else {
            return false;
        }
    }

    /*
        Mengembalikan String yang menggambarkan RedAstronaut sebagai berikut:
        * `"My name is [name], and I have a `susLevel` of [susLevel]. I am currently (frozen / not frozen). I am an [skill] player!"`
        * Jika susLevel lebih besar dari 15, kembalikan keluaran dalam huruf kapital semua. (Hint: toUppercase)
        * (Note: gantikan nilai dalam tanda kurung [] dengan nilai sebenarnya)
        * Anda harus menggunakan metode toString() dari kelas Player.
    */
    public String toString() {
        String temp = super.toString() + " I am an " + this.skill + " player!";
        if (this.getSusLevel() > 15) {
            temp = temp.toUpperCase();
        }
        return temp;
    }
}
