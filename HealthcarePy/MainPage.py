import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic

import sys
import os
sys.path.append(os.path.dirname(os.path.abspath(__file__)))

from exercise import Dumbbell
from exercise import PushUp
from handTracking import Finger_counter
from PyQt5.QtGui import QPixmap



# UI파일 연결
form_class = uic.loadUiType("ui/MainMenu.ui")[0]

naxtPageFlag = 0

# 화면을 띄우는데 사용되는 Class 선언
class WindowClass2(QDialog, form_class) :
    def __init__(self, uid) :
        super().__init__()
        self.setupUi(self)
        self.uid = uid

        #self.fingerCountDef()

        self.fingerCount.clicked.connect(self.fingerCountDef)
        self.startDumbbelBtn.clicked.connect(self.startDumbbelWithFinger)
        self.logoutBtn.clicked.connect(self.logout)
        self.exitBtn.clicked.connect(self.exit)
        self.pushUpBtn.clicked.connect(self.startPushUpFuc)

	# 작동시킬 함수들 작성
    ''' ex)
    def 작동시킬함수(self):
    	print("함수작동")
        self.출력할위젯objectName값.setText(str(1))
     
    def 작동시킬함수2(self):
    	print("2함수작동")
        self.출력할위젯objectName값.setText(str(2))
    .
    .
    .
    '''
    def fingerCountDef(self):
        naxtPageFlag = Finger_counter.startFingerCount()
        print("nextPageFlag: ", naxtPageFlag)
        print(self.uid)
    
        if naxtPageFlag == 1:
            self.startDumbbelWithFinger()
        if naxtPageFlag == 2:
            self.startPushUpWithFinger()

    def startDumbbelWithFinger(self):
        print("dumbbell")
        Dumbbell.start(self.uid)
        self.fingerCountDef()

    def startPushUpWithFinger(self):
        print("pushUp")
        PushUp.start(self.uid)
        self.fingerCountDef()

    def startPushUpFuc(self):
         PushUp.start(self.uid)

    def logout(self):
        print("로그아웃")
        self.close()

    def exit(self): 
        exit()



if __name__ == "__main__" :
    app = QApplication(sys.argv) 
    myWindow = WindowClass2() 
    myWindow.show()
    app.exec_()