# Követelmény specifikáció

## Áttekintés
Az alkalmazásunk szórakozás céljából készül. Rendelkezik egy menüvel, amelyben nevet valamint karaktert és pályát választhatunk. A karaktereket saját magunk hoztuk létre, ugyanúgy, mint a pályákat. Adatbázisban tároljuk a szerzett pontokat, amelyek a pályán való haladás és az összegyűjtött szimbólumok alapján adódik össze. Ezeket a játék végén megjelenítjük egy táblázatban a játékos neve mellett, a többi játékos pontjaival csökkenő sorrendbe rendezve. 8 bites ábrázolással rendelkező asztali alkalmazás.

## Jelenlegi helyzet
Új játékokra mindig szükség van. Ha a felhasználóknak tetszik egy fajta játék, szeretnének találni olyan hasonló játékokat, amik csak elvben ugyanazok, de vannak újítások benne. Pl:
- karakterekben
- pályákban
- pontozási módokban
- összeszedhető tokenek/szimbólumokban való változtatások, melyektől a játék megváltozik, ettől újnak számít. A felhasználóknak mindig lesznek új és új igényeik, ezek létrehozása kielégíthetik az igényeiket. Fontos a játékok korszerűsítése és optimalizálása.

## Vágyálom Rendszer
A project célja egy olyan rendszer, ami a szórakoztatást és kellemes időtöltést biztosítja.
A rendszer **csak windows plaformon** lesz elérhető. A menü egy felhasználóbarát **3 pontos felület** lesz. A játékos a nevét megadva megkezdheti a játékot, ami egy **platformer-adventure orientált** környezetben történik. **2D-s**, **8-bites** grafikát szánunk a rendszernek, ami egy retrosabb hangulatot nyújt a felhasználónak. Természetesen látványosan és változatosan megoldva, hogy lekösse a játékos figyelmét. A játék fő célja, hogy az adott szintet a lehető leggyorsabban teljesítse, ergo eljusson A-ból B-be.a Ezt a teljesítményt **adatbázisban szeretnénk tárolni** a felhasználóval együtt és kialakítani egy jutalom rendszert a **futott idő alapján**, így a játékosok versenghetnek egymással. A pályákon az **ellenfelek** és a **csapdák** megnehezítik a játékos dolgát, ha egyszer is hozzájuk érünk, előlröl kell kezdeni. A további pályákat kizárólag az előző teljesítésével lehet feloldani, illetve a pálya értékelését láthatjuk az adott szintet mutató blokk, így nyomom lehet követni az eredményt.

## Funkcionális követelmény
A program az elindítás után, kér a felhasználótól egy nevet. Ha olyan nevet ad meg amivel már játszott a felhasználó, akkor egy menü fogja fogadni.
Ha olyan nevet ad meg amivel még nem játszott, akkor a játék automatikusan elindul az első pályától. Minden pálya végén le lesz osztályozva a felhasználó teljesítménye. A pályák elkezdésénél egy timer indul, ami méri a pályák teljesítési idejét. Ez idő alapján kapja a felhasználó az osztályozást(1,2 vagy 3 csillag). A következő pálya csak az előző pálya teljesítése után lesz elérhető.
A program adatbázisban tárolja az eddig elért eredményeket.  
Menüpontok:
- pályaválsztás
- karakterválsztás
- beállítások
- kilépés  
Az eredmények és a pályaválasztás, a pályaválasztás menüpontnál található.  
A karakterválsztás menüpontban két karakter közül lehet választani.
A beállítások menüpontban a játék hangerejét lehet állítani.
A kilépés menüpontot választva, bezárja a programot.

## Rendszerre vonatkozó törvények


### Általános Információk

Az Alkalmazáshoz való hozzáférést és annak használatát az alkalmazandó jogszabályok és a jelen Felhasználási Feltételek és Adatkezelési tájékoztató (a továbbiakban Felhasználási Feltételek) szabályozzák. Az Alkalmazást letöltők és használók (a továbbiakban: a Felhasználók) elfogadják a jelen Felhasználási Feltételeket. Amennyiben ezen Felhasználási Feltételeket és Adatkezelési Tájékoztatót nem fogadják el, nem jogosultak a regisztrációra és az Alkalmazás használatára.  
A jelen Felhasználási Feltételekre a magyar jog az irányadó, tekintet nélkül a nemzetközi magánjog előírásaira. Az Alkalmazás Felhasználói kifejezetten hozzájárulnak ahhoz, hogy a jogvitákra a magyar hatóságoknak és bíróságoknak legyen kizárólagos joghatóságuk a magyar jog alapján.


