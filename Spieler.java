public class Spieler extends AktivesMitglied{
    private String position;
    private int tore;
    private int einsaetze;
    private int auflaufpreamie;
    
    public Spieler(String nachName, String vorName, String anmeldedatum, String adresse, double aufwandsentschaedigung){
        super(nachName, vorName, anmeldedatum, adresse, aufwandsentschaedigung);    
    }
    
    public String gibPosition(){
        return position;    
    }
    
    public int gibTore(){
        return tore;    
    }
    
    public int gibEinsaetze(){
        return einsaetze;    
    }
    
    public void setPosition(String position){
        this.position = position;       
    }
    
    public void setTore(int tore){
        this.tore = tore;    
    }
    
    public void setEinsaetze(int einsaetze){
        this.einsaetze = einsaetze;       
    }
    
    public void setAuflaufpraemie(int auflaufpraemie){
        this.auflaufpreamie = auflaufpreamie;    
    } 
}