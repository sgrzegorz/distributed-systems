import sys
import Ice
from read_console import *
Ice.loadSlice('../config.ice')
import House


class Device:
    def __init__(self, communicator, id):
        self.id = id
        self.adapter = communicator.stringToProxy("{0}:default -h localhost -p 10000".format(id))
    def turnOn(self): # just to paste this code to child class of Device
        return self.proxy.turnOn()
    def turnOff(self):
        return self.proxy.turnOff()
    def getState(self):
        return self.proxy.getState()

class Calculator(Device):
    def __init__(self,communicator,id):
        super(Calculator,self).__init__(communicator,id)
        proxy = House.CalculatorPrx.checkedCast(self.adapter)
        if not proxy:
            raise RuntimeError("Invalid proxy")
        self.proxy = proxy
    def add(self, x, y):
        return self.proxy.add(x, y)
    def subtract(self, x, y):
        return self.proxy.subtract(x, y)
    def divide(self,x,y):
        return self.proxy.divide(x, y)
    def square(self,x):
        return self.proxy.square(x)
    def squareRoot(self,x):
        return self.proxy.squareRoot(x)

class Fridge(Device):
    def __init__(self,communicator,id):
        super(Fridge,self).__init__(communicator,id)
        proxy = House.FridgePrx.checkedCast(self.adapter)
        if not proxy:
            raise RuntimeError("Invalid proxy")
        self.proxy = proxy
    def getTemperature(self):
        return self.proxy.getTemperature()
    def setTemperature(self,temperature):
        return self.proxy.setTemperature(temperature)

class TiltCamera(Device):
    def __init__(self,communicator,id):
        super(TiltCamera,self).__init__(communicator,id)
        proxy = House.TiltCameraPrx.checkedCast(self.adapter)
        if not proxy:
            raise RuntimeError("Invalid proxy")
        self.proxy = proxy
    def setScreen(self,position):
        return self.proxy.setScreen(position)
    def getScreen(self):
        return self.proxy.getScreen()

class ZoomCamera(Device):
    def __init__(self,communicator,id):
        super(ZoomCamera,self).__init__(communicator,id)
        proxy = House.ZoomCameraPrx.checkedCast(self.adapter)
        if not proxy:
            raise RuntimeError("Invalid proxy")
        self.proxy = proxy
    def getScreen(self):
        return self.proxy.getScreen()
    def zoom(self):
        return self.proxy.zoom()

def listAll(communicator):
    adapter = communicator.stringToProxy("all/list:default -h localhost -p 10000")
    proxy = House.ListDevicesPrx.checkedCast(adapter)
    if not proxy:
        raise RuntimeError("Invalid proxy")
    print(proxy.listAll())

if __name__ =="__main__":
    with Ice.initialize(sys.argv) as communicator:

        read_console(communicator)

        # listAll(communicator)
        # no_reply()
        # calculator = Calculator(communicator,"calc/1")
        # print(calculator.turnOn())
        # fridge = Fridge(communicator,"fridge/1")
        # print(fridge.setTemperature(6))
        # print(fridge.getTemperature())
        # camera = TiltCamera(communicator,"tiltCamera/1")
        # camera.getScreen()

        # camera = ZoomCamera(communicator,"zoomCamera/1")
        # camera.getScreen()

        # adapter = communicator.stringToProxy("calc/1:default -h localhost -p 10000")
        # proxy = House.CalculatorPrx.checkedCast(adapter)
        # if not proxy:
        #     raise RuntimeError("Invalid proxy")
        # p =proxy.add(4,5)
        # print(p)

