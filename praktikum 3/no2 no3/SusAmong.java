import java.util.Scanner;
import java.lang.System;

public class SusAmong{
    private int taskCompleted = 0;
    private int playersFrozen = 0;
    private int emergencyMeetingCalled = 0;

    public void operation1(Player p){
        if (p instanceof BlueAstronaut){
            BlueAstronaut blue = (BlueAstronaut) p;
            blue.completeTask();
            this.taskCompleted++;
        }
    }

    public void operation2 (Player impostor, Player crew){
        if (impostor instanceof RedAstronaut){
            RedAstronaut red = (RedAstronaut) impostor;
            red.freeze(crew);
            this.playersFrozen++;
        }
    }

    public void operation3 (Player[] players){
        for (Player p : players){
            p.emergencyMeeting();
        }
        this.emergencyMeetingCalled++;
    }

    public void endThisShit(){
        System.out.println("Task completed: " + this.taskCompleted);
        System.out.println("Players frozen: " + this.playersFrozen);
        System.out.println("Emergency meetings called: " + this.emergencyMeetingCalled);
        System.out.println("Game Over. Thanks for playing!");
    }

    public static void main(String[] args){
        SusAmong game = new SusAmong();
        Scanner input = new Scanner(System.in);

        //input jumlah player
        int numPlayer = input.nextInt();
        
        //input jumlah impostor
        int numImpostor = input.nextInt();
        
        //create player
        for (int i = 0; i < numPlayer-numImpostor; i++){ //crewmate
            String name = "Crewmate " + (i+1);
            Player current = new BlueAstronaut(name);
        }
        for (int i = numPlayer-numImpostor; i < numPlayer; i++){ //impostor
            String name = "Crewmate " + (i+1);
            Player current = new RedAstronaut(name);
        }

        //start
        Player[] playerlist = current.getPlayers();
        while (!current.gameOver()){
            switch (input.nextInt()){
                case 1:
                    game.operation1(playerlist[input.nextInt()]);
                    break;
                case 2:
                    game.operation2(playerlist[input.nextInt()], playerlist[input.nextInt()]);
                    break;
                case 3:
                    game.operation3(playerlist);
                    break;
                case 4:
                    input.close();
                    return;
            }
        }
        game.endThisShit();
    }
}