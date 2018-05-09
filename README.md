A feladat témája:
Az önálló laboratórium keretében egy olyan webalkalmazást fogok készíteni, amely egy jármű menetlevelét helyettesítheti.

Funkciók:
- Bejelentkezés felhasználó és adminisztrátor szerepkörrel.

Felhasználói szerepkör funkciói:
- Utazás felvétele, amely tartalmazza a megtett kilómétert, a járművet, kezdő- és végállomást, valamint a személyeket.
- Saját felvett utazás módosítása.
- Saját utazások listázása.
- Összes utazás listázása.
- Tankolás felvitele.
- Helyszínek felvitele, módosítása és törlése.
- Korábbi elszámolások megtekintése.

Adminisztrátori szerepkör funkciói:
- Felhasználó felvétele, módosítása, törlése.
- Bármelyik felvett utazás módosítása.
- Tankolások módosítása, törlése.
- Elszámolás készítése.

Az elszámolás alatt azt lehet érteni, hogy létrehoz egy táblázatot, amelyben felhasználóra lebontva megtekinthető az előző elszámolás óta megtett kilóméter, az ehhez tartozó költség (ami a jármű átlagfogyasztásából van kiszámolva), az összes tankolt összeg, illetve a két összeg különbsége.

Adat réteg:
Az alkalmazáshoz JPA-t és Spring Data-t fogok használni az adatbázis eléréséhez.

A tervezett entitások:
- Felhasználó
- Jármű
- Utazás
- Helyszín (kezdő- és végállomások)
- Tankolás
- Elszámolás

A Felhasználó kapcsolattal rendelkezik az Utazás, Tankolás és Elszámolás entitás felé.
Az Jármű kapcsolattal rendelkezik az Utazás, Tankolás és Elszámolás entitás felé.
Az Utazás kapcsolattal rendelkezik a Felhasználó, a Jármű, a Helyszín és az Elszámolás entitás felé.
Az Elszámolás kapcsolattal rendelkezik a Felhasználó, a Jármű, az Utazás és a Tankolás entitás felé.

Az üzleti logikát Spring segítségével fogom megoldani, a web réteg és az oldalsablonok megvalósításához pedig Vaadin webes keretrendszert fogok használni.