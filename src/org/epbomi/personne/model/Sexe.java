package org.epbomi.personne.model;

public enum Sexe {
	INCONNU("INCONNU"),
	MASCULIN("MASCULIN"),
	FEMININ("FEMININ");
	
	private String name = "";
	
	Sexe(String n)
	{
		name = n;
	}
	
	public String toString() 
	{
		return name;
	}
	
	public static Sexe toEnum(String value)
	{
		if(value != null)
		{
			for(Sexe s: values())
			{
				if(s.name.equals(value))
					return s;
			}
		}
		
		return INCONNU;
	}
}
