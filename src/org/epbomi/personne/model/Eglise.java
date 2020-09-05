package org.epbomi.personne.model;

public enum Eglise {
	INCONNU("INCONNU"),
	JEUNESSE("EGLISE LOCALE JUNIOR"),
	PARENT("EGLISE LOCALE PARENT");
	
	private String name;
	
	Eglise(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
	
	public static Eglise toEnum(String value)
	{
		if(value != null)
		{
			for(Eglise e: values())
			{
				if(e.name.equals(value))
					return e;
			}
		}
		
		return INCONNU;
	}
}