### Szellemi tulajdon

Az Alkalmazás és valamennyi kapcsolódó védjegy, szerzői jogi alkotás és egyéb – akár bejegyzett, akár be nem jegyzett – szellemi tulajdon (a továbbiakban együttesen: Szellemi Tulajdon) tulajdonosa az EKE és/vagy EKE Szolgáltató, valamint az alkalmazáshoz kedvezményt nyújtó partnerek. A Felhasználók az Alkalmazást a Szellemi Tulajdon maximális tiszteletben tartásával jogosultak használni. A Szellemi Tulajdon kiterjed különösen, de nem kizárólagosan valamennyi szoftverre, logóra, márkajelre, márkanévre, fényképre, szövegre, grafikára, adatbázisra. A Szellemi Tulajdonnak tilos bárminemű megsértése, bitorlása, másolása, átdolgozása és tilos azt bármilyen egyéb módon megsérteni, azt jogosulatlanul felhasználni, továbbadni, megterhelni, azzal bármilyen módon rendelkezni, visszaélni. Ezen szabályok megsértése az Alkalmazás használati lehetőségeinek azonnali hatályú megszüntetése mellett a megfelelő jogi lépések megtételét – beleértve esetleges büntetőjogi lépések megtételét is – vonja maga után a Felhasználóval, más jogsértő személlyel szemben a Tulajdonos és/vagy a Szellemi Tulajdon egyéb jogosultjai által.


### Használat

Az Alkalmazás Felhasználói 7 éven felüli természetes személyek lehetnek. Az Alkalmazás díjmentesen vehető igénybe, kizárólag privát, azaz nem üzletszerű módon és célra, kizárólag a jelen Felhasználási Feltételek szerint. Az Alkalmazás letöltéséért és használatáért az adatforgalmat biztosító szolgáltató külön díjat számíthat fel. Az ezzel kapcsolatos esetlegesen felmerülő költségek a Felhasználót terhelik.

A Felhasználási Feltételek és az Alkalmazás működésének a módosítására, az Alkalmazás működésének a megszüntetésére a Tulajdonos bármikor jogosult, a Felhasználó előzetes értesítése nélkül. Az Alkalmazáshoz való hozzáférést a Tulajdonos bármikor visszavonhatja akár az adott Felhasználóra nézve, akár szélesebb körben vagy teljes körűen előzetes értesítés, figyelmeztetés nélkül.


### Felelősségi szabályok

A Tulajdonos fenntartja magának a jogot arra, hogy amennyiben valamely Felhasználó részéről bármilyen manipulációt, tömegesen generált letöltést, illetve az Alkalmazás szellemével bármilyen módon összeférhetetlen vagy azt sértő magatartást tapasztal, vagy ennek megalapozott gyanúja felmerül, úgy a Felhasználót azonnali hatállyal kizárja az Alkalmazás felhasználói köréből.

Az Alkalmazáshoz kapcsolódó adatbázis módosítása kizárólag az Üzemeltető által, illetve az Alkalmazást üzemeltető webkiszolgálón keresztül lehetséges. Bármilyen külső, nem az Alkalmazás részeként elérhető eszközzel történő beavatkozás a Felhasználó azonnali kizárását eredményezi.

Ha a Felhasználó az Alkalmazást használat közben bezárja, vagy ha a kapcsolat (bármely okból) megszakad a kiszolgáló webhelyével, abban az esetben az adatok elvesztéséért a Tulajdonos semmilyen felelősséget nem vállal. A Tulajdonos és az Üzemeltető a rendelkezésére álló eszközökkel biztosítja, hogy az Alkalmazás használata technikai szempontból biztonságosnak minősüljön. Az Alkalmazáshoz való csatlakozás miatt bekövetkező károkért, az internetes hálózat esetleges üzemkimaradásából, az elérési út hibájából, bármely nem várt technikai hibából eredő adatvesztésért, vírusból, vagy más károkért a Tulajdonos nem felelős. A Felhasználóknak minden esetben fel kell mérniük, hogy rendelkeznek-e az Alkalmazás használatához szükséges ismeretekkel, technikai követelményekkel és teljesítményekkel.


### Technikai követelmények

