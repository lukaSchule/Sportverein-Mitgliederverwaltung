/**
 * Abstrakte Klasse Mitglied - beschreiben Sie hier die Klasse
 * 
 * @author (Ihr Name)
 * @version (eine Version-Nummer oder ein Datum)
 */
public abstract class Mitglied
{
    String nachName;
    String vorName;
    String anmeldedatum;
    String adresse;
    
    public Mitglied(String nachName, String vorName, String anmeldedatum, String adresse){
        this.nachName = nachName;
        this.vorName = vorName;
        this.anmeldedatum = anmeldedatum;
        this.adresse = adresse;
    }
    
    public String gibNachName(){
        return nachName;    
    }
    public String gibVorName(){
        return vorName;    
    }
    public String gibAnmeldedatum(){
        return anmeldedatum;    
    }
    public String gibAdresse(){
        return adresse;    
    }
    
    
}
