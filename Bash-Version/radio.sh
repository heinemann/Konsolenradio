#!/bin/bash
############################################################
# Adrian Heinemann <mail@heinemann.ms> Homepage: http://heinemann.ms ©
# Letzte Änderung: Fri Dez 23 10:30:00 CET 2010
#
#Dieses Skript wird unter den Bedingungen der 
#Deutsche Freie Software Lizenz veröffentlicht. 
#http://www.d-fsl.org/
############################################################
# Dieses Kript spielt den Radiostream von diversen Internetradiosendern  über die Konsole ab. 
# Das Programm mpg123 und mplayer wird hier für benötigt. 
#012345678901234567890123456789012345678901234567890123456789012345678901234567890


##Globale Variablen
schalter=0
SENDER=" wählen ein Radiosender aus"
SENDER_WAHL="Gebe bitte die Nummer vor dem Sender oder den Servicecode an."
radio_debug=0
sender_name="void"
stream_url="void"
stream_format="void"
stream_pid=999999

#exec 1>/dev/null 


##Funktionen
######################################
##################################################
#Diese Funktion startet den Stream
play(){
kill $stream_pid
if [ $stream_format = "mp3" ] ; then
	        screen -dmS PLAYER mpg123 $stream_url  &
		stream_pid=$(pidof mpg123)
		echo $stream_pid
 else

  if [ $stream_format = "asx" ] ; then
	        screen -dmS PLAYER mplayer -playlist $stream_url &
		stream_pid=$(pidof mplayer)
 else
  echo "Radio schaltet sich aus."
	  
  fi
fi
#sendersuche
}

##################################################
#Radio Ausschalten
ausschalten(){
if [$radio = "aus"|"Aus"|"0"] ; then
 ((schalter++))
fi
}

#Prozess ID der Programme in Variable ablegen
prozessid(){
if [ $stream_format = "mp3" ] ; then
		stream_pid=$(pidof mpg123)
 else

  if [ $stream_format = "asx" ] ; then
		stream_pid=$(pidof mplayer)
 else
  echo "Bitte wähle einen Sender aus!(Kein Format angegeben)."
	  
  fi
fi
}

#Die Funktion Sendersuche bietet eine kleine Auswahl von Internetradioprogrammen an. 
sendersuche(){
clear	
echo ""
echo -e '\E[37;44m' "			Das Konsolenradio"
tput sgr0
echo ""
echo  "			Hallo $USER,$SENDER. "
echo  ""
echo  ""
echo  "	1- WDR-1Live	2-WDR2 		3-WDR3		4-WDR4"
echo  "	5- RadioQ	6-Antenne-MS	7-BBC1		8-N-Joy"
echo ""
echo  "		0-Aus (Radio schaltet sich aus.)"
echo  "		S-Sleepfunktion"
echo  "		P-Pause (Sender ausschalten, aber das Skript nicht verlassen)"
echo  "		D (Debug Informationen)"
echo  "		DH (Verstecke Debug Informationen)"
echo  "		URL (URL und Format eines Streams angeben und abspielen)"
echo ""
echo ""
echo " $SENDER_WAHL "
echo ""


if [ $radio_debug -eq 1 ] ; then
screen -ls
echo "PID: $stream_pid"
echo "Format: $stream_format"
else echo ""
fi
read radio
SENDER_WAHL="Zum wechseln des Senders, bitte die Nummer vor dem Sender eingeben oder den Servicecode auswählen."
echo""
#echo "Das Radio ausschalten kannst du die Eingabe von  0-Aus"

case $radio in
#WDR Sender
	WDR-1Live*|1*|einslive*) SENDER=" du hörst Radio 1Live" stream_format="mp3"; stream_url=" http://gffstream.ic.llnwd.net/stream/gffstream_stream_wdr_einslive_b" play ;;
	WDR2*|2*) SENDER=" du hörst WDR 2 Münsterland" stream_format="mp3"; stream_url=" http://gffstream.ic.llnwd.net/stream/gffstream_mp3_w97a" play ;;
	WDR3*|3*) SENDER=" du hörst WDR 3" stream_format="mp3"; stream_url=" http://gffstream.ic.llnwd.net/stream/gffstream_w21a" play ;;
	WDR4*|4*) SENDER=" du hörst WDR 4" stream_format="mp3"; stream_url=" http://gffstream.ic.llnwd.net/stream/gffstream_w18a" play ;;

#RadioQ - Campusradio der Westfälischen Wilhelms-Universität
	RadioQ*|5*) SENDER=" du hörst RadioQ, das Campusradio" stream_format="mp3"; stream_url=" http://radioq.uni-muenster.de:8000/beta" play ;;

#Radio Antenne Münster (Lokalradio)

	6*|Antenne*|Antenne-MS*|Antenne-Münster*) SENDER=" du hörst Radio Antenne Münster" stream_format="mp3"; stream_url=" http://stream.antennemuenster.de:8000/am128k" play ;;

#BFBS Radio 1
	BBC1*|7*)  SENDER=" du hörst BBC Radio 1" stream_format="asx"; stream_url="  http://www.bbc.co.uk/radio/listen/live/r1.asx" play ;;

#NDR N-Joy
	N-Joy*|8*) SENDER=" du hörst NDR N-Joy" stream_format="mp3"; stream_url=" http://sc20.frf.llnw.net:80/stream/ndrstream_n-joy_hi_mp3" play ;;

#Radio Ausschalten
	aus*|Aus*|0*) ((schalter++)); kill $stream_pid;;

#Sleepfunktion
	S*|s*|sleep*|Sleep*) echo -e "\n Nach wieviel Sekunken soll das Radio ausgehen?"; read SEK ; sleep $SEK ; ((schalter++)); kill $stream_pid;; 

#Pause
	P*|p*|pause*) SENDER=" bitte wähle einen Sender aus!" stream_format=""; stream_url="" kill $stream_pid ;;

#Debug-Mode
	D*|d*) ((radio_debug++));;

#Hide-Debug-Mode
	D*|d*) ((radio_debug--));;

#MP3 Stream abspielen
	URL*|url*) SENDER=" du hörst einen eigenen Radiostream" echo " Bitte gibt die URL an:";read stream_url; echo "Format (mp3 oder asx):" ; read stream_format; play ;;
	
	
#Falsche eingabe  
	*) echo Der Sender stehtl leider nicht zur verfügung oder ihre Eingabe war fehlerhaft. ;;    
 esac



}

##################################################
#echo "Jetzt kommt die While schleife, welche die Funktion Sender ausführt, bis die Variable schalter = 1 ist."
radio(){
while [ $schalter -lt 1 ]
do 
sendersuche
prozessid
done
}

##################################################
# Überprügung ob die Streamplayer installiert sind
abhaengigkeiten(){
if  command -v mpg123 >/dev/null 2>&1 ; then ((schalter++))
else echo "Hallo $USER, bitte installiere das Programm \"mpg123\" und \"mplayer\", den ohne diese beiden Programme läuft dieses Skript leider nicht."
fi

if command -v mplayer >/dev/null 2>&1 ; then ((schalter++))
else echo "Hallo $USER, bitte installiere das Programm \"mpg123\" und \"mplayer\", den ohne diese beiden Proramme läuft dieses Skript leider nicht."
fi

if command -v screen >/dev/null 2>&1 ; then ((schalter++))
else echo "Hallo $USER, bitte installiere das Programm \"mpg123\" und \"mplayer\", den ohne diese beiden Proramme läuft dieses Skript leider nicht."
fi
}

##Radio starten
abhaenigkeiten
radio
echo "Turn OFF"
tput sgr0 
echo "Radio ist aus."
exit 0
