
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
    /**
     * fügt Mitglied hinzu. Position muss auch angegeben werden, wenn das Mitglied kein Spieler ist, dann einfach leerstelle angeben.
     */
    public void fuegeMitgliedHinzu(String Nachname, String Vorname, String Adresse, String Anmeldedatum, String Typ, String Position, int Aufwandsentschaedigung, int Sponsoring){
        gate.hinzufuegen(Nachname, Vorname, Adresse, Anmeldedatum, Typ, Position, Aufwandsentschaedigung, Sponsoring); 
    }
    
    /**
     * löscht mitglied mit der übergebenen id
     * 
     * @param loescheMitglied
     */
    public void loescheMitglied(int id){
        gate.loesche(id);       
    }
    
    /**
     * gibt das gesamte Sponsoring zurück
     * 
     * @return int
     */
    public int gibSponsoring(){
        return gate.gibSummeSponsoring();        
    }
    
    /**
     * gibt true zurück, wenn die Ausgaben größer als das Sponsoring sind, andernfalls false.
     * 
     * @return boolean
     */
    public boolean vergleicheSponsorgeldMitAusgaben(){
        int sponsoring = gate.gibSummeSponsoring();
        int ausgaben = gate.gibSummeAusgaben();
        if(ausgaben > sponsoring){
            return true;       
        }
        else
            return false;
    }
    
    /**
     * schreibt den Spielern die Auflaufprämien gut. Wusste immernoch nicht, wie die Aufgabe gemeint war.
     */
    public void auflaufpraemieGutschreiben(){
        gate.auflaufpraemienGutschreiben();    
    }
}
