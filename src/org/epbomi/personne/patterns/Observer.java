package org.epbomi.personne.patterns;

import org.epbomi.personne.model.Personne;

public interface Observer {
	public void updateScreen();
	public void updateAdding(Personne p);
	public void updateUpdating(Personne p);
	public void updateDeleting(Personne p);
}
