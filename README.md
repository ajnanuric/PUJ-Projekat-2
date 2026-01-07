# Life Management System (PUJ – Projekat 2)

## Opis projekta
Life Management System je Java aplikacija razvijena u okviru predmeta Programiranje u Javi. 
Aplikacija omogućava korisniku praćenje svakodnevnih aktivnosti i lične produktivnosti kroz
više funkcionalnih modula, uz podršku za različite teme i napredne opcije analitike.

Projekat 2 predstavlja proširenje Projekta 1, gdje je modul za upravljanje finansijama
integrisan u novu JavaFX aplikaciju.

## Funkcionalnosti
- Registracija i prijava korisnika
- Odabir i promjena teme aplikacije (Pink, Dark, Blue, Green)
- Sleep Tracker – unos i pregled sati sna po danima
- Mood Tracker – praćenje dnevnog raspoloženja
- Habit Tracker – dodavanje i praćenje navika
- Finance Tracker – integracija modula iz Projekta 1 (Swing)
- Generator dnevnih podsjetnika
- Analitika i značke postignuća na osnovu aktivnosti korisnika
- Export izvještaja u PDF format

## Tehnologije
- Java 11
- JavaFX
- Swing (Finance Tracker – Projekat 1)
- MongoDB
- Maven
- iText PDF biblioteka

## Struktura projekta
- lms – glavna logika aplikacije i JavaFX ekrani
- lms.analytics – analitika i PDF export
- lms.achievement – sistem znački postignuća
- lms.database – konekcija i rad sa MongoDB bazom
- finance – modul za finansije (preuzet iz Projekta 1)

## Napomena
Aplikacija je razvijena uz dodatne funkcionalnosti
predviđene za ostvarivanje veće ocjene.
