# Tamagotchi [DBJLLMJK]
Projekt końcowy gry Tamagotchi na przedmiot Zaawansowane Zagadnienia Programowania w Javie.

## Uruchomienie
Program można zbudować i uruchomić poprzez maven:
```bash
mvn clean package exec:java
```
Lub uruchomić od razu poprzez komendę:
```bash
mvn exec:java
```
## Testy mutacyjne
Zrealizowane przy pomocy PITEST, można uruchomić za pomocą komendy:
```bash
mvn org.pitest:pitest-maven:mutationCoverage
```
Po wykonaniu w katalogu target/pit-reports znajdują się raporty.

## Skład zespołu
* Łukasz Miedziński (195659) [Przydział zadań, Kod główny aplikacji, Testy mutacyjne]
* Jacek Lewański (195643) [Java 1.8, Kod główny aplikacji]
* Damian Bartosik (195559) [Testy jednostkowe]
* Jakub Krysiak (195634) [Testy jednostkowe, Testy Mockito]

## Prowadzenie repozytorium i projektu
Projekt prowadzony jest przy wykorzystaniu issues na Githubie.
Wszystkie zmiany w kodzie odbywają się na osobnych branchach i kończą się pull requestem do mastera.
Każda zmiana jest również monitorowana przez zastosowanie continuous integration (Travis-CI).

## Dodatkowe punkty
1. Wykorzystanie RxJava na przykładzie tablicy lotów Schiphol
[https://github.com/miedzinski195659/schiphol]
2. Wykorzystanie Spring Boot na przykładzie api do wstawiania oraz czytania tweetów z MongoDB
[https://github.com/miedzinski195659/tweetTest]
