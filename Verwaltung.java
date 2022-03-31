
/**
 * Beschreiben Sie hier die Klasse Verwaltung.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Verwaltung
{
    Gateway gate;
    public Verwaltung(){
        gate = new Gateway();        
    }
    
    public void fuegeMitgliedHinzu(String Nachname, String Vorname, String Adresse, String Anmeldedatum, String Typ, String Position, int Aufwandsentschaedigung, int Sponsoring){
        gate.hinzufuegen(Nachname, Vorname, Adresse, Anmeldedatum, Typ, Position, Aufwandsentschaedigung, Sponsoring); 
            }
    public void loescheMitglied(int id){
        gate.loesche(id);       
    }
    public void gibSponsoring(){
        gate.gibSummeSponsoring();        
    }
    public boolean vergleicheSponsorgeldMitAusgaben(){
        int sponsoring = gate.gibSummeSponsoring();
        int ausgaben = gate.gibSummeAusgaben();
        if(ausgaben > sponsoring){
            return true;       
        }
        else
            return false;
    }
}
