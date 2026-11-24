
/**
 * Beschreiben Sie hier die Klasse bus.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;   
import java.util.Random;


//import java.util.Scanner;

//Scanner sc = new Scanner(System.in);
//String input = sc.nextLine();  // blocks until you hit "Send" in BlueJ
//int value = Integer.parseInt(input.trim());   

public class bus
{
    private static int passagiere = 0;
    private static int maxPassagiere = 30;
    private static boolean fährt = false;
    private static boolean türAuf = false;
    private static int geschwindigkeit = 0;
    private static boolean vorwärts = false;
    private static int tank = 100;
    private static int gänge = 5;
    private static int aktiverGang = 0;
    Random random = new Random();
    private static ArrayList<String> fahrplan = new ArrayList<String>();
    static{
        fahrplan.add("start station");
        fahrplan.add("zündorf");
        fahrplan.add("langel");
        fahrplan.add("porz");
        fahrplan.add("köln");
    }
    private static int station = 1;
    private static int fplen = fahrplan.size();
    
    public String getstation(int factor){
        return fahrplan.get(factor);
    }
    public void einsteigen(int anzahl){
        int pSnap = passagiere;
        passagiere = passagiere + anzahl <= maxPassagiere ? passagiere + anzahl : maxPassagiere;
        if(passagiere == pSnap){
            System.out.println(String.format("maximum passagiere erreicht! Capped at %s. %d passagers had to stay outside",maxPassagiere,passagiere+anzahl-maxPassagiere));
        }
        else{
            System.out.println(anzahl + " passagiere sind eingestiegen!");
        }
    }
    public void losFahren(){
        if(türAuf){
            System.out.println("FATAL: Türen offen!");
        }
        if(fährt){
            System.out.println("Bus fährt schon!");
        }
        else{
            fährt = true;
            geschwindigkeit = 10;
            aktiverGang = 1;
            System.out.println(String.format("Bus fährt im %sten Gang mit %skm/h nach %s los!",aktiverGang,geschwindigkeit,getstation(station)));
        }
        int time = random.nextInt(300 / geschwindigkeit);
        System.out.println(String.format("Ankunft mit %skm/h in %s sekunden!",geschwindigkeit,time));
        for(int i = 0;i <= time; i++){
            try {
            TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            }
            System.out.println(time-i);
        }
        System.out.println(String.format("angekommen bei %s. Passgiere wollen rein. Türen sind Geschlossen!",getstation(station)));
    }
    public void status(){
        String drivestatus = fährt ? String.format("bus fährt mit %skm/h",geschwindigkeit) : "Bus steht.";
        System.out.println(drivestatus);
        System.out.println(String.format("Passagiere: %s von %d",passagiere,maxPassagiere));
        String CTA = türAuf ? "Türen sind offen." : "Türen sind geschlossen";
        System.out.println(CTA);
        System.out.println("Tankstand:" + tank + "%");
        System.out.println(String.format("gang: %s von %d",aktiverGang,gänge));
        System.out.println(String.format("nächste station: %s (%s von %s)",getstation(station),station,fplen));
        System.out.println("fahrplan:");
        System.out.println(fahrplan);
    }
}
