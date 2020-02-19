# Követelmény specifikáció

## Áttekintés
Az alkalmazásunk szórakozás céljából készül. Rendelkezik egy menüvel, amelyben nevet valamint karaktert és pályát választhatunk. A karaktereket saját magunk hoztuk létre, ugyanúgy, mint a pályákat. Adatbázisban tároljuk a szerzett pontokat, amelyek a pályán való haladás és az összegyűjtött szimbólumok alapján adódik össze. Ezeket a játék végén megjelenítjük egy táblázatban a játékos neve mellett, a többi játékos pontjaival csökkenő sorrendbe rendezve. 8 bites ábrázolással rendelkező asztali alkalmazás.

## Jelenlegi helyzet
Új játékokra mindig szükség van. Ha a felhasználóknak tetszik egy fajta játék, szeretnének találni olyan hasonló játékokat, amik csak elvben ugyanazok, de vannak újítások benne. Pl:
- karakterekben
- pályákban
- pontozási módokban
- összeszedhető tokenek/szimbólumokban való változtatások, melyektől a játék megváltozik, ettől újnak számít. A felhasználóknak mindig lesznek új és új igényeik, ezek létrehozása kielégíthetik az igényeiket. Fontos a játékok korszerűsítése és optimalizálása.

## Jelenlegi üzleti folyamatok
Mai világban nagy az igény a számítógépes játékokra kikapcsolódás gyanánt. Sajna a nagy játék kiadók elmentek egy olyan írányba hogy a minnél szebb játékot fejlesszenek , ezáltal háttérbe szorítva a történetet. Hatalmas igénybe merült fel az indi piac felé az egyszerű de igényes és jó történettel ellátot játékok felé . Mi ezt a piacot szeretnénk kiszolgálni ezzel a játékkal .

## Igényelt üzleti folyamatok
A felhasználó az első indításkor meg kell addnia a játékos nevét ha van ilyen név már mentve akkor betölti az eddigi eredményeket ha nincs akkor létre lesz hozzva neki egy új mentés . Mentés betöltése után a egy egyszerű menüt láthatunk majd ahol ki lehet választani a pályákat , be lehet a zene hang erejét állítani és ki lehet lépni .

## Fogalomtár 
- Reszponzív felület: Mindig igazodik a felület a képerányhoz.
- Beállítások: Itt lesz lehetőség a zene hangját állítani. 
- Pálya választó: Itt lesz lehetőség a már fel oldott pályák végig vitelére.
- Fel oldott pályák: egy pálya akkor lesz "feloldva" ha az elötte lévő szintet sikeresen meg csináltuk
- Karakter választó: A felhasználónak lehetősége lesz 2 karakter között választani. 
- Ellenfelek: Mozgó ellemek amiket ellkell kerülni azért vannak a játékban hogy nehezítsék a játékot .
- Csapdák: Statikus ellenfelek akik nem mozognak fix pozícióban vannak.
