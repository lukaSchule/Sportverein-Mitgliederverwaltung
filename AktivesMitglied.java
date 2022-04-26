
/**
 * Beschreiben Sie hier die Klasse AktivesMitglied.
 * 
 * @author (Luka) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class AktivesMitglied extends Mitglied
{
    private double aufwandsentschaedigung;
    public AktivesMitglied(String nachName, String vorName, String anmeldedatum, String adresse, double aufwandsentschaedigung){
        super(nachName, vorName, anmeldedatum, adresse);      
        this.aufwandsentschaedigung = aufwandsentschaedigung;
    }
    
    public boolean gibStatusAktiv(){
        return true;    
    }
    
    public double gibAufwandsentschaedigung(){
        return aufwandsentschaedigung;    
    }
    
    public void setAufwandsentschaedigung(double aufwandsentschaedigung){
        this.aufwandsentschaedigung = aufwandsentschaedigung;    
    }
}
