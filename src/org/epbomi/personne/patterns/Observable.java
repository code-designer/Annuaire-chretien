package org.epbomi.personne.patterns;

import org.epbomi.personne.model.Personne;

public interface Observable {
	public void addObservers(Observer obs);
	public void delObserver(Observer obs);
	public void updateObserverForAdding(Personne p);
	public void updateObserverForUpdating(Personne p);
	public void updateObserverForDeleting(Personne p);
	public void updateObserverForScreen();
}
