
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
    private static int wollenRein = 0;
    private static int wollenRaus = 0;
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
    
    public String Z_getstation(int factor){
        return fahrplan.get(factor);
    }
    public void Z_einsteigen(int anzahl){
        int pSnap = passagiere;
        passagiere = passagiere + anzahl <= maxPassagiere ? passagiere + anzahl : maxPassagiere;
        if(passagiere == pSnap){
            System.out.println(String.format("maximum passagiere erreicht! Capped at %s. %d passagers had to stay outside",maxPassagiere,passagiere+anzahl-maxPassagiere));
        }
        else{
            System.out.println(anzahl + " passagiere sind eingestiegen!");
        }
    }
    public void türenÖffnen(){
        if(fährt){
            System.out.println("FATAL: Bus fährt gerade!");
            return;
        }
        System.out.println("Türen öffnen sich!");
        int psnap2 = passagiere;
        if(wollenRaus >0){
            System.out.println(wollenRaus + " Passagiere sind ausgestiegen");
            wollenRaus = 0;
            passagiere = passagiere - wollenRaus;
        }
        else{
            System.out.println("Niemand ist ausgestiegen");
        }
        if(wollenRein > 0){
            Z_einsteigen(wollenRein);
        }
        else{
            System.out.println("keine Passagiere sind eingestiegen.");
        }
    }
    public void losFahren(){
        if(türAuf){
            System.out.println("FATAL: Türen offen!");
            return;
        }
        if(fährt){
            System.out.println("Bus fährt schon!");
            return;
        }
        else{
            fährt = true;
            geschwindigkeit = 10;
            aktiverGang = 1;
            if(wollenRein > 0){
                System.out.println(String.format("WARNUNG: %s Passagiere wurden nicht mitgenommen!!!",wollenRein));
            }
            if(wollenRaus > 0){
                System.out.println("WARNUNG: %s Passagiere wollten aussteigen. Die Türen wurden aber nicht geöffnet!!!");
            }
            wollenRaus = 0;
            wollenRein = 0;
            System.out.println(String.format("Bus fährt im %sten Gang mit %skm/h nach %s los!",aktiverGang,geschwindigkeit,Z_getstation(station)));
        }
        int time = random.nextInt(3,300 / geschwindigkeit);
        System.out.println(String.format("Ankunft mit %skm/h in %s sekunden!",geschwindigkeit,time));
        for(int i = 0;i <= time; i++){
            try {
            TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            }
            System.out.println(time-i);
        }
        wollenRein = random.nextInt(10);
        wollenRaus = random.nextInt(passagiere / 2);
        fährt = false;
        geschwindigkeit = 0;
        aktiverGang = 0;
        System.out.println(String.format("angekommen bei %s. %s Passagiere wollen einsteigen. %s Passagiere wollen raus. Türen sind Geschlossen!",Z_getstation(station),wollenRein,wollenRaus));
    }
    public void status(){
        String drivestatus = fährt ? String.format("bus fährt mit %skm/h",geschwindigkeit) : "Bus steht.";
        System.out.println(drivestatus);
        System.out.println(String.format("Passagiere: %s von %d",passagiere,maxPassagiere));
        String CTA = türAuf ? "Türen sind offen." : "Türen sind geschlossen";
        System.out.println(CTA);
        System.out.println("Tankstand:" + tank + "%");
        System.out.println(String.format("gang: %s von %d",aktiverGang,gänge));
        System.out.println(String.format("nächste station: %s (%s von %s)",Z_getstation(station),station,fplen));
        System.out.println("fahrplan:");
        System.out.println(fahrplan);
    }
}
