package org.epbomi.personne.model;

public enum Departement {
	INCONNU("INCONNU"),
	MCEC("MCEC"),
	MCID("MCID"),
	MCLA("MCLA"),
	MCSEO("MCSEO"),
	MCEG("MCEG"),
	MCDIA("MCDIA"),
	MCEF("MCEF"),
	MCCF("MCCF"),
	MCFA("MCFA"),
	MCMM("MCMM"),
	MCRP("MCRP"),
	SESAM("SESAM"),
	ASSISTANAT("ASSISTANAT");
	
	private String name = "";
	
	Departement(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
	
	public static Departement toEnum(String value)
	{
		if(value != null)
		{
			for(Departement e: values())
			{
				if(e.name.equals(value))
					return e;
			}
		}
		
		return INCONNU;
	}
	
}
