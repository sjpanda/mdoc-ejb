package entityBeans.impl;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Entreprise extends Contact implements Serializable {
	private static final long serialVersionUID = 1L;
	private int numSiret;

	public int getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(int numSiret) {
		this.numSiret = numSiret;
	}
	
	
}
