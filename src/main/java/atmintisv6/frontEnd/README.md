# Atmintis
JAVAU7

Programos veikimo principas:

1. Naudojant OAuth2 patvirtinama tapatybė (Google/Github)
2. Paimami duomenys iš google contacts
3. Užpildoma vietinė SQL duomenų bazė duomenimis paimtais iš google contacts
4. Duomenys atvaizduojami ir manipuliuojami reactjs ir naršyklės pagalba 
Naudojimosi instrukcijos:

1.  https://console.cloud.google.com/ tinklapyje prisijungti ir sukurti tokeną su teisėmis naudotis google PEOPLE API. 
2.	Atsisiūsti sukurto tokeno json failą ir patalpinti jį ~/resources direktorijoje.
3.	Atsisiustą json failą pavadinti credentialsVEIKIA.json
4.	Paleisti programą.
5.  Paleisti frontend programą.
6.	Prisijungti http://localhost:8080/login/auith (patartina naudoti google prisijungimą).
7.	Prima karta jungiatis, prisijungus javos konsoleje bus parasyta google nuoroda. 
	    Ja atsidarius reikia patvirtinti priejima prie PEOPLE API duomenu.
8.	Atsidaryti http://localhost3000 ir naudotis programa.
Produktas nėra baigtas ir nėra tinkams(skirtas) viešam naudojimui ar platinimui. Šiuo metu duomenys esantys google contacts nėra redaguojami. Visi pakeitimai yra atliekami tiktai SQL serveryje esantiems duomenims.

(c) Andrius Dulevičius

Dalintis be Andriaus Duleviciaus raštiško sutikimo yra draudžiama.