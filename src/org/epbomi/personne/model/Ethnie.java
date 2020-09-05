package org.epbomi.personne.model;

public enum Ethnie {
	INCONNU("INCONNU"),
	ABBEY("ABBEY"),
	ABIDJI("ABIDJI"),
	ABOURE("ABOURE"),
	ABRON("ABRON"),
	ADJOUKROU("ADJOUKROU"),
	AGNI("AGNI"),
	AHIZI("AHIZI"),
	AKIE("AKIE"),
	ALLADJAN("ALLADJAN"),
	AMERICAIN("AMERICAIN"),
	APPOLO("APPOLO"),
	AVIKAM("AVIKAM"),
	BAKWE("BAKWE"),
	BAMBARA("BAMBARA"),
	BAOULE("BAOULE"),
	BENINOIS("BENINOIS"),
	BETE("BETE"),
	BIREFOR("BIREFOR"),
	BURKINABE("BURKINABE"),
	DAN("DAN"),
	DEGHA("DEGHA"),
	DIDA("DIDA"),
	DIOULA("DIOULA/KOYAKA"),
	EBRIE("EBRIE"),
	EHOTILE("EHOTILE"),
	EGA("EGA"),
	ESSOUMA("ESSOUMA"),
	EUROPEEN("EUROPEEN"),
	FOULAHUELA("FOULA-HUELA"),
	GAGOU("GAGOU"),
	GBIN("GBIN"),
	GHANEEN("GHANEEN"),
	GODIE("GODIE"),
	GONJA("GONJA"),
	GOUION("GOUION"),
	GOURO("GOURO"),
	GUINEEN("GUINEEN"),
	KODIA("KODIA"),
	KOTROHOU("KOTROHOU"),
	KOULANGO("KOULANGO"),
	KOUYA("KOUYA"),
	KOUZIE("KOUZIE"),
	KROBOU("KROBOU"),
	KROUMEN("KROUMEN"),
	LOBI("LOBI"),
	MALINKE("MALINKE"),
	MBATTO("M'BATTO"),
	MONA("MONA"),
	NGAN("N'GAN"),
	NZIMA("N'ZIMA"),
	NEYO("NEYO"),
	NIAMBOUA("NIAMBOUA"),
	NIDROU("NIDROU"),
	NIEDEBOUA("NIEDEBOUA"),
	NIGERIEN("NIGERIEN"),
	OUAN("OUAN"),
	OUBI("OUBI"),
	SAMAGHO("SAMAGHO"),
	SENOUFO("SENOUFO"),
	SITI("SITI"),
	TAGBANA("TAGBANA"),
	TEGUESSIE("TEGUESSIE"),
	TOGOLAIS("TOGOLAIS"),
	TOURA("TOURA"),
	WANE("WANE"),
	WE("WE"),
	YOROBOUA("YOROBOUA"),
	YOWLE("YOWLE"),
	AUTRES("AUTRES");
	
	private String name;
	
	Ethnie(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
	
	public static Ethnie toEnum(String value)
	{
		if(value != null)
		{
			for(Ethnie e : values())
			{
				if(e.name.equals(value))
				{
					return e;
				}
			}
		}
		return INCONNU;
	}
}