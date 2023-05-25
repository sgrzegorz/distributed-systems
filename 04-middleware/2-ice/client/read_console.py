import sys
from client import Calculator, Fridge, TiltCamera,ZoomCamera, listAll
import Ice

def read_console(communicator):


    while (True):
        try:

            c = sys.stdin.readline().strip()
            # c= "zoomCamera/1 add 4 5"
            words = c.split()
            if len(words)<2 or len(words)>4:
                printInfo()
                continue
            if words[0]=="all" and words[1]=="list":
                print("list")
                listAll(communicator)
                continue

            [category, name] = words[0].split("/")
            cmd = words[1]
            id =words[0]
            if category =="calc":
                calculator = Calculator(communicator, id)
                if cmd=="add":
                    print(calculator.add(int(words[2]),int(words[3])))
                elif cmd =="subtract":
                    print(calculator.subtract(int(words[2]), int(words[3])))
                elif cmd =="divide":
                    print(calculator.divide(int(words[2]), int(words[3])))
                elif cmd =="square":
                    print(calculator.square(int(words[2])))
                elif cmd =="squareRoot":
                    print(calculator.squareRoot(int(words[2])))
                elif cmd =="turnOn":
                    calculator.turnOn()
                elif cmd =="turnOff":
                    calculator.turnOff()
                elif cmd =="getState":
                    print(calculator.getState())
                else:
                    printInfo()
            elif category=="fridge":
                fridge = Fridge(communicator,id)
                if cmd == "getTemperature":
                    print(fridge.getTemperature())
                elif cmd == "setTemperature":
                    fridge.setTemperature(int(words[2]))
                elif cmd =="turnOn":
                    fridge.turnOn()
                elif cmd =="turnOff":
                    fridge.turnOff()
                elif cmd =="getState":
                    print(fridge.getState())
                else:
                    printInfo()
            elif category=="tiltCamera":
                tiltCamera = TiltCamera(communicator,id)
                if cmd == "getScreen":
                    print(tiltCamera.getScreen())
                elif cmd == "setScreen":
                    tiltCamera.setScreen(words[2])
                elif cmd == "turnOn":
                    tiltCamera.turnOn()
                elif cmd == "turnOff":
                    tiltCamera.turnOff()
                elif cmd == "getState":
                    print(tiltCamera.getState())
                else:
                    printInfo()
            elif category == "zoomCamera":
                zoomCamera = ZoomCamera(communicator,id)
                if cmd == "getScreen":
                    print(zoomCamera.getScreen())
                elif cmd == "zoom":
                    zoomCamera.zoom()
                elif cmd == "turnOn":
                    zoomCamera.turnOn()
                elif cmd == "turnOff":
                    zoomCamera.turnOff()
                elif cmd == "getState":
                    print(zoomCamera.getState())
                else:
                    printInfo()
            else:
                printInfo()
        except Ice.ObjectNotExistException:
            print("Object doesnt exist")
        except:
            print("error input")






def printInfo():
    str = """
    <device id> <command> <parameters>
    all list
    calc/1 add 4 5
    calc/1 subtract 4 5
    calc/1 divide 4 5
    calc/1 square 4
    calc/1 squareRoot 4
    fridge/1 getTemperature
    fridge/1 setTemperature 6
    tiltCamera/1 getScreen
    tiltCamera/1 setScreen cat
    zoomCamera/1 getScreen
    zoomCamera/1 zoom
    <device id> turnOn
    <device id> turnOff
    <device id> getState
    """
    print(str)