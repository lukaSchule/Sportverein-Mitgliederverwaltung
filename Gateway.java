/**
 * 
 */
public class Gateway
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private DatabaseConnector db;

    /**
     * Konstruktor für Objekte der Klasse HighscoreGateway
     */
    public Gateway()
    {
        // Instanzvariable initialisieren
        db = null;
    }

    /**
     * Diese Methode setzt die CREATE-Funktion um, indem hier ein neues Mitglied in die Datenbank Mitglieder eingetragen wird.
     * Der Status wird dabei automatisch ermittelt, jenachdem was für ein Typ übergeben wird.
     * 
     * @param Nachname 
     * @param Vorname
     * @param Adresse
     * @param Typ Dies ist der Typ des Mitglieds, also Spieler, Trainer, Fan, Sponsor oder Koordinator
     */
    public void hinzufuegen(String Nachname, String Vorname, String Adresse, String Anmeldedatum, String Typ, String Position, int Aufwandsentschaedigung, int Sponsoring)
    {
        String status;
        if(Typ.equalsIgnoreCase("Spieler") || Typ.equalsIgnoreCase("Trainer") || Typ.equalsIgnoreCase("Koordinater")){
            status = "Aktiv";    
        }else{
            status = "Passiv";    
        }
        
        if(!Typ.equalsIgnoreCase("Sponsor")){
            Sponsoring = 0;    
        }
        
        if(status == "Passiv"){
            Aufwandsentschaedigung = 0;    
        }
        
        verbinde();
        db.executeStatement("INSERT INTO Mitglieder (Nachname, Vorname, Adresse, Anmeldedatum, Typ, Status, Aufwandsentschaedugung, Sponsoring) VALUES ('"+Nachname+"', '"+Vorname+"', '"+Adresse+"', '"+Anmeldedatum+"', '"+Typ+"', '"+status+"', "+Aufwandsentschaedigung+", "+Sponsoring+")");
        beende();
    }
    
    public void executeStatement(String statement){
        db.executeStatement(statement);    
    }
    
    public int gibSummeSponsoring(){
        db.executeStatement("SELECT SUM(Sponsoring) FROM Mitglieder");    
        QueryResult ergebnis = db.getCurrentQueryResult();
        int erg = Integer.parseInt(ergebnis.getData()[0][0]);
        return erg;
    }
    
    public int gibAuflaufpraemien(){
        db.executeStatement("SELECT SUM(Einsaetze) FROM Spieler");       
        QueryResult ergebnis = db.getCurrentQueryResult();
        int einsaetze = Integer.parseInt(ergebnis.getData()[0][0]);
        return einsaetze * 50;
    }
    
    public int gibSummeAusgaben(){
        db.executeStatement("SELECT SUM(Aufwandsentschaedigung) FROM Mitglieder");   
        QueryResult ergebnis = db.getCurrentQueryResult();
        int aufwandsentschaedigung = Integer.parseInt(ergebnis.getData()[0][0]);
        int auflaufpraemien = gibAuflaufpraemien();
        return aufwandsentschaedigung + auflaufpraemien;
    }
    
    /**
     * Diese Methode setzt die DELETE-Funktion um, indem hier Datensätze über die Angabe der id gelöscht werden können.
     * 
     * @param id
     */
    public void loesche(int id)
    {
        verbinde();
        db.executeStatement("DELETE FROM Mitglieder WHERE id ="+id);
        try{
            db.executeStatement("DELETE FROM Spieler WHERE id ="+id);   
        }catch(Exception e){
            
        }
        beende();
    }
    
    /**
     * Diese Methode setzt die UPDATE-Funktion um, indem hier Datensätze über die Angabe der id und der zu aktualisierenden Attributwerte aktualisiert werden können.
     * 
     * @param id
     * @param name
     * @param punkte
     */
    public void aktualisiere(int id, String name, int punkte)
    {
        verbinde();
        db.executeStatement("UPDATE highscore SET name = '"+name+"', punkte = "+punkte+" WHERE id = "+id);
        beende();
    }
    
    /**
     * Diese Methode erzeugt die Tabelle Mitglieder, wenn diese nicht schon exisitiert.
     */
    public void erzeugeTabelle()
    {
         verbinde();
         db.executeStatement("Create table if not exists Mitglieder(VereinsID SERIAL PRIMARY_KEY, Nachname VARCHAR(40), Vorname VARCHAR(40), Adresse VARCHAR(100), Anmeldedatum Varchar(25), Typ VARCHAR(20), Status BOOLEAN, Aufwandsentschaedigung int, Sponsoring int)");
         db.executeStatement("Create table if not exists Spieler(VereinsID int PRIMARY_KEY REFERENCES Mitglieder(VereinsID), Einsaetze INT, Tore INT, Position Varchar(255)");
         beende();
    }
    
    /**
     * Diese Methode stellt eine Verbindung zur Datenbank her.
     */
    private void verbinde()
    {
        if(db == null)
        {
            db = new DatabaseConnector("",0,"Mitglieder","","");
        }
    }
    
    /**
     * Diese Methode beendet die Verbindung zur Datenbank.
     */
    private void beende()
    {
        if(db != null)
        {
            db.close();
            db = null;
        }
    }
}
