# Rendszerterv

## A rendszer célja
A rendszer célja a folyamatosan újuló igények kielégítése valamint a játékok izgalmasságának fenntartása. A játékos választhat pályát, melyek egyre nehezednek, váratlan események történhetnek, így ha végig akarják játszani a játékot akkor fejleszteniük kell a reakcióidejüket valamint a memóriájukat(pl egy hosszúpályánál), megoldóképességüket. Nem célunk webes vagy telefonos felületre is implementálni a játékot, csak asztali alkalmazásként szeretnénk futhatóvá tenni. A játékosok pontokat szereznek a pálya százalékos teljesítése és a felszedett szimbólumok függvényében, amiket toplistán megjelenítünk. A rendszer az adatokat (pl a toplistához) az adatbázisból kapjuk.  

## Architekturális terv
A rendszerhez szükség van egy adatbázis szerverre, ebben az esetben MySql-t használunk. Az asztali program JavaFX keretrendszer használatával készül el. Bejelentkezés után elérhetőek lesznek az eddig elért eredmények.  

## Implementációs terv
### Windows Desktop Application  
Az alkalmazás felhasználói oldalát asztali alkalmazásnak tervezzük. A terv a JAVA nyelv használatát írja elő, főleg mert ismerős és könnyen használható.
### Adatbázis  
A tervezett szolgáltatás jellegét tekintve egy központi adatbázis használata elengedhetetlen. A felhasználók, és azok adatainak rögzítése, tárolása és rendszerezése céljából a modern adatbáziskezelés konvenciói a legcélravezetőbbek. Arra, hogy ezeket implementálhassuk, a MySQL rendszerét választottuk, az ismeretségeink tudatában, és a megbízhatóság fényében.  

## Funkcionális terv
**Rendszerszereplők:**
Felhasználók

**Rendszerhasználati esetek és lefutásaik:**
Felhasználók:
- Név megadása ami betölti az előzményeket, ha voltak, ha nem akkor új felhasználót hoz létre
- Karakter kiválasztása
- Pálya kiválasztása
- Következő pályára lépés 
- Pálya újrakezdése
- Zene lenémítása
- Zene felhangosítása
- Toplista megtekintése
- Kilépés

## Fizikai környezet

- Az alkalmazás PC-re készül
- A következő operációs rendszereken lesz elérhető: **Windows, Linux, Macintosh**
- Nincsenek megvásárolt komponenseink
- Nincsenek alkalmazáson belüli mikrotranzakciók
**Fejlesztői eszközök:**
- IntelliJ IDE
- MySQL Workbench

## Teszt terv
A tesztelések célja a rendszer és komponensei funkcionalitásának teljes vizsgálata, ellenőrzése, a rendszer által megvalósított üzleti szolgáltatások verifikálása.

**Tesztelési eljárások**
- Unit teszt: A teszt elsődleges célja: az eddig meglévő funkcióknak a különböző böngészőkkel való kompatibilitásának tesztelése. A tesztet a fejlesztők végzik. 
Az eljárás sikeres, ha különböző operációs rendszereken megfelelően működnek a különböző funkciók. A teszt időtartama egy hét.

- Beta teszt: Ezt a tesztet nem a fejlesztők végzik.
Tesztelendő operációs rendszerek: Windows , Linux , MacOs
Tesztelendő kijelző méretek: 1280x720 (minimum), 1366x768, 1920x1080
A teszt időtartama egy hét. 
A tesztelés alatt a tesztelő felhasználók visszajelzéseket küldhetnek a fejlesztőknek, probléma/hiba felmerülése esetén. 
Ha hiba lép fel, a fejlesztők kijavítják a lehető leghamarabb. Sok hiba esetén a tesztelés ideje elhúzódhat plusz egy héttel.

- Tesztelendő funkciók, Backend Service: Képesnek kell lennie menteni a játékos adatait és azokat betölteni  
Képesnek kell lennie minden felületen elérhető funkciók biztosítására.

## Telepítési terv
A Játékos a web oldalunkat felkeresve a letöltheti. Itt kettő változat közül választhat egy pocket editon közül mely telepítés nélkül működik és rögtön játszható. Teleíthető váltazat varászló segítségével ez a változat végig vezeti a felhasználót a telepítés menetén kényelmesen , meg szokott módon és minden hiányzó programot , .dll telepít. Sikeres telepítés után a játék már rögtön játszható és élvezhető. 

## Karbantartási terv
Az alkalmazás folyamatos üzemeltetése és karbantartása, mely magában foglalja a programhibák elhárítását, a belső igények változása miatti módosításokat, valamint a környezeti feltételek változása miatt megfogalmazott program-, illetve állomány módosítási igényeket. Ellenőrizni kell, hogy a jövőben kiadott Microsoft Windows verziókkal kompatibilis-e az alkalmazás. A jövőben szükség lehet új hardware implementációra igénynövekedés esetén. Karbantartás Corrective Maintenance: A felhasználók által felfedezett és "user reportban" elküldött hibák kijavítása. Adaptive Maintenance: A program naprakészen tartása és finomhangolása. Perfective Maintenance: A szoftver hosszútávú használata érdekében végzett módosítások, új funkciók, a szoftver teljesítményének és működési megbízhatóságának javítása. Preventive Maintenance: Olyan problémák elhárítása, amelyek még nem tűnnek fontosnak, de később komoly problémákat okozhatnak.
