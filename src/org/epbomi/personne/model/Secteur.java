package org.epbomi.personne.model;

public enum Secteur {
	INCONNU("INCONNU"),
	PRIVE("PRIVE"),
	PUBLIC("PUBLIC"),
	LIBERAL("LIBERAL"),
	SCOLAIRE("ELEVE/ETUDIANT"),
	RETRAITE("RETRAITE(E)");
	
	
	private String name;
	
	Secteur(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
	
	public static Secteur toEnum(String value)
	{
		if(value != null)
		{
			for(Secteur s: values())
			{
				if(s.name.equals(value))
					return s;
			}
		}
		
		return INCONNU;
	}
}
