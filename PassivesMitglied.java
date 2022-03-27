
/**
 * Beschreiben Sie hier die Klasse AktivesMitglied.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class PassivesMitglied extends Mitglied
{
    public PassivesMitglied(String nachName, String vorName, String anmeldedatum, String adresse){
        super(nachName, vorName, anmeldedatum, adresse);         
    }
    
    public boolean gibStatusAktiv(){
        return false;    
    }
}
