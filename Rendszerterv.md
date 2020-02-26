# Rendszerterv

## A rendszer célja
A rendszer célja a folyamatosan újuló igények kielégítése valamint a játékok izgalmasságának fenntartása. A játékos választhat pályát, melyek egyre nehezednek, váratlan események történhetnek, így ha végig akarják játszani a játékot akkor fejleszteniük kell a reakcióidejüket valamint a memóriájukat(pl egy hosszúpályánál), megoldóképességüket. Nem célunk webes vagy telefonos felületre is implementálni a játékot, csak asztali alkalmazásként szeretnénk futhatóvá tenni. A játékosok pontokat szereznek a pálya százalékos teljesítése és a felszedett szimbólumok függvényében, amiket toplistán megjelenítünk. A rendszer az adatokat (pl a toplistához) az adatbázisból kapjuk.  

## Architekturális terv
A rendszerhez szükség van egy adatbázis szerverre, ebben az esetben MySql-t használunk. Az asztali program JavaFX keretrendszer használatával készül el. Bejelentkezés után elérhetőek lesznek az eddig elért eredmények.

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

## Teszt terv

A tesztelések célja a rendszer és komponensei funkcionalitásának teljes vizsgálata, ellenőrzése, a rendszer által megvalósított üzleti szolgáltatások verifikálása.

Tesztelési eljárások
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