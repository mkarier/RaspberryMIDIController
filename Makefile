jarFileName = 'RaspberryPiMIDIController'

RaspberryPieMIDIController:
	javac java/src/*.java java/src/*/*.java -d java/bin/

BuildAll:
	javac java/src/*.java java/src/*/*.java -d ./
	jar cvfe $(jarFileName).jar SoundController SoundController.class ./*/*.class && chmod a+x $(jarFileName).jar
	rm SoundController.class ./*/*.class
	rmdir ./*

