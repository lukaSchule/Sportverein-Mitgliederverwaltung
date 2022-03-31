/**
 * 
 */
public class Gateway
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private DatabaseConnector db;

    /**
     * Konstruktor für Objekte der Klasse Gateway
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
     * @param Anmeldedatum
     * @param Typ
     * @param Position
     * @param Aufwandsentschaedigung
     * @param Sponsoring
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
        if(Typ.equalsIgnoreCase("Spieler")){
            db.executeStatement("INSERT INTO Spieler (Einsaetze, Tore, Position) VALUES ('"+0+"', "+0+", '"+Position);  
        }
        beende();
    }
    
    /**
     * Übergibt ein Statement
     * 
     * @param statement
     */
    public void executeStatement(String statement){
        verbinde();
        db.executeStatement(statement);    
        beende();
    }
    
    /**
     * Gibt die Summe der gesamten erhaltenen Gelder durch Sponsoring wieder
     */
    public int gibSummeSponsoring(){
        verbinde();
        db.executeStatement("SELECT SUM(Sponsoring) FROM Mitglieder");    
        QueryResult ergebnis = db.getCurrentQueryResult();
        int erg = Integer.parseInt(ergebnis.getData()[0][0]);
        beende();
        return erg;
    }
    
    /**
     * Gibt das die Summe des Preises für alle Auflaufprämien
     */
    public int gibSummeAuflaufpraemien(){
        verbinde();
        db.executeStatement("SELECT SUM(Einsaetze) FROM Spieler");       
        QueryResult ergebnis = db.getCurrentQueryResult();
        int einsaetze = Integer.parseInt(ergebnis.getData()[0][0]);
        beende();
        return einsaetze * 50;
        
        }
    
    /**
     * Gibt die SUmme aller Ausgaben also die Summe aller Aufwandsentschaedigungen und Auflaufprämien
     */
    public int gibSummeAusgaben(){
        verbinde();
        db.executeStatement("SELECT SUM(Aufwandsentschaedigung) FROM Mitglieder");   
        QueryResult ergebnis = db.getCurrentQueryResult();
        int aufwandsentschaedigung = Integer.parseInt(ergebnis.getData()[0][0]);
        int auflaufpraemien = gibSummeAuflaufpraemien();
        beende();
        return aufwandsentschaedigung + auflaufpraemien;
        
    }
    
    /**
     * schreibt die Auflaufprämien an die Spieler gut. Wusste nicht was mit der Aufgabe wirklich gemeint war haben das so interpretiert
     */
    public void auflaufpraemienGutschreiben(){
        verbinde();    
        db.executeStatement("SELECT Nachname, Vorname, Adresse, Anmeldedatum, Einsaetze FROM Spieler JOIN Mitglieder on Spieler.VereinsID = Mitglieder.VereinsID");
        QueryResult ergebnis = db.getCurrentQueryResult();
        while(true){
            int i = 0;
            try{
                new Spieler(ergebnis.getData()[i][0], ergebnis.getData()[i][1], ergebnis.getData()[i][2], ergebnis.getData()[i][3], Integer.parseInt(ergebnis.getData()[i][4]));    
            }catch(Exception e){
                break;        
            }
        }
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
     * Diese Methode erzeugt die Tabelle Mitglieder und Spieler, wenn diese nicht schon exisitiert.
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
