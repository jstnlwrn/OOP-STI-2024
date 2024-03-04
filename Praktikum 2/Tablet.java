import java.lang.System;

public class Tablet extends StoreItem {
    private int screenSize;
    private boolean hasCellular;

    /**
     * Tablet constructor
     * @param brand
     * @param model
     * @param screenSize
     * @param hasCellular
     */
    public Tablet(String brand, String model, int screenSize, boolean hasCellular) {
        super(brand, model);
        this.screenSize = screenSize;
        this.hasCellular = hasCellular;
    }
    
    /**
     * Mencetak tambahan detail sebuah barang dengan format seperti berikut (tanpa [])
     * Cetak brand dan model dengan metode displayDetails dari class StoreItem
     * Screen size: [Ukuran layar dengan satuan inches]
     * Has cellular: [Apabila hasCellular, maka cetak Yes, selain itu No]
     */
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.printf("Screen size: %d inches\n", this.screenSize);
        if(this.hasCellular){
            System.out.printf("Has cellular: %s\n", "Yes");
        }
        else System.out.printf("Has cellular: %s\n", "No");;        
    }

    /**
     * Menghitung harga dari tablet
     * Apabila nama brand "Legowo", maka rumus: 300 + (Ukuran layar * 5) + (Jika has cellular, tambahkan 50)
     * Selain nama brand "Legowo", rumus: 300 + (Ukuran layar * 15) + (Jika has cellular, tambahkan 150)
     */
    @Override
    public int calculatePrice() {
        int total;
        if(super.getBrand() == "Legowo"){
            total = 300 + (this.screenSize * 5);
            if(this.hasCellular)total += 50;
        }
        else{
            total = 300 + (this.screenSize * 15);
            if(this.hasCellular)total += 150;            
        }
        return total;
    }
}
