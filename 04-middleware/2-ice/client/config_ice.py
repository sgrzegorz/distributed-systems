# -*- coding: utf-8 -*-
#
# Copyright (c) ZeroC, Inc. All rights reserved.
#
#
# Ice version 3.7.3
#
# <auto-generated>
#
# Generated from file `config.ice'
#
# Warning: do not edit this file.
#
# </auto-generated>
#

from sys import version_info as _version_info_
import Ice, IcePy

# Start of module House
_M_House = Ice.openModule('House')
__name__ = 'House'

if 'State' not in _M_House.__dict__:
    _M_House.State = Ice.createTempClass()
    class State(Ice.EnumBase):

        def __init__(self, _n, _v):
            Ice.EnumBase.__init__(self, _n, _v)

        def valueOf(self, _n):
            if _n in self._enumerators:
                return self._enumerators[_n]
            return None
        valueOf = classmethod(valueOf)

    State.On = State("On", 0)
    State.Off = State("Off", 1)
    State._enumerators = { 0:State.On, 1:State.Off }

    _M_House._t_State = IcePy.defineEnum('::House::State', State, (), State._enumerators)

    _M_House.State = State
    del State

if 'DivideByZeroException' not in _M_House.__dict__:
    _M_House.DivideByZeroException = Ice.createTempClass()
    class DivideByZeroException(Ice.UserException):
        def __init__(self):
            pass

        def __str__(self):
            return IcePy.stringifyException(self)

        __repr__ = __str__

        _ice_id = '::House::DivideByZeroException'

    _M_House._t_DivideByZeroException = IcePy.defineException('::House::DivideByZeroException', DivideByZeroException, (), False, None, ())
    DivideByZeroException._ice_type = _M_House._t_DivideByZeroException

    _M_House.DivideByZeroException = DivideByZeroException
    del DivideByZeroException

if 'NegativeRootException' not in _M_House.__dict__:
    _M_House.NegativeRootException = Ice.createTempClass()
    class NegativeRootException(Ice.UserException):
        def __init__(self):
            pass

        def __str__(self):
            return IcePy.stringifyException(self)

        __repr__ = __str__

        _ice_id = '::House::NegativeRootException'

    _M_House._t_NegativeRootException = IcePy.defineException('::House::NegativeRootException', NegativeRootException, (), False, None, ())
    NegativeRootException._ice_type = _M_House._t_NegativeRootException

    _M_House.NegativeRootException = NegativeRootException
    del NegativeRootException

_M_House._t_Device = IcePy.defineValue('::House::Device', Ice.Value, -1, (), False, True, None, ())

