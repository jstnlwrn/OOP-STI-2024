import java.util.Scanner;

public class GameSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int player = Integer.parseInt(scanner.nextLine());
        String seeker = scanner.nextLine() ;

        HideNSeek game1 = new HideNSeek(seeker, player);
        System.out.printf("Game dimulai dengan %d pemain, seeker adalah %s\n", player, seeker);

        int count=0;
        int input;

        while (true){
            input = Integer.parseInt(scanner.nextLine());
            if (input == count + 1){
                game1.foundPlayer();
                count++;
                System.out.printf("%d Pemain ditemukan\n", count);
            }
            if (game1.getPlayerFound() == player-1){
                System.out.println("Semua pemain telah ditemukan, permainan selesai.");
                break;
            }
        }
        scanner.close();
    }
}
