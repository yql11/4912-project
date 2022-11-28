import pyrebase
import time
import board
import adafruit_dht
from gps import *
from time import sleep
from picamera2 import Picamera2 ,Preview
import sys
import threading

firebaseConfig ={'apiKey': "AIzaSyB5vs12I-kqPSMJUSXA9b1oQS56mUdWqe4",
  'authDomain': "smart-farm-21a6e.firebaseapp.com",
  'databaseURL': "https://smart-farm-21a6e-default-rtdb.firebaseio.com",
  'projectId': "smart-farm-21a6e",
  'storageBucket': "smart-farm-21a6e.appspot.com",
  'messagingSenderId': "341088203815",
  'appId': "1:341088203815:web:b67408f1bbaa5d41d68a02",
  'measurementId': "G-HW96W0LTSY"} # config the firebase connection


firebase = pyrebase.initialize_app(firebaseConfig)# initialize the Web app

db = firebase.database()# initialize the database


imageStorage = firebase.storage() # initialize  the storage
dhtDevice = adafruit_dht.DHT11(board.D17)# grab data from DHT11 Sensor
gpsd = gps(mode = WATCH_ENABLE|WATCH_NEWSTYLE)# grab data and setting the gps module(BN-180)
#reading the temperature & Temperature
def getTemperatureHumidity():
    i=0
    while True:
        try: 
            temperature_c = dhtDevice.temperature
            temperature_f = temperature_c * (9 / 5) + 32
            humidity = dhtDevice.humidity
            print("Temp: {:.1f} F / {:.1f} C    Humidity: {}% ".format(temperature_f, temperature_c, humidity))
            data = {"Temperture_c":temperature_c,"Temperature_f": temperature_f, "Humidity":humidity}
            db.child("Temperture&Humidity").child('currentT&H').set(data)
            db.child("Temperture&Humidity").child('passedT&H'+str(i)).set(data)
            i +=1
            time.sleep(1.0)
        except RuntimeError as error:
            print(error.args[0])
            time.sleep(2.0)
        except Exception as error:
            dhtDevice.exit()
            raise error
        except(KeyboardInterrupt):
            print("Thread1 end")
        
#reading the GPS info
def getGPS():
    
    j =0
    while True:
        try:
            nx = gpsd.next()
            if nx['class'] == 'TPV':
                latitude = getattr(nx, 'lat', 'Unknown')
                longtitude = getattr(nx, 'lon', "Unknown")
                print("Your location: latitude ="+ str(latitude)+", longtitude ="+ str(longtitude))
                data = {"Latitude":latitude, "Longtitude": longtitude}
                db.child("GPS").child("currentGPS").set(data)
                db.child("GPS").child("passedGPS"+str(j)).set(data)
                j += 1
                time.sleep(1.0)
        except(KeyboardInterrupt):
            print("Thread2 end")

def getImage():
    k = 0 
    while True:
        try: 
            camera = Picamera2()# initialize the Camera
            camera_config= camera.create_preview_configuration({"size":(1920,1080)})
            camera.configure(camera_config)
            camera.start_preview(Preview.QT,x=1000, y=200, width=800,height= 600)
            camera.start()
            time.sleep(10)
            camera.capture_file('text.jpg')
            camera.stop_preview()
            camera.close()
            imageStorage.child('currentImage').put('text.jpg')
            imageStorage.child('pastImage'+str(k)).put('text.jpg')
            k +=1 
            time.sleep(10)
        except(KeyboardInterrupt):
            print("Thread3 end")



def main():
    t1 = threading.Thread(target=getTemperatureHumidity,name="temperature&humidity")
    t2 = threading.Thread(target=getGPS, name="GPS")
    t3 = threading.Thread(target=getImage,name="Image") 

    t1.start()
    t2.start()
    time.sleep(5)
    t3.start()
    t1.join()
    t2.join()
    t3.join()

if __name__ == "__main__":

    main()














