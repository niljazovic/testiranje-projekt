# Automated Testing Framework – Selenium & REST API

## Opis projekta

Ovaj projekt predstavlja **okvir (framework) za automatsko testiranje programske podrške**, izrađen u sklopu kolegija *Metode i tehnike testiranja programske podrške*.

Framework objedinjuje:
- **automatsko testiranje web korisničkog sučelja (UI)** korištenjem Selenium WebDrivera
- **automatsko testiranje REST API-ja** korištenjem REST Assured biblioteke
- **kontinuiranu integraciju (CI)** putem GitHub Actions

Projekt je izrađen u programskom jeziku **Java**, uz korištenje **Maven** alata za upravljanje ovisnostima i **TestNG** okvira za izvođenje testova.

---

## Korištene tehnologije i alati

- Java (JDK 17)
- Maven
- Selenium WebDriver
- TestNG
- REST Assured
- WebDriverManager
- Git & GitHub
- GitHub Actions (CI)
- Chromium / Firefox (za UI testove)

---

## Struktura projekta
```
src/test/java
├── hr/ferit/framework
│   ├── core           # BaseTest, DriverFactory, Wait helpers
│   ├── pages          # Page Object Model klase
│   └── tests          # UI testovi
└── hr/ferit/framework/api
    ├── core           # API base konfiguracija
    └── tests          # REST API testovi
```

---

## Implementirane tehnike i koncepti

### UI testiranje (LV2)

- Selenium WebDriver
- **Page Object Model (POM)**
- **Explicit wait naredbe**
- Cross-browser testiranje (Chrome/Chromium, Firefox)
- Headless izvođenje testova
- Objektno-orijentirani pristup (BaseTest, DriverFactory)

**Testirana aplikacija:**
- https://the-internet.herokuapp.com

**Primjeri testova:**
- Login (uspješan i neuspješni scenariji)
- Rad s checkbox elementima
- Dinamičko učitavanje sadržaja

---

### REST API testiranje (LV3)

- REST Assured
- TestNG
- Data-driven testiranje (`@DataProvider`)
- CRUD operacije (GET, POST, PUT, DELETE)

**Testirani API:**
- https://jsonplaceholder.typicode.com

**Primjeri testova:**
- Dohvat resursa (GET)
- Kreiranje resursa (POST)
- Ažuriranje resursa (PUT)
- Brisanje resursa (DELETE)

---

### Ručno testiranje (LV1)

Prije automatizacije definirani su osnovni ručni testni slučajevi (login, validacija unosa), a odabrani scenariji automatizirani su zbog:
- čestog izvođenja
- velike vjerojatnosti regresije
- ponovljivosti

---

### Kontinuirana integracija (CI)

Projekt koristi **GitHub Actions** za kontinuiranu integraciju:
- Testovi se automatski izvršavaju pri svakom `push` ili `pull request` događaju
- Izvode se UI i API testovi
- Generira se HTML test report

**CI konfiguracija:**
```
.github/workflows/ci.yml
```

---

## Pokretanje testova

### Lokalno
```bash
mvn test
```

Pokretanje s Firefox preglednikom:
```bash
mvn test -Dbrowser=firefox
```

Pokretanje u headless modu:
```bash
mvn test -Dheadless=true
```

### Testni izvještaji
```bash
mvn surefire-report:report
```

Izvještaj se nalazi u:
```
target/site/surefire-report.html
```

---
