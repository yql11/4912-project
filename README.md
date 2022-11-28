# 4912-project

The Rasberry Pi 4 part of the project require the use of Pyhton 3.9.2

some third-party libraries need to be pre-install into the coding enviroment

Here are the command need to run after you have installed python3 and pip:

For GPS  module:

	sudo apt-get install minicom -y
	sudo apt-get install screen
	sudo apt-get install gpsd-clients gpsd -y

Don't forget change the configuration

  	sudo nano /etc/default/gpsd:

  Look for DEVICES=""

  change into DEVICES="/dev/serial0"

  reboot

autostart:

	sudo systemctl enable gpsd.socket
	sudo systemctl start gpsd.socket

  	sudo gpsd /dev/serial0 -F /var/run/gpsd.socket

For DHT11 sensor:

	pip3 install adafruit-circuitpython-dht
	sudo apt-get install libgpiod2

For Pi camera:

  	sudo apt install -y python3-libcamera python3-kms++
  	sudo apt install -y python3-prctl libatlas-base-dev ffmpeg python3-pip
	sudo apt install -y python3-pyqt5 python3-opengl # only if you want GUI features
  	pip3 install numpy --upgrade
	
	pip3 install picamera2




