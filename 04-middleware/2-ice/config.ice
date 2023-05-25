["java:package:gen"]
//["python:package:gen"]
module House{
	
	enum State{On, Off}

    exception DivideByZeroException
    {
    }

    exception NegativeRootException
    {
		
    }
	
	
	interface Device{
		idempotent void turnOn();
		idempotent void turnOff();
		idempotent State getState();
	}
	
	interface Camera extends Device
	{
		idempotent string getScreen();
	}
	
	
   interface TiltCamera extends Camera
    {
        idempotent void setScreen(string position);
		
    }
	
	interface ZoomCamera extends Camera
    {
        idempotent void zoom();
		
    }


    interface Calculator extends Device
    {
        idempotent int add(int x, int y);

        idempotent int subtract(int x, int subtrahend);

        idempotent double divide(int numerator, int denominator) throws DivideByZeroException;

        idempotent int square(int x);

        idempotent double squareRoot(int x) throws NegativeRootException;


    }
	
	interface Fridge extends Device{
		idempotent int getTemperature();
		idempotent void setTemperature(int temperature);
	}
	
	sequence<string> mySequence;
    interface ListDevices
    {
        idempotent mySequence listAll();

    }



}
