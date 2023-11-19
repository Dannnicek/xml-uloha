## Zadání:
Vytvořit javovskou aplikaci, která stáhne data v xml z internetu, zpracuje je a uloží do sql databáze.
Zazipované XML na URL https://www.smartform.cz/download/kopidlno.xml.zip obsahuje všechny adresy v obci Kopidlno.
Cílem je napsat program který stáhne soubor z daného URL, zparsuje data z XML a (některá) uloží do DB.
Aplikace má dvě tabulky - obec a část obce.
U obce stačí do DB vložit kód a název, u části obce kód, název a kód obce, ke které část obce patří
Program by měl tyto dvě tabulky naplnit (V tom XML by měla být jedna obec - element vf:Obec - a několik málo částí
obce - vf:CastObce).
Program nemusí databázové schéma vytvářet, to stačí udělat ručně.
Na parsování použít nějaký standardní nástroj (nějakou knihovnu např. DOM, SAX, StAX, ...).
Není nutné při parsování získat všechna data, co jsou v XML, stačí z něj získat jen ta, která budeme dávat do DB.
Jako databázi lze použít libovolnou SQL databázi.
Program by měl být napsán v Javě (samozřejmě můžete použít jakýkoliv framework, který znáte a usnadní vám práci).
Chuti a fantazii se meze nekladou a na úloze nám můžete ukázat co umíte. Těšíme se na vaše inovativní řešení.


## Řešení:
Jako databázi jsem využil MySQL. Pro implementaci aplikace jsem využil framework Spring Boot. Pro správný běh programu
stačí vytvořit databázi a nastavit příslušné údaje v souboru resources/application.properties.

