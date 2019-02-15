
public class DeplacementSalle {

	protected int compteur = 0;
	protected int valueCapteur;
	protected boolean firstDeplacementLeft; //Pour se reperer pour pouvoir sortir
	

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}
	
	public void deplacement() {
		this.compteur++;
		while(compteur!=0) {
			//faire avancer le robot
			if(valueCapteur < 0) {//on regarde si la ligne est toujours devant nous - valeur a specifier
				if(compteur ==1) {
					this.firstDeplacementLeft=true;
				}
				if (compteur == 4) {
					if(firstDeplacementLeft == true) {
						//on sort en tournant a droite
					}else {
						//on sort en tournant a gauche
					}
					//on reinitialise pour la prochaine salle
					compteur = 0;
					break;
				}
				
				//faire tourner robot a gauche
				if(valueCapteur <0) {//on regarde si il est maintenant sur la ligne sinon on lui fait faire demi tour
					//faire un demi tour au robot
					if(compteur ==1) {
						this.firstDeplacementLeft=false;
					}
				}
				compteur++;
			}
		}	
	}
}
