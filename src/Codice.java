
import java.util.*;


public class Codice {

	public static void main(String[] args) {	
	
	Scanner s=new Scanner(System.in);
	int year = Calendar.getInstance().get(Calendar.YEAR);
	System.out.print("Benvenuto, questo programma ti consente di calcolare il tuo codice fiscale o quello di persone attualmente vive \n"
			+ "cioe' che non possono avere piu' di "+(year-1900) +" anni e che son nate dopo il "+(year+1)+", compreso ovviamente. \n");

	
	Genera Mario = new Genera("Mario","Rossi","Brescia","Aprile","1978",21,"m");
	System.out.println("Il codice fiscale di Mario Rossi, nato a Brescia il 21 aprile 1978, e' il seguente: "+Mario.getCodiceFiscale());
	System.out.println("");
	System.out.println("Ora invece puoi inserire i tuoi dati per calcolare il tuo o quello di altre persone");
	int t;
	do {
		
	System.out.println("Ok, quindi inserisci i dati, facendo attenzione agli errori di battitura (se sbagli puoi comunque rifare)");
	
	Genera Persona = new Genera("a","a","a","a","a",1,"a");
	Persona.chiediDati();
	
	System.out.println("Il codice fiscale dei dati inseriti e' il seguente: "+Persona.getCodiceFiscale());
	
	System.out.println("se hai finito premi 0, se vuoi continuare premi 1");
	
	do {
		t=s.nextInt();
	}while(t<0 && t>1);

	}while(t==1);
}
}


