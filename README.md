# 1. Specyfikacja projektu

## 1. Opis programu / systemu
* Cel projektu
Celem projektu jest utworzenie aplikacji desktopowej z interfejsem graficznym, pozwalającej na wizualizację i eksplorację danych przy pomocy wybranych metod data mining z hurtowni dotyczącej kursów i opłat korporacji TAXI.
* Zakres projektu
Aplikacja desktopowa napisana w języku Java z wykorzystaniem JavaFX, hurtownia danych w postgreSQL, biblioteki WEKA. System ma w swoim zakresie objąć komunikację z hurtowni danych dotyczącej kursów i opłat korporacji TAXI i przetwarzaniem danych w niej zawartych, za pomocą parametryzowanych zapytań hurtowniczych, jak i też ma wykorzystywać metody eksploracji danych, zawartych w bibliotece WEKA. Przetworzone dane mają być przedstawione w graficznym interfejsie użytkownika. 
* Główny problem rozwiązywany w aplikacji
Głównym problemem rozwiązywanym w aplikacji będzie sprawdzanie czy dany kierowca dopuszcza się oszukiwania na cenach oferowanych przejazdów, tj. nalicza za dużo lub za mało za dany kurs.

## 2. Wymagania stawiane aplikacji / systemowi
* System powinien współpracować z hurtownią danych
* System powinien mieć kilka modułów
  * Moduł wizualizacji danych
  * Moduł do wykonywania zapytań
  * Moduł połączenia się z bazą danych
  * Moduł logowania
* System powinien mieć intuicyjne gui
       
## 3. Panele / zakładki systemu, które będą oferowały potrzebne funkcjonalności
* Panel wizualizacji hurtowni
  * Wyświetlanie danych w postaci tabel.
  * Zyski z kursów: przedstawia zyski wybranego kierowcy, gdzie nazwa kierowcy jest parametrem zapytania hurtowniczego.
  * Zestawienie kierowców: przedstawia zestawienie kierowców w wybranym miesiącu z których, posiada największe zyski z kursów.
  * Zestawienie miast: przedstawia zestawienie miast, z których, posiada największą ilość klientów w danym miesiącu.
* Panel eksploracji danych
  * Zbadanie dany kierowca jest oszustem czy nie. Czyli czy nie nalicza dużej kwoty za krótki kurs, zastosowanie nieodpowiedniej stawki 
* Panel logowania
  * Logowanie do systemu

## 2. Diagramy UML
* Diagram przypadków użycia

![Diagram przypadków użycia](https://raw.githubusercontent.com/CJay2k/taxi_hurtownia-jfx/master/Dokumentacja/uml.png)

* Diagram aktywności

![Diagram aktywności](https://raw.githubusercontent.com/CJay2k/taxi_hurtownia-jfx/master/Dokumentacja/aktywnosci.png)

* Diagram sekwencji

![Diagram sekwencji](https://raw.githubusercontent.com/CJay2k/taxi_hurtownia-jfx/master/Dokumentacja/sekwencji.png)

## 3. Hurtownia danych
* Diagram ERD

![Diagram ERD](https://raw.githubusercontent.com/CJay2k/taxi_hurtownia-jfx/master/Dokumentacja/erd.png)

## 4. Wykorzystane technologie
* Język Java 8
  * Współbieżny, oparty na klasach, obiektowy język programowania ogólnego zastosowania. Java jest językiem tworzenia programów źródłowych kompilowanych do kodu bajtowego, czyli postaci wykonywanej przez maszynę wirtualną. Język cechuje się silnym typowaniem. Jego podstawowe koncepcje zostały przejęte z języka Smalltalk oraz z języka C++.
* Baza danych PostgreSQL
  * PostgreSQL często nazywany także Postgres to, obok MySQL i SQLite, jeden z trzech najpopularniejszych otwartych systemów zarządzania relacyjnymi bazami danych. Początkowo opracowywany na Uniwersytecie Kalifornijskim w Berkeley i opublikowany pod nazwą Ingres
* GUI JavaFX
  * JavaFX – rodzina technologii i produktów firmy Sun Microsystems, przeznaczonych głównie do tworzenia Rich Internet Application. W skład JavaFX wchodzi język skryptowy JavaFX Script oraz system dla urządzeń mobilnych Java ME. Język JavaFX Script ma w założeniu stać się konkurentem dla Adobe Flash i Flex, technologii AJAX oraz Microsoft Silverlight
* WEKA
  * Waikato Environment for Knowledge Analysis, opracowane na University of Waikato, Nowa Zelandia. Jest to wolne oprogramowanie na licencji GNU General Public Licence oraz oprogramowanie towarzyszące książce „Data Mining: Practical Machine Learning Tools and Techniques”
## 5. Interfejs aplikacji / systemu

* Logowanie

![Logowanie](https://raw.githubusercontent.com/CJay2k/taxi_hurtownia-jfx/master/Dokumentacja/logowanie.png)

* Zakładka: Zyski z kursów

![Zakładka: Zyski z kursów](https://raw.githubusercontent.com/CJay2k/taxi_hurtownia-jfx/master/Dokumentacja/zyski_z_kursow.png)

* Zakładka: Zestawienie kierowców

![Zakładka: Zestawienie kierowców](https://raw.githubusercontent.com/CJay2k/taxi_hurtownia-jfx/master/Dokumentacja/zestawienie_kierowcow.png)

* Zakładka: Zestawienie miast

![Zakładka: Zestawienie miast](https://raw.githubusercontent.com/CJay2k/taxi_hurtownia-jfx/master/Dokumentacja/zestawienie_miast.png)

* Zakładka: Potencjalni oszuści

![Zakładka: Potencjalni oszuści](https://raw.githubusercontent.com/CJay2k/taxi_hurtownia-jfx/master/Dokumentacja/potencjalni_oszusci.png)
