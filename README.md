# Automatizirano testiranje – DemoQA

Ovaj projekt predstavlja okvir (framework) za automatizirano testiranje korisničkog sučelja (UI), izrađen u sklopu kolegija iz testiranja programske podrške.

Testovi su razvijeni nad javno dostupnom demo aplikacijom [demoqa.com](https://demoqa.com) s ciljem demonstracije tehnika automatiziranog testiranja, arhitekture testnog frameworka i rada sa Selenium WebDriverom i TestNG-om.

---

## Napomena o stabilnosti testova

Aplikacija DemoQA je demonstracijska (demo) aplikacija i poznata je po povremenoj nestabilnosti.

Zbog toga:

- during pokretanja cijelog testnog paketa može povremeno pasti manji broj testova
- isti testovi u pravilu prolaze kada se pokreću pojedinačno
- padovi testova najčešće su uzrokovani:
    - privremenim mrežnim problemima (network error)
    - sporim ili nepotpunim učitavanjem stranice
    - dinamičkim sadržajem i preklapajućim (overlay) elementima
    - headless načinom rada preglednika

Ovo ponašanje je očekivano za demo aplikaciju i nije pokazatelj loše implementacije testova, već realan primjer problema s kojima se susreće UI automatizacija.

---

## Korištene tehnologije i alati

- Java 21
- Selenium WebDriver 4
- TestNG
- Maven
- WebDriverManager
- Firefox (primarni preglednik)
- Chromium (lokalno, po potrebi)
- CI/CD Actions
- Git i GitHub

---

## Arhitektura projekta

Projekt je implementiran prema Page Object Model (POM) arhitekturnom obrascu, čime se odvaja testna logika od UI implementacije.

```
│
├── .github
│ └── workflows
│ └── ci.yml
│
├── src
│ ├── main
│ │ └── java
│ │ ├── config
│ │ │ ├── Config.java
│ │ │ └── ConfigLoader.java
│ │ │
│ │ ├── driver
│ │ │ ├── BrowserType.java
│ │ │ └── DriverFactory.java
│ │ │
│ │ ├── pages
│ │ │ ├── base
│ │ │ │ └── BasePage.java
│ │ │ │
│ │ │ ├── alerts
│ │ │ │ ├── AlertsPage.java
│ │ │ │ ├── BrowserWindowsPage.java
│ │ │ │ └── FramesPage.java
│ │ │ │
│ │ │ ├── elements
│ │ │ │ ├── ButtonsPage.java
│ │ │ │ ├── CheckBoxPage.java
│ │ │ │ ├── LinksPage.java
│ │ │ │ ├── RadioButtonPage.java
│ │ │ │ ├── TextBoxPage.java
│ │ │ │ └── WebTablesPage.java
│ │ │ │
│ │ │ ├── forms
│ │ │ │ └── PracticeFormPage.java
│ │ │ │
│ │ │ └── upload
│ │ │ └── UploadDownloadPage.java
│ │ │
│ │ └── utils
│ │ └── WaitUtil.java
│ │
│ └── resources
│
├── src
│ └── test
│ └── java
│ ├── listeners
│ │ ├── RetryAnalyzer.java
│ │ └── RetryListener.java
│ │
│ ├── tests
│ │ ├── base
│ │ │ └── BaseTest.java
│ │ │
│ │ ├── alerts
│ │ │ ├── AlertsTests.java
│ │ │ ├── BrowserWindowsTests.java
│ │ │ └── FramesTests.java
│ │ │
│ │ ├── elements
│ │ │ ├── ButtonsTests.java
│ │ │ ├── CheckBoxTests.java
│ │ │ ├── LinksTests.java
│ │ │ ├── RadioButtonTests.java
│ │ │ ├── TextBoxTests.java
│ │ │ └── WebTablesTests.java
│ │ │
│ │ ├── forms
│ │ │ └── PracticeFormTests.java
│ │ │
│ │ └── upload
│ │ └── UploadDownloadTests.java
│ │
│ └── resources
│ ├── config.properties
│ └── log4j.properties
│
├── testng.xml
├── pom.xml
└── README.md
```

---

## Ključne značajke frameworka

### Page Object Model

- jasna podjela između testova i UI logike
- jednostavno održavanje i proširivanje testova
- poštivanje objektno-orijentiranih (OOP) načela

### Explicit waits

- implicit waits su isključeni
- sva čekanja su centralizirana kroz `WaitUtil`
- koristi se čekanje na vidljivost, prisutnost i tekst elemenata

### Retry mehanizam

- implementiran pomoću TestNG `RetryAnalyzer` i `RetryListener`
- omogućuje automatsko ponovno izvođenje flaky testova
- retry je konfiguriran globalno

### Safe open mehanizam

- stranice se otvaraju koristeći sigurnu metodu (`safeGet`)
- omogćuje retry u slučaju privremenog network problema
- smanjuje broj lažnih padova testova

### Cross-browser podrška

- Firefox (primarni)
- Chromium (lokalno, ovisno o konfiguraciji)
- podržan headless i non-headless način rada

---

## Pokretanje testova

**Pokretanje svih testova (Firefox, headless):**

```bash
mvn clean test -Dbrowser=firefox -Dheadless=true
```

**Pokretanje testova s vidljivim preglednikom:**

```bash
mvn clean test -Dbrowser=firefox -Dheadless=false
```

**Pokretanje pojedine testne klase:**

```bash
mvn -Dtest=PracticeFormTests test
```

**Pokretanje pojedinog testa:**

```bash
mvn -Dtest=PracticeFormTests#happyPath_fullForm_shouldShowModal test
```

### TestNG konfiguracija

Testovi su grupirani pomoću TestNG grupa (npr. `smoke`, `regression`) i mogu se pokretati putem `testng.xml` datoteke, Maven komandi ili pojedinačno iz IDE-a.

---

## Zaključak

Ovaj projekt prikazuje primjer UI automatizacije nad nestabilnom demo aplikacijom. Fokus je stavljen na dobru arhitekturu testova, korištenje explicit waits mehanizama, upravljanje flaky testovima te realne probleme s kojima se susreće UI automatizacija.

Cilj projekta nije forsirati savršenu stabilnost nad demo aplikacijom, već demonstrirati ispravne prakse automatiziranog testiranja.

---

## Autor

**Nikola Iljazović**  
GitHub: [niljazovic](https://github.com/niljazovic)
