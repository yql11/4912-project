from time import sleep
from picamera2 import Picamera2 ,Preview

import sys
camera = Picamera2()
camera_config= camera.create_preview_configuration({"size":(1920,1080)})
camera.configure(camera_config)

# camera.resolution=(720,480)
# camera.preview_fullscreen=False
# #camera.vflip = True
# #camera.hflip = True
# camera.preview_window=(2000,200,720,480)
camera.start_preview(Preview.QTGL,x=1000, y=200, width=800,height= 600)
camera.start()
sleep(20)
camera.stop_preview()
camera.close()

            