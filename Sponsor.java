
public class Sponsor extends PassivesMitglied{
    private double monatlichesSponsoring;
    
    public Sponsor(String nachName, String vorName, String anmeldedatum, String adresse, double monatlichesSponsoring){
        super(nachName, vorName, anmeldedatum, adresse);    
        this.monatlichesSponsoring = monatlichesSponsoring;
    }
    
    public double gibMonatlichesSponsoring(){
        return monatlichesSponsoring;    
    }
    
    public void setMonatlichesSponsoring(double monatlichesSponsoring){
        this.monatlichesSponsoring = monatlichesSponsoring;    
    }
}
