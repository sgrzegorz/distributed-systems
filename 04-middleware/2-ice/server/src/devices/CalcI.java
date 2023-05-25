package devices;

import com.zeroc.Ice.Current;
import gen.House.Calculator;
import gen.House.DivideByZeroException;
import gen.House.NegativeRootException;
import gen.House.State;

import static gen.House.State.Off;
import static gen.House.State.On;

public class CalcI extends Device implements Calculator  {


    @Override
    public int add(int x, int y, Current current) {
        printInfo( new Object() {}.getClass().getEnclosingMethod().getName(),current);
        return x+y;
    }

    @Override
    public int subtract(int x, int subtrahend, Current current) {
        printInfo( new Object() {}.getClass().getEnclosingMethod().getName(),current);
        return x-subtrahend;
    }

    @Override
    public double divide(int numerator, int denominator, Current current) throws DivideByZeroException {
        printInfo( new Object() {}.getClass().getEnclosingMethod().getName(),current);

        if(denominator==0) throw new DivideByZeroException();

        return numerator/denominator;
    }

    @Override
    public int square(int x, Current current) {
        printInfo( new Object() {}.getClass().getEnclosingMethod().getName(),current);
        return (int) Math.pow(x,2);
    }

    @Override
    public double squareRoot(int x, Current current) throws NegativeRootException {
        printInfo( new Object() {}.getClass().getEnclosingMethod().getName(),current);
        return Math.sqrt(x);
    }

}
