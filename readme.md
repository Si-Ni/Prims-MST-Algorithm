- verwendete IDE: IntelliJ IDEA
- verwendete Programmiersprache: Java (JDK 17)
- ausführbares Programm befindet sich im Ordner: build
- Quellcode zu dem Programm befindet sich im Ordner: src

Programmstart:
- Die Ausführung des Java-Programms setzt voraus, dass auf dem PC eine Java (JRE) Version 17 oder höher installiert ist. Sollte das noch nicht der Fall sein, kann hier eine aktuelle JDK-Version heruntergeladen werden: https://www.oracle.com/de/java/technologies/downloads/

- Das Programm kann durch das Doppelklicken auf die Datei primMST.exe gestartet werden. Sollte ein Antivirenprogramm, wie der Windows Defender oder ähnliches, den Start verhindert, so ist „trotzdem ausführen“ auszuwählen. Eventuell ist vorher ein Klick auf „weitere Informationen“ notwendig.

- Eine alternative Möglichkeit, um das Programm zu starten, besteht darin, die Konsole zu nutzen. Hierzu muss man sich mit der Konsole im Verzeichnis der Datei befinden und anschließend folgenden Befehl eingeben: ```java -jar primMST.jar```

- Anschließend wird dem Nutzer das Format der Textdatei erläutert, in welcher der zu benutzende Graph an das Programm übergeben werden muss. Durch das drücken der „Enter“-Taste öffnet sich ein JFileChooser, in dem der Nutzer die Datei auswählen muss. In diesem Fall handelt es sich dabei um die Datei MST_Prim1.txt.	
Sollte es dabei zu Problemen kommen, kann der FileChooser ohne die Auswahl einer Datei geschlossen werden, woraufhin das Programm in dem Verzeichnis, in welchem es sich befindet, nach einer Datei namens „MST_Prim1.txt“ sucht und diese, wenn vorhanden, nutzt. 
