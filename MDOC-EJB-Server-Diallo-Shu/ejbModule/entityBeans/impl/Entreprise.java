package entityBeans.impl;

import javax.persistence.Entity;

@Entity
public class Entreprise extends Contact {
	private int numSiret;

	public int getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(int numSiret) {
		this.numSiret = numSiret;
	}
	
	
}
