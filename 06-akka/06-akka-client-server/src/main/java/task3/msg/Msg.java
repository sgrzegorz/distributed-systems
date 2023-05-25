package task3.msg;


public class Msg  {
    public int value;

    public Msg(int value) {
        this.value = value;
    }

    public static Msg getLowerPrice(Msg first, Msg second) {

        if (first == null && second == null) {
            return new Msg(-1);
        } else if (first != null && second != null) {
            int greater = (first.getPrice() > second.getPrice()) ? first.getPrice() : second.getPrice();
            return new Msg(greater);
        } else if (first != null) {
            return new Msg(first.getPrice());
        } else if (second != null) {
            return new Msg(second.getPrice());
        }
        throw new RuntimeException("PriceMsg exception");
    }

    public int getPrice() {
        return value;
    }

    @Override
    public String toString() {
        if(value==-1){
            return "Msg{" +
                    "value=" + "No price available for given product" +
                    '}';
        }else{
            return "Msg{" +
                    "value=" + value +
                    '}';
        }

    }
}
