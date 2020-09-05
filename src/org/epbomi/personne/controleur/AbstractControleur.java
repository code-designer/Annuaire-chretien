package org.epbomi.personne.controleur;

import java.util.Set;

import org.epbomi.personne.model.Personne;

public abstract class AbstractControleur {
	
	
	public AbstractControleur()
	{
		
	}
	
	public abstract boolean save(Personne p);
	
	public abstract void del(Personne p);
	
	public abstract Set<Personne> search(String filter);
	
	public abstract Set<Personne> getAll();
	
}
