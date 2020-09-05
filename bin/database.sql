BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "personne" (
	"personne_id"	INTEGER NOT NULL,
	"code"	TEXT NOT NULL UNIQUE,
	"nom"	TEXT NOT NULL,
	"prenoms"	TEXT NOT NULL,
	"sexe"	TEXT NOT NULL,
	"date_de_naissance"	TEXT,
	"lieu_de_naissance"	TEXT,
	"ethnie"	TEXT,
	"nationalite"	TEXT,
	"pathimg"	TEXT,
	PRIMARY KEY("personne_id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "profession" (
	"profession_id"	INTEGER NOT NULL,
	"secteur"	TEXT,
	"profession"	TEXT,
	"structure"	TEXT,
	"descriptif_activite"	TEXT,
	PRIMARY KEY("profession_id"),
	FOREIGN KEY("profession_id") REFERENCES "personne"("personne_id") ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS "contact" (
	"contact_id"	INTEGER NOT NULL,
	"cel"	TEXT,
	"tel"	TEXT,
	"urgence"	TEXT,
	"postal"	TEXT,
	"email"	TEXT,
	"residence"	TEXT,
	PRIMARY KEY("contact_id"),
	FOREIGN KEY("contact_id") REFERENCES "personne"("personne_id") ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS "ministere" (
	"ministere_id"	INTEGER NOT NULL,
	"date_conversion"	TEXT,
	"date_bapteme"	TEXT,
	"departement"	TEXT,
	"responsabilite"	TEXT,
	"section"	TEXT NOT NULL,
	PRIMARY KEY("ministere_id"),
	FOREIGN KEY("ministere_id") REFERENCES "personne"("personne_id") ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS "vieconjugale" (
	"vieconjugale_id"	INTEGER NOT NULL,
	"situation"	TEXT,
	"conjoint"	TEXT,
	"date_union"	TEXT,
	"nombre_enfants"	INTEGER DEFAULT 0,
	PRIMARY KEY("vieconjugale_id"),
	FOREIGN KEY("vieconjugale_id") REFERENCES "personne"("personne_id") ON UPDATE CASCADE ON DELETE CASCADE
);
COMMIT;
