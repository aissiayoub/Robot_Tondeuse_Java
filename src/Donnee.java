

public class Donnee {
	
	Terrain t;
	Robot r;
	
	/**Distance pour que le robot parcourt 50% du terrain*/
	private String D50; 
	/**Temps pour que le robot parcourt 50% du terrain*/
	private String T50;
	/**Distance pour que le robot parcourt 75% du terrain*/
	private String D75;
	/**Temps pour que le robot parcourt 75% du terrain*/
	private String T75;
	/**Distance pour que le robot parcourt 90% du terrain*/
	private String D90; 
	/**Temps pour que le robot parcourt 90% du terrain*/
	private String T90;
	/**Distance pour que le robot parcourt 100% du terrain*/
	private String D100; 
	/**Temps pour que le robot parcourt 100% du terrain*/
	private String T100;
	
/**Constructeur*/
public Donnee(Terrain t, Robot r)  {
		
		this.t = t;
		this.r = r;
	}

/**Afficher donnée de temps et distance*/
public void afficherDonnee() {
	System.out.println(afficheDistance());
	System.out.println(afficheTemps());
	
	
}

/**Afficher donnée de temps et distance*/
public void afficherDonnee(String distance, String temps) {
	
	
	System.out.println(distance);
	System.out.println(temps);
}

/**Retourne le nombre de case*/
public int distanceEnMetre() {
	return r.getDistance()*3;
}

/**Calcule la distance à partir du temps mis par le robot et sa vitesse*/
public double convertDistancetoTemps() {
		double t = distanceEnMetre() / r.getVitesse();
		return t;			
	}


/**Affiche avec une phrase le pourcentage de terrain et distance*/
public String afficheDistance(){
	
	
	
	return("Le robot a tondu "+t.TerrainTondu()+" % du terrain en "+(float)distanceEnMetre()/1000+" kms.");
}

/**Affiche le temps en heures, minutes et secondes*/
public String afficheTemps() {
	
	double t = convertDistancetoTemps();
	int h =0;
	int m=0;
	int s=0;
	
	while(t > 3600) {
		h++;
		t = t- 3600;
	}
	
	while(t > 60) {
		m++;
		t = t- 60;
	}
	
	while(t > 1) {
		s++;
		t = t - 1;
	}

	
	return(""+h+"heures, "+m+" minutes, "+s+" secondes.");
}

/**Accesseur*/
public String getD50() {
	return D50;
}

/**Accesseur*/
public String getT50() {
	return T50;
}

/**Accesseur*/
public String getD75() {
	return D75;
}

/**Accesseur*/
public String getT75() {
	return T75;
}

/**Accesseur*/
public String getD90() {
	return D90;
}

/**Accesseur*/
public String getT90() {
	return T90;
}

/**Accesseur*/
public String getD100() {
	return D100;
}

/**Accesseur*/
public String getT100() {
	return T100;
}

/**mutateur*/
public void setD50(String d50) {
	D50 = d50;
}

/**mutateur*/
public void setT50(String t50) {
	T50 = t50;
}

/**mutateur*/
public void setD75(String d75) {
	D75 = d75;
}

/**mutateur*/
public void setT75(String t75) {
	T75 = t75;
}

/**mutateur*/
public void setD90(String d90) {
	D90 = d90;
}

/**mutateur*/
public void setT90(String t90) {
	T90 = t90;
}

/**mutateur*/
public void setD100(String d100) {
	D100 = d100;
}

/**mutateur*/
public void setT100(String t100) {
	T100 = t100;
}


}
