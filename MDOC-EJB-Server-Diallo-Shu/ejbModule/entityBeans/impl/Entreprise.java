package entityBeans.impl;

import javax.persistence.Entity;

import entityBeans.IEntreprise;

@Entity
public class Entreprise extends Contact implements IEntreprise {
	private int numSiret;

	public int getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(int numSiret) {
		this.numSiret = numSiret;
	}
	
	
}
