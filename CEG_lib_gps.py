from gps import *

import time

running = True
def getPositionData(gps):
    nx = gpsd.next()
    if nx['class'] == 'TPV':
        latitude = getattr(nx, 'lat', 'Unknown')
        longtitude = getattr(nx, 'lon', "Unknown")
        print("Your location: latitude ="+ str(latitude)+", longtitude ="+ str(longtitude))

gpsd = gps(mode = WATCH_ENABLE|WATCH_NEWSTYLE)

try: 
    print("Application started!")
    while running:
        getPositionData(gpsd)
        time.sleep(1.0)
except(KeyboardInterrupt):
    running =False
    print("Applicaion end")
    