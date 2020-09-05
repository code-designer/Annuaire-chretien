package org.epbomi.personne.model;

public enum Statut {
	INCONNU("INCONNU"),
	CELIBATAIRE("CELIBATAIRE"),
	CONCUBIN("CONCUBIN(E)"),
	FIANCE("FIANCE(E)"),
	MARIE("MARIE(E)"),
	VEUF("VEUF/VEUVE");
	
	private String name;
	
	Statut(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
	
	public static Statut toEnum(String value)
	{
		if(value != null)
		{
			for(Statut s: values())
			{
				if(s.name.equals(value))
					return s;
			}
		}
		
		return INCONNU;
	}
}
