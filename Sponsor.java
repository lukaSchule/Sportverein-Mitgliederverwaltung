
public class Sponsor extends PassivesMitglied{
    private double monatlichesSponsoring;
    
    public Sponsor(String nachName, String vorName, String anmeldedatum, String adresse){
        super(nachName, vorName, anmeldedatum, adresse);    
    }
    
    public double gibMonatlichesSponsoring(){
        return monatlichesSponsoring;    
    }
    
    public void setMonatlichesSponsoring(double monatlichesSponsoring){
        this.monatlichesSponsoring = monatlichesSponsoring;    
    }
}
