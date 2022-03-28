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
     * Diese Methode setzt die CREATE-Funktion um, indem hier neue Highscores in die Datenbank eingetragen werden.
     * 
     * @param name
     * @param punkte
     */
    public void hinzufuegen(String name, int punkte)
    {
        verbinde();
        db.executeStatement("INSERT INTO highscore (name, punkte) VALUES ('"+name+"', "+punkte+")");
        beende();
    }
    
    /**
     * Diese Methode setzt die DELETE-Funktion um, indem hier Datensätze über die Angabe der id gelöscht werden können.
     * 
     * @param id
     */
    public void loesche(int id)
    {
        verbinde();
        db.executeStatement("DELETE FROM highscore WHERE id ="+id);
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
     * Diese Methode erzeugt die Tabelle highscore, wenn diese nicht schon exisitiert.
     */
    public void erzeugeTabelle()
    {
         verbinde();
         db.executeStatement("Create table if not exists highscore (id SERIAL PRIMARY_KEY, name Varchar(255), punkte int)");
         beende();
    }
    
    /**
     * Diese Methode stellt eine Verbindung zur Datenbank her.
     */
    private void verbinde()
    {
        if(db == null)
        {
            db = new DatabaseConnector("",0,"spielstand","","");
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
