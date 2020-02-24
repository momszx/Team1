# Funkcionális specifikáció

## Áttekintés
Az alkalmazásunk szórakozás céljából készül. Játékunk ingyenes, bárki számára elérhető. Rendelkezik egy menüvel, amelyben nevet valamint karaktert és pályát választhatunk. Az ábrázolás úgynevezett 8bites ábrázolás, azaz pixeles, ezzel egyfajta retro érzést kapunk, mégis a játék maga gördülékeny. A karaktereket saját magunk hoztuk létre, ugyanúgy, mint a pályákat. Adatbázisban tároljuk a szerzett pontokat, amelyek a pályán való haladás és az összegyűjtött szimbólumok alapján adódik össze. Ezeket a játék végén megjelenítjük egy táblázatban a játékos neve mellett, a többi játékos pontjaival csökkenő sorrendbe rendezve amolyan toplistás rendezésben. Ez lehetővé teszi a többi játékosokhoz való összehasonlítást, versengést.

## Jelenlegi helyzet
A szükség, ami ösztönzött minket hogy implementáljuk ezt az alkalmazást a felhasználók változó és újuló igényei. Nem ragadhatunk le ugyanazoknál a játékoknál, kell hogy legyenek benne újítások,hogy fenntartsuk az érdeklődésüket és ne legyen romló véleményük.

## Követelménylista
|    Modul    | ID |       Név        |                                                        Kifejtés                                                        |
|-------------|----|------------------|------------------------------------------------------------------------------------------------------------------------|
| Jogosultság | I1 | Belépési felület | A felhaszáló megadja a játékosnevét. Ha létezik, egy menü fogadja, ha nem, indul a játék automatikusan.                |
|   Felület   | I2 | Pályaválasztás   | Főmenü pont, a játékos később innen tudja kiválasztani a következő pályát, vagy újrajátszani előzőt egy jobb időért.   |
|   Felület   | I3 | Karakterválasztás| Főmenü pont, a játékos 2 karakter közül választhat számára megfelelőt.                                                 |
|   Felület   | I4 | Kilépés          | Főmenü pont, a játékos kiléphet.                                                                                       |
|   Felület   | I5 | Beállítások      | Főmenü pont, a játékos állíthatja a hangerőt.                                                                          |
| Statisztika | I6 | Toplista         | A játékosok idejét, és pontszámát egy toplistán jelenítjük meg a szint teljesítése után.                               |
| Statisztika | I7 | Adatbázis        | Itt tároljuk a játékos nevét, előrehaladását, pontszámát és idejét.                                                    |
|   Feature   | I8 | Időszámláló      | Az időzítő egyből a pálya megkezdése után elindul, teljesítés után leáll.                                              |

## Igényelt üzleti folyamatok modellje
Az emberek igénye a videójátékok iránt mindig is magas volt. Fő célunk hogy ezeket a személyeket szórakoztassuk. A játékosok szeretik összemérni tudásukat, és egy hosszú nap után kicsit kikapcsolni. Egy felhasználóbarát egyszerű retro játék elkészítése a cél, ami standard időtöltést és kihívást nyújthat a játékosoknak. Kevés manapság a régibb típusú játék, főleg azoknak az embereknek készült, akik kicsit visszamennének az időben, mikor még nem volt se lootbox, se kozmetika, se ranked, se balance probléma, csak a kész játék és a játékos.