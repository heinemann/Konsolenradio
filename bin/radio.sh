#!/bin/bash
############################################################
# Adrian Heinemann <mail@heinemann.ms> Homepage: http://heinemann.ms ©
# Letzte Änderung: Mon Mär 21 44:30:00 CET 2011
#
#Dieses Skript wird unter den Bedingungen der 
#Deutsche Freie Software Lizenz veröffentlicht. 
#http://www.d-fsl.org/
############################################################
# Dieses Skript startet das Konsolenradio (JVM-Edition) um  Internetradiosendern  über die JavaVirtuelMachine zu hören. 
# Java wird hier für benötigt. 
#012345678901234567890123456789012345678901234567890123456789012345678901234567890

Schalter=0

abhaenigkeiten(){
if  command -v java >/dev/null 2>&1 ; then ((Schalter++))
else echo "Hallo $USER, bitte installiere  \"Java\"."
fi
}

radio(){
if [ $Schalter -eq 1 ];then
java -jar Radio.jar
fi
}

##Radio starten
echo "Überprüfe Abhängigkeiten"
abhaenigkeiten
echo "Starte das Konsolenradio"
radio;
exit 0