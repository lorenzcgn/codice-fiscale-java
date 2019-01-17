package codice;
import java.util.*;
import java.io.*;
 
class Genera {
  private String anno;
  private String nome;
  private String cognome;
  private String comune;
  private String m;
  private String sesso;
  private String giorno;
 
  Scanner s=new Scanner(System.in);

  
  Genera(String nome, String cognome, String comune, String m, String anno, String giorno,String sesso) {
    this.nome=nome;
    this.cognome=cognome;
    this.comune=comune;
    this.m=m;
    this.anno=anno;
    this.giorno=giorno;
    this.sesso=sesso;
  }
  
  public void chiediDati() {
	  System.out.println("Inserisci il tuo nome: ");
	  do{
		  this.nome=s.next();
	  }while(this.nome.length()<2);
	  System.out.println("Inserisci il tuo cognome: ");
	  do {
	  this.cognome=s.next();
	  }while(this.cognome.length()<2);
	  System.out.println("Inserisci il tuo comune: ");
	  do {
	  this.comune=s.next();
      }while(this.comune.length()<2);
	  System.out.println("Inserisci il tuo mese di nascita: ");
	  this.m=s.next();
	  
	  do{   
		  System.out.println("Inserisci il tuo anno di nascita: ");
		  this.anno=s.next();
	  
	  if(Integer.parseInt(this.anno) < 1900)
          System.out.println("Inserire un anno di nascita reale, quindi a partire da 1900");
  	  
  	  if(Integer.parseInt(this.anno) > Calendar.getInstance().get(Calendar.YEAR))
            System.out.println("Inserire un anno di nascita reale, quindi non superiore all'anno corrente");
    		}while(Integer.parseInt(this.anno) < 1900 || Integer.parseInt(this.anno) > Calendar.getInstance().get(Calendar.YEAR));
	   
	  System.out.println("Inserisci il tuo giorno di nascita: ");
	  this.giorno=s.next();
          int giorno1 = Integer.parseInt(giorno); 
	  while(giorno1<1 || giorno1>31) {
		  try {
			  System.out.print("Input non valido. Reiserisci il giorno: ");
			  this.giorno=s.next();
		  } catch(InputMismatchException e) {
		    System.out.print("Input non valido. Reiserisci il giorno: ");
		  }
		}

          if(giorno1 > 10)
              this.giorno = Integer.toString(giorno1);
          
          else this.giorno = Integer.toString('0'+giorno1);
         

	  
	  System.out.println("Inserisci il tuo sesso (M/F - m/f): ");
	  String so=s.next();
	  while(!so.equals("M") && !so.equals("F") && !so.equals("m") && !so.equals("f")) {
		  try {
			  System.out.print("Input non valido. Scrivi m o f: ");
			  so=s.next();
		  } catch(InputMismatchException e) {
		    System.out.print("Input non valido. Scrivi m o f: ");
		  }
		}
	  this.sesso=so.toUpperCase();
          
          if (sesso.equals("F")) {
              giorno1= giorno1+40;
              this.giorno = Integer.toString(giorno1);
          }

  }
  
  String getNome() {
    return modificaNC(nome,true);
  }
  String getCognome() {
    return modificaNC(cognome,false);
  }
 
  String getNomeInserito() {
    return nome;
  }
  String getCognomeInserito() {
    return cognome;
  }
  String getMese() {
    return modificaMese();
  }
  String getMeseInserito() {
    return m;
  }
  String getAnno() {
    return (anno.substring(2,4));
  }
  String getGiorno() {
	  return giorno;
  }
  String getGiornoInserito() {
    return giorno;
  }
  String getComune() {
    return elaboraCodiceComune();
  }
  String getCodice() {
    return calcolaCodice();
  }
  String getCodiceFiscale() {
    return toString();
  }
 