if 'DevicePrx' not in _M_House.__dict__:
    _M_House.DevicePrx = Ice.createTempClass()
    class DevicePrx(Ice.ObjectPrx):

        def turnOn(self, context=None):
            return _M_House.Device._op_turnOn.invoke(self, ((), context))

        def turnOnAsync(self, context=None):
            return _M_House.Device._op_turnOn.invokeAsync(self, ((), context))

        def begin_turnOn(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_House.Device._op_turnOn.begin(self, ((), _response, _ex, _sent, context))

        def end_turnOn(self, _r):
            return _M_House.Device._op_turnOn.end(self, _r)

        def turnOff(self, context=None):
            return _M_House.Device._op_turnOff.invoke(self, ((), context))

        def turnOffAsync(self, context=None):
            return _M_House.Device._op_turnOff.invokeAsync(self, ((), context))

        def begin_turnOff(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_House.Device._op_turnOff.begin(self, ((), _response, _ex, _sent, context))

        def end_turnOff(self, _r):
            return _M_House.Device._op_turnOff.end(self, _r)

        def getState(self, context=None):
            return _M_House.Device._op_getState.invoke(self, ((), context))

        def getStateAsync(self, context=None):
            return _M_House.Device._op_getState.invokeAsync(self, ((), context))

        def begin_getState(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_House.Device._op_getState.begin(self, ((), _response, _ex, _sent, context))

        def end_getState(self, _r):
            return _M_House.Device._op_getState.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_House.DevicePrx.ice_checkedCast(proxy, '::House::Device', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_House.DevicePrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::House::Device'
    _M_House._t_DevicePrx = IcePy.defineProxy('::House::Device', DevicePrx)

    _M_House.DevicePrx = DevicePrx
    del DevicePrx

    _M_House.Device = Ice.createTempClass()
    class Device(Ice.Object):

        def ice_ids(self, current=None):
            return ('::House::Device', '::Ice::Object')

        def ice_id(self, current=None):
            return '::House::Device'

        @staticmethod
        def ice_staticId():
            return '::House::Device'

        def turnOn(self, current=None):
            raise NotImplementedError("servant method 'turnOn' not implemented")

        def turnOff(self, current=None):
            raise NotImplementedError("servant method 'turnOff' not implemented")

        def getState(self, current=None):
            raise NotImplementedError("servant method 'getState' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_House._t_DeviceDisp)

        __repr__ = __str__

    _M_House._t_DeviceDisp = IcePy.defineClass('::House::Device', Device, (), None, ())
    Device._ice_type = _M_House._t_DeviceDisp

    Device._op_turnOn = IcePy.Operation('turnOn', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (), (), None, ())
    Device._op_turnOff = IcePy.Operation('turnOff', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (), (), None, ())
    Device._op_getState = IcePy.Operation('getState', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (), (), ((), _M_House._t_State, False, 0), ())

    _M_House.Device = Device
    del Device

_M_House._t_Camera = IcePy.defineValue('::House::Camera', Ice.Value, -1, (), False, True, None, ())

if 'CameraPrx' not in _M_House.__dict__:
    _M_House.CameraPrx = Ice.createTempClass()
    class CameraPrx(_M_House.DevicePrx):

        def getScreen(self, context=None):
            return _M_House.Camera._op_getScreen.invoke(self, ((), context))

        def getScreenAsync(self, context=None):
            return _M_House.Camera._op_getScreen.invokeAsync(self, ((), context))

        def begin_getScreen(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_House.Camera._op_getScreen.begin(self, ((), _response, _ex, _sent, context))

        def end_getScreen(self, _r):
            return _M_House.Camera._op_getScreen.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_House.CameraPrx.ice_checkedCast(proxy, '::House::Camera', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_House.CameraPrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::House::Camera'
    _M_House._t_CameraPrx = IcePy.defineProxy('::House::Camera', CameraPrx)

    _M_House.CameraPrx = CameraPrx
    del CameraPrx

    _M_House.Camera = Ice.createTempClass()
    class Camera(_M_House.Device):

        def ice_ids(self, current=None):
            return ('::House::Camera', '::House::Device', '::Ice::Object')

        def ice_id(self, current=None):
            return '::House::Camera'

        @staticmethod
        def ice_staticId():
            return '::House::Camera'

        def getScreen(self, current=None):
            raise NotImplementedError("servant method 'getScreen' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_House._t_CameraDisp)

        __repr__ = __str__

    _M_House._t_CameraDisp = IcePy.defineClass('::House::Camera', Camera, (), None, (_M_House._t_DeviceDisp,))
    Camera._ice_type = _M_House._t_CameraDisp

    Camera._op_getScreen = IcePy.Operation('getScreen', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (), (), ((), IcePy._t_string, False, 0), ())

    _M_House.Camera = Camera
    del Camera

_M_House._t_TiltCamera = IcePy.defineValue('::House::TiltCamera', Ice.Value, -1, (), False, True, None, ())

if 'TiltCameraPrx' not in _M_House.__dict__:
    _M_House.TiltCameraPrx = Ice.createTempClass()
    class TiltCameraPrx(_M_House.CameraPrx):

        def setScreen(self, position, context=None):
            return _M_House.TiltCamera._op_setScreen.invoke(self, ((position, ), context))

        def setScreenAsync(self, position, context=None):
            return _M_House.TiltCamera._op_setScreen.invokeAsync(self, ((position, ), context))

        def begin_setScreen(self, position, _response=None, _ex=None, _sent=None, context=None):
            return _M_House.TiltCamera._op_setScreen.begin(self, ((position, ), _response, _ex, _sent, context))

        def end_setScreen(self, _r):
            return _M_House.TiltCamera._op_setScreen.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_House.TiltCameraPrx.ice_checkedCast(proxy, '::House::TiltCamera', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_House.TiltCameraPrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::House::TiltCamera'
    _M_House._t_TiltCameraPrx = IcePy.defineProxy('::House::TiltCamera', TiltCameraPrx)

    _M_House.TiltCameraPrx = TiltCameraPrx
    del TiltCameraPrx

    _M_House.TiltCamera = Ice.createTempClass()
    class TiltCamera(_M_House.Camera):

        def ice_ids(self, current=None):
            return ('::House::Camera', '::House::Device', '::House::TiltCamera', '::Ice::Object')

        def ice_id(self, current=None):
            return '::House::TiltCamera'

        @staticmethod
        def ice_staticId():
            return '::House::TiltCamera'

        def setScreen(self, position, current=None):
            raise NotImplementedError("servant method 'setScreen' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_House._t_TiltCameraDisp)

        __repr__ = __str__

    _M_House._t_TiltCameraDisp = IcePy.defineClass('::House::TiltCamera', TiltCamera, (), None, (_M_House._t_CameraDisp,))
    TiltCamera._ice_type = _M_House._t_TiltCameraDisp

    TiltCamera._op_setScreen = IcePy.Operation('setScreen', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (((), IcePy._t_string, False, 0),), (), None, ())

    _M_House.TiltCamera = TiltCamera
    del TiltCamera

_M_House._t_ZoomCamera = IcePy.defineValue('::House::ZoomCamera', Ice.Value, -1, (), False, True, None, ())

if 'ZoomCameraPrx' not in _M_House.__dict__:
    _M_House.ZoomCameraPrx = Ice.createTempClass()
    class ZoomCameraPrx(_M_House.CameraPrx):

        def zoom(self, context=None):
            return _M_House.ZoomCamera._op_zoom.invoke(self, ((), context))

        def zoomAsync(self, context=None):
            return _M_House.ZoomCamera._op_zoom.invokeAsync(self, ((), context))

        def begin_zoom(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_House.ZoomCamera._op_zoom.begin(self, ((), _response, _ex, _sent, context))

        def end_zoom(self, _r):
            return _M_House.ZoomCamera._op_zoom.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_House.ZoomCameraPrx.ice_checkedCast(proxy, '::House::ZoomCamera', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_House.ZoomCameraPrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::House::ZoomCamera'
    _M_House._t_ZoomCameraPrx = IcePy.defineProxy('::House::ZoomCamera', ZoomCameraPrx)

    _M_House.ZoomCameraPrx = ZoomCameraPrx
    del ZoomCameraPrx

    _M_House.ZoomCamera = Ice.createTempClass()
    class ZoomCamera(_M_House.Camera):

        def ice_ids(self, current=None):
            return ('::House::Camera', '::House::Device', '::House::ZoomCamera', '::Ice::Object')

        def ice_id(self, current=None):
            return '::House::ZoomCamera'

        @staticmethod
        def ice_staticId():
            return '::House::ZoomCamera'

        def zoom(self, current=None):
            raise NotImplementedError("servant method 'zoom' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_House._t_ZoomCameraDisp)

        __repr__ = __str__

    _M_House._t_ZoomCameraDisp = IcePy.defineClass('::House::ZoomCamera', ZoomCamera, (), None, (_M_House._t_CameraDisp,))
    ZoomCamera._ice_type = _M_House._t_ZoomCameraDisp

    ZoomCamera._op_zoom = IcePy.Operation('zoom', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (), (), None, ())

    _M_House.ZoomCamera = ZoomCamera
    del ZoomCamera

_M_House._t_Calculator = IcePy.defineValue('::House::Calculator', Ice.Value, -1, (), False, True, None, ())

if 'CalculatorPrx' not in _M_House.__dict__:
    _M_House.CalculatorPrx = Ice.createTempClass()
    class CalculatorPrx(_M_House.DevicePrx):

        def add(self, x, y, context=None):
            return _M_House.Calculator._op_add.invoke(self, ((x, y), context))

        def addAsync(self, x, y, context=None):
            return _M_House.Calculator._op_add.invokeAsync(self, ((x, y), context))

        def begin_add(self, x, y, _response=None, _ex=None, _sent=None, context=None):
            return _M_House.Calculator._op_add.begin(self, ((x, y), _response, _ex, _sent, context))

        def end_add(self, _r):
            return _M_House.Calculator._op_add.end(self, _r)

        def subtract(self, x, subtrahend, context=None):
            return _M_House.Calculator._op_subtract.invoke(self, ((x, subtrahend), context))

        def subtractAsync(self, x, subtrahend, context=None):
            return _M_House.Calculator._op_subtract.invokeAsync(self, ((x, subtrahend), context))

        def begin_subtract(self, x, subtrahend, _response=None, _ex=None, _sent=None, context=None):
            return _M_House.Calculator._op_subtract.begin(self, ((x, subtrahend), _response, _ex, _sent, context))

        def end_subtract(self, _r):
            return _M_House.Calculator._op_subtract.end(self, _r)

        def divide(self, numerator, denominator, context=None):
            return _M_House.Calculator._op_divide.invoke(self, ((numerator, denominator), context))

        def divideAsync(self, numerator, denominator, context=None):
            return _M_House.Calculator._op_divide.invokeAsync(self, ((numerator, denominator), context))

        def begin_divide(self, numerator, denominator, _response=None, _ex=None, _sent=None, context=None):
            return _M_House.Calculator._op_divide.begin(self, ((numerator, denominator), _response, _ex, _sent, context))

        def end_divide(self, _r):
            return _M_House.Calculator._op_divide.end(self, _r)

        def square(self, x, context=None):
            return _M_House.Calculator._op_square.invoke(self, ((x, ), context))

        def squareAsync(self, x, context=None):
            return _M_House.Calculator._op_square.invokeAsync(self, ((x, ), context))

        def begin_square(self, x, _response=None, _ex=None, _sent=None, context=None):
            return _M_House.Calculator._op_square.begin(self, ((x, ), _response, _ex, _sent, context))

        def end_square(self, _r):
            return _M_House.Calculator._op_square.end(self, _r)

        def squareRoot(self, x, context=None):
            return _M_House.Calculator._op_squareRoot.invoke(self, ((x, ), context))

        def squareRootAsync(self, x, context=None):
            return _M_House.Calculator._op_squareRoot.invokeAsync(self, ((x, ), context))

        def begin_squareRoot(self, x, _response=None, _ex=None, _sent=None, context=None):
            return _M_House.Calculator._op_squareRoot.begin(self, ((x, ), _response, _ex, _sent, context))

        def end_squareRoot(self, _r):
            return _M_House.Calculator._op_squareRoot.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_House.CalculatorPrx.ice_checkedCast(proxy, '::House::Calculator', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_House.CalculatorPrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::House::Calculator'
    _M_House._t_CalculatorPrx = IcePy.defineProxy('::House::Calculator', CalculatorPrx)

    _M_House.CalculatorPrx = CalculatorPrx
    del CalculatorPrx

    _M_House.Calculator = Ice.createTempClass()
    class Calculator(_M_House.Device):

        def ice_ids(self, current=None):
            return ('::House::Calculator', '::House::Device', '::Ice::Object')

        def ice_id(self, current=None):
            return '::House::Calculator'

        @staticmethod
        def ice_staticId():
            return '::House::Calculator'

        def add(self, x, y, current=None):
            raise NotImplementedError("servant method 'add' not implemented")

        def subtract(self, x, subtrahend, current=None):
            raise NotImplementedError("servant method 'subtract' not implemented")

        def divide(self, numerator, denominator, current=None):
            raise NotImplementedError("servant method 'divide' not implemented")

        def square(self, x, current=None):
            raise NotImplementedError("servant method 'square' not implemented")

        def squareRoot(self, x, current=None):
            raise NotImplementedError("servant method 'squareRoot' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_House._t_CalculatorDisp)

        __repr__ = __str__

    _M_House._t_CalculatorDisp = IcePy.defineClass('::House::Calculator', Calculator, (), None, (_M_House._t_DeviceDisp,))
    Calculator._ice_type = _M_House._t_CalculatorDisp

    Calculator._op_add = IcePy.Operation('add', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (((), IcePy._t_int, False, 0), ((), IcePy._t_int, False, 0)), (), ((), IcePy._t_int, False, 0), ())
    Calculator._op_subtract = IcePy.Operation('subtract', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (((), IcePy._t_int, False, 0), ((), IcePy._t_int, False, 0)), (), ((), IcePy._t_int, False, 0), ())
    Calculator._op_divide = IcePy.Operation('divide', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (((), IcePy._t_int, False, 0), ((), IcePy._t_int, False, 0)), (), ((), IcePy._t_double, False, 0), (_M_House._t_DivideByZeroException,))
    Calculator._op_square = IcePy.Operation('square', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (((), IcePy._t_int, False, 0),), (), ((), IcePy._t_int, False, 0), ())
    Calculator._op_squareRoot = IcePy.Operation('squareRoot', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (((), IcePy._t_int, False, 0),), (), ((), IcePy._t_double, False, 0), (_M_House._t_NegativeRootException,))

    _M_House.Calculator = Calculator
    del Calculator

_M_House._t_Fridge = IcePy.defineValue('::House::Fridge', Ice.Value, -1, (), False, True, None, ())

if 'FridgePrx' not in _M_House.__dict__:
    _M_House.FridgePrx = Ice.createTempClass()
    class FridgePrx(_M_House.DevicePrx):

        def getTemperature(self, context=None):
            return _M_House.Fridge._op_getTemperature.invoke(self, ((), context))

        def getTemperatureAsync(self, context=None):
            return _M_House.Fridge._op_getTemperature.invokeAsync(self, ((), context))

        def begin_getTemperature(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_House.Fridge._op_getTemperature.begin(self, ((), _response, _ex, _sent, context))

        def end_getTemperature(self, _r):
            return _M_House.Fridge._op_getTemperature.end(self, _r)

        def setTemperature(self, temperature, context=None):
            return _M_House.Fridge._op_setTemperature.invoke(self, ((temperature, ), context))

        def setTemperatureAsync(self, temperature, context=None):
            return _M_House.Fridge._op_setTemperature.invokeAsync(self, ((temperature, ), context))

        def begin_setTemperature(self, temperature, _response=None, _ex=None, _sent=None, context=None):
            return _M_House.Fridge._op_setTemperature.begin(self, ((temperature, ), _response, _ex, _sent, context))

        def end_setTemperature(self, _r):
            return _M_House.Fridge._op_setTemperature.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_House.FridgePrx.ice_checkedCast(proxy, '::House::Fridge', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_House.FridgePrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::House::Fridge'
    _M_House._t_FridgePrx = IcePy.defineProxy('::House::Fridge', FridgePrx)

    _M_House.FridgePrx = FridgePrx
    del FridgePrx

    _M_House.Fridge = Ice.createTempClass()
    class Fridge(_M_House.Device):

        def ice_ids(self, current=None):
            return ('::House::Device', '::House::Fridge', '::Ice::Object')

        def ice_id(self, current=None):
            return '::House::Fridge'

        @staticmethod
        def ice_staticId():
            return '::House::Fridge'

        def getTemperature(self, current=None):
            raise NotImplementedError("servant method 'getTemperature' not implemented")

        def setTemperature(self, temperature, current=None):
            raise NotImplementedError("servant method 'setTemperature' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_House._t_FridgeDisp)

        __repr__ = __str__

    _M_House._t_FridgeDisp = IcePy.defineClass('::House::Fridge', Fridge, (), None, (_M_House._t_DeviceDisp,))
    Fridge._ice_type = _M_House._t_FridgeDisp

    Fridge._op_getTemperature = IcePy.Operation('getTemperature', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (), (), ((), IcePy._t_int, False, 0), ())
    Fridge._op_setTemperature = IcePy.Operation('setTemperature', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (((), IcePy._t_int, False, 0),), (), None, ())

    _M_House.Fridge = Fridge
    del Fridge

if '_t_mySequence' not in _M_House.__dict__:
    _M_House._t_mySequence = IcePy.defineSequence('::House::mySequence', (), IcePy._t_string)

_M_House._t_ListDevices = IcePy.defineValue('::House::ListDevices', Ice.Value, -1, (), False, True, None, ())

if 'ListDevicesPrx' not in _M_House.__dict__:
    _M_House.ListDevicesPrx = Ice.createTempClass()
    class ListDevicesPrx(Ice.ObjectPrx):

        def listAll(self, context=None):
            return _M_House.ListDevices._op_listAll.invoke(self, ((), context))

        def listAllAsync(self, context=None):
            return _M_House.ListDevices._op_listAll.invokeAsync(self, ((), context))

        def begin_listAll(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_House.ListDevices._op_listAll.begin(self, ((), _response, _ex, _sent, context))

        def end_listAll(self, _r):
            return _M_House.ListDevices._op_listAll.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_House.ListDevicesPrx.ice_checkedCast(proxy, '::House::ListDevices', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_House.ListDevicesPrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::House::ListDevices'
    _M_House._t_ListDevicesPrx = IcePy.defineProxy('::House::ListDevices', ListDevicesPrx)

    _M_House.ListDevicesPrx = ListDevicesPrx
    del ListDevicesPrx

    _M_House.ListDevices = Ice.createTempClass()
    class ListDevices(Ice.Object):

        def ice_ids(self, current=None):
            return ('::House::ListDevices', '::Ice::Object')

        def ice_id(self, current=None):
            return '::House::ListDevices'

        @staticmethod
        def ice_staticId():
            return '::House::ListDevices'

        def listAll(self, current=None):
            raise NotImplementedError("servant method 'listAll' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_House._t_ListDevicesDisp)

        __repr__ = __str__

    _M_House._t_ListDevicesDisp = IcePy.defineClass('::House::ListDevices', ListDevices, (), None, ())
    ListDevices._ice_type = _M_House._t_ListDevicesDisp

    ListDevices._op_listAll = IcePy.Operation('listAll', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (), (), ((), _M_House._t_mySequence, False, 0), ())

    _M_House.ListDevices = ListDevices
    del ListDevices

# End of module House