Az Alkalmazás használatához szükséges technikai feltételek: Windows XP operációs rendszer valamint minimum 100 MB szabad tárhely és a használathoz megfelelő sávszélességű internetelérés. A technikai feltételeket az Alkalmazás letöltéséhez és használatához a Felhasználónak kell teljesítenie. A technikai feltételek nem teljesüléséért a Tulajdonos nem vonható felelősségre. Ugyanígy nem vonható felelősségre a Tulajdonos az Alkalmazás használatából a készüléken bekövetkező adatvesztésért, meghibásodásért. A Tulajdonos kizár minden kártérítési felelősséget az Alkalmazáshoz csatlakozó minden külső szerver által nyújtott (kiemelten Facebook adatok átvétele) vagy megjelenített adattal, információval kapcsolatban is.

Az Alkalmazás telepítéssel vehető használatba.

### Garancia és kártérítés

Az Alkalmazás használatához a felhasználói oldalon szükséges – fent meghatározott vagy bármely egyéb - technikai feltételeket a Felhasználónak kell biztosítania, teljesítenie. Ezen technikai feltételek nem teljesüléséért a Tulajdonos nem vonható felelősségre. Ugyanígy nem vonható felelősségre a Tulajdonos az Alkalmazás használatából adódóan, a készüléken bekövetkező adatvesztésért, meghibásodásért. A Tulajdonos kizár minden kártérítési felelősséget az Alkalmazáshoz csatlakozó minden külső szoftver által nyújtott (így kiemelten Facebook adatok átvétele) vagy megjelenített adattal, információval kapcsolatban. A Tulajdonos nem vállal garanciát az Alkalmazás megszakításmentes működéséért, valamint vis major hibákért. Az ebből eredő adatvesztésért, tartalom vesztésért a Tulajdonos szintén nem tartozik kártérítési felelősséggel.


### Egyéb rendelkezések

Jelen Felhasználási Feltételekben nem szabályozott kérdésekben a hatályos jogszabályok – különösen, de nem kizárólagosan a Polgári Törvénykönyvről szóló 2013. évi V. törvény, az Európai Parlament és Tanács 2016. április 27-i (EU) 2016/679 Rendelete a természetes személyeknek a személyes adatok kezelése tekintetében történő védelméről és az ilyen adatok szabad áramlásáról, valamint a 95/46/EK irányelv hatályon kívül helyezéséről, az információs önrendelkezési jogról és az információ szabadságról szóló 2011. évi CXII. törvény, a szerzői jogról szóló 1999. évi LXXVI. törvény, valamint az elektronikus kereskedelmi szolgáltatások, valamint az információs társadalommal összefüggő szolgáltatások egyes kérdéseiről szóló 2001. évi CVIII. törvény – rendelkezései az irányadóak.


### Kapcsolat

Az Alkalmazás támogatását az Üzemeltető végzi munkanapokon, 9:00 és 17:00 között. Az Alkalmazás működésével kapcsolatban a Felhasználó czafikm97@gmail.com e-mail címre küldheti kérdéseit, amelyre az Üzemeltető a fenti időszakban válaszol.


## Jelenlegi üzleti folyamatok
Mai világban nagy az igény a számítógépes játékokra kikapcsolódás gyanánt. Sajna a nagy játék kiadók elmentek egy olyan írányba hogy a minnél szebb játékot fejlesszenek , ezáltal háttérbe szorítva a történetet. Hatalmas igénybe merült fel az indi piac felé az egyszerű de igényes és jó történettel ellátot játékok felé . Mi ezt a piacot szeretnénk kiszolgálni ezzel a játékkal .

## Igényelt üzleti folyamatok
A felhasználó az első indításkor meg kell addnia a játékos nevét ha van ilyen név már mentve akkor betölti az eddigi eredményeket ha nincs akkor létre lesz hozzva neki egy új mentés . Mentés betöltése után a egy egyszerű menüt láthatunk majd ahol ki lehet választani a pályákat , be lehet a zene hang erejét állítani és ki lehet lépni .

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


## Fogalomtár 
- Reszponzív felület: Mindig igazodik a felület a képerányhoz.
- Beállítások: Itt lesz lehetőség a zene hangját állítani. 
- Pálya választó: Itt lesz lehetőség a már fel oldott pályák végig vitelére.
- Fel oldott pályák: egy pálya akkor lesz "feloldva" ha az elötte lévő szintet sikeresen meg csináltuk
- Karakter választó: A felhasználónak lehetősége lesz 2 karakter között választani. 
- Ellenfelek: Mozgó ellemek amiket ellkell kerülni azért vannak a játékban hogy nehezítsék a játékot .
- Csapdák: Statikus ellenfelek akik nem mozognak fix pozícióban vannak.