  private String modificaNC(String stringa, boolean cod) {
    String nuovastringa = "";
    stringa = stringa.replaceAll(" ", "");        
    stringa = stringa.toLowerCase();
   
    String consonanti = getConsonanti(stringa);    
    String vocali = getVocali(stringa);
   
 
    if(consonanti.length() == 3) {                   
      nuovastringa = consonanti;
    }
                                                    
    else if((consonanti.length() < 3) && (stringa.length() >= 3)) {
      nuovastringa = consonanti;
      nuovastringa = aggiungiVocali(nuovastringa, vocali);
    }
                                                    
    else if((consonanti.length() < 3) && (stringa.length() < 3)) {
      nuovastringa = consonanti;
      nuovastringa += vocali;
      nuovastringa = aggiungiX(nuovastringa);
    }
                                                    
    else if(consonanti.length() > 3) {
      if(cod==false) nuovastringa = consonanti.substring(0,3);
      else nuovastringa = consonanti.charAt(0) +""+ consonanti.charAt(2) +""+ consonanti.charAt(3);
    }
   
    return nuovastringa;
  }
  
   
  private String aggiungiX(String stringa) {
    while(stringa.length() < 3) {
      stringa =stringa + "x";
    }
    return stringa;
  }
   
  
  private String aggiungiVocali(String stringa, String vocali) {
    int j = 0;
    while(stringa.length() < 3) {
      stringa = stringa+vocali.charAt(j);
      j++;
    }
    return stringa;
  }
  
  private String getVocali(String stringa) {
    stringa = stringa.replaceAll("[^aeiou]", "");
    return stringa;
  }

  private String getConsonanti(String stringa) {
    stringa = stringa.replaceAll("[aeiou]","");
    return stringa;
  }

  private String modificaMese() {
    for(int i=0; i<mese.length; i++) {
      if(mese[i][0].equalsIgnoreCase(m)) return mese[i][1];
    }
    return null;
  }
  
  private String elaboraCodiceComune() {
  String cc="";
    try {
      Scanner scanner = new Scanner(new File("Comuni.txt"));
      scanner.useDelimiter("\r\n");
     
      while(scanner.hasNext()) {
        String s1 = scanner.nextLine();
        String s2 = s1.substring(0,s1.indexOf('-')-1);
        if(s2.equalsIgnoreCase(comune)) {
          cc = s1.substring(s1.lastIndexOf(' ')+1);
        }
      }
     
      scanner.close();
    } catch(Exception e) {e.printStackTrace();}
    return cc;
  }
  
  private String calcolaCodice() {
    String str = getCognome().toUpperCase()+getNome().toUpperCase()+getAnno()+getMese()+getGiorno()+getComune();
    int pari=0,dispari=0;
   
    for(int i=0; i<str.length(); i++) {
      char ch = str.charAt(i);       
      if((i+1) % 2 == 0) {
        int index = Arrays.binarySearch(elencoPari,ch);
        pari += (index >= 10) ? index-10 : index;
      } else {
        int index = Arrays.binarySearch(elencoPari,ch);
        dispari += elencoDispari[index];
      }
    }
   
    int controllo = (pari+dispari) % 26;
    controllo += 10;
   
    return elencoPari[controllo]+"";
  }
  
  public String toString() {
    return getCognome().toUpperCase()+getNome().toUpperCase()+getAnno()+getMese()+getGiorno()+getComune()+getCodice().toUpperCase();
  }
 
  
  private final char[] elencoPari = {'0','1','2','3','4','5','6','7','8','9','A','B',
          'C','D','E','F','G','H','I','J','K','L','M','N',
          'O','P','Q','R','S','T','U','V','W','X','Y','Z'
      };
         
  private final int[] elencoDispari= {1, 0, 5, 7, 9, 13, 15, 17, 19, 21, 1, 0, 5, 7, 9, 13,
      15, 17, 19, 21, 2, 4, 18, 20, 11, 3, 6, 8, 12, 14, 16,
      10, 22, 25, 24, 23
     };

  private final String[][] mese = { {"Gennaio","A"},
          {"Febbraio","B"},
          {"Marzo","C"},
          {"Aprile","D"},
          {"Maggio","E"},
          {"Giugno","H"},
          {"Luglio","L"},
          {"Agosto","M"},
          {"Settembre","P"},
          {"Ottobre","R"},
          {"Novembre","S"},
          {"Dicembre","T"}
        };
}
