# Beschreibung
Mit hilfe dieses Programms ist es möglich den kürzesten Pfad zwischen zwei ausgewählten Städten aus der Datenbank zu finden. Der Pfad wird nach dem Dijkstra Algorithmus berechnet.

<p align="center">
  <img src="https://github.com/tetiana-w/dijkstra-algorithm/blob/master/screenshot_project_example.jpg" width="450" title="Screenshot">
</p>

Mit dem Dijkstra Algorithmus ist es möglich in einem Graphen den kürzesten Pfad zwischen dem Startknoten und allen anderen Knoten zu berechnen. Zu Testzwecken wurden mehrere deutsche Städte als Knotenpunkte eines Graphen und die Distanzen zwischen den Städten als Kanten verwendet. Eine schematische Darstellung ist in der folgenden Abbildung zu sehen.

<p align="center">
   <img src="https://github.com/tetiana-w/dijkstra-algorithm/blob/master/cities_network.jpg" width="450" title="Deutsche Städte und Distanzen zwischen den Städten">  
</p>

# Projekt starten
Benötigt wird:
- Java SE DK 13
- Eclipce IDE
- XAMPP v3.2.4

<ol>
  <li> Archiv algorithm_project_dijkstra.zip in Eclipce importieren (File - Import - Existing Project into Workspace - Select archive file - Finish)</li>
  <li> Apache und MySQL in XAMPP starten</li>
  <li> cities.sql in phpMyAdmin (MySQL) importieren (Import - Choose File - Go)</li>
</ol>

<b>Außerdem den Code zum Projekt ist im 
<a href="https://github.com/tetiana-w/dijkstra-algorithm/tree/master/src">"src" Verzeichnis</a> 
und das JavaDoc im 
<a href="https://github.com/tetiana-w/dijkstra-algorithm/tree/master/doc">"doc" Verzeichnis</a>
zu finden. </b>
