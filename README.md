# Tamagotchi
Projekt gry Tamagotchi na przedmiot Zaawansowane Zagadnienia Programowania w Javie

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