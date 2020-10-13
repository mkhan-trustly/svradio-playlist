Project
========================================================================================================================
Assignment

Start guide
========================================================================================================================
The following needs to be installed in order to run the application.

* Java 11
* Maven
* Git

Use your desired IDE to open the project for example IntelliJ

Running locally 
========================================================================================================================
Using IDE:> Run `SvRadioApplication.java `

Using terminal:> `mvn clean install && mvn spring-boot:run`

### Test
Using terminal:> `mvn clean test`

Task
========================================================================================================================

Din uppgift är skriva en liten applikation, som ansluter mot Sveriges Radios publika API ( https://sverigesradio.se/oppetapi ), 
hämta ut en kanals låtlista för ett visst tidsintervall (helt ok med ett hårdkodat datumintervall, hårdkoda max antal låtar till 100,  
och en hårdkodad radiokanal, förslagsvis P3). Därefter ska ett svar levereras som är uppdelat på skivbolag, i bokstavsordning, 
samt vilka artister som ligger under respektive skivbolag.

Applikationen behöver inte vara deployad men ska vara möjlig att starta.
Ramverk etc. är frivilligt, liksom hur svaret levereras till slutanvändaren.
Informationen är inte så detaljerad men gör egna antaganden om hur det ska fungera.

Informationen är inte så detaljerad men gör egna antaganden om hur det ska fungera.

Solution
========================================================================================================================

The idea is to build a front-end to this application, so a RESTful service shall deliver the content. The part of 
the code that actually solves the task is the following. 

```
playlist.getSongs()
                 .collect(Collectors.groupingBy(Song::getRecordLabel, TreeMap::new,
                         Collectors.mapping(Song::getArtist, Collectors.toSet())));
```
TreeMap ensures sorted items and artists are collected in Set to avoid duplicates.

### REST API Services
Service to fetch the playlist grouped by record-label (alphabetically sorted)
`curl http://localhost:8080/api/v1/channel/p3/playlist/recordlabels/artist`

Two additional services to demonstrate RESTful services

* GET Service to fetch all record labels grouped by record-label
`curl http://localhost:8080/api/v1/channel/p3/playlist/recordlabels`

* A specific record label can be fetched by 
`curl http://localhost:8080/api/v1/channel/p3/playlist/recordlabels/<record-label>`

Reference source
========================================================================================================================
https://sverigesradio.se/api/documentation/v2/metoder/musik.html

Out of scope and improvements
========================================================================================================================
* Caching is out of scope for this assignment otherwise Hazelcast or other similar technology can be used for caching.
* RecordLabel, Album, Artist are can be different types instead of String class
* At some places the new 'Record' can be used, especially with RecordLabel, Album, Artist instead of class (@Lombok) 
