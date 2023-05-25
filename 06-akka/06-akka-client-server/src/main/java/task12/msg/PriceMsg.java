package task12.msg;

import com.fasterxml.jackson.annotation.JsonCreator;

public class PriceMsg implements MySerializable {
    public int value;

    @JsonCreator
    public PriceMsg(int value) {
        this.value = value;
    }

    public static PriceMsg getLowerPrice(PriceMsg first, PriceMsg second) {

        if (first == null && second == null) {
            return new PriceMsg(-1);
        } else if (first != null && second != null) {
            int greater = (first.getPrice() > second.getPrice()) ? first.getPrice() : second.getPrice();
            return new PriceMsg(greater);
        } else if (first != null) {
            return new PriceMsg(first.getPrice());
        } else if (second != null) {
            return new PriceMsg(second.getPrice());
        }
        throw new RuntimeException("PriceMsg exception");
    }

    public int getPrice() {
        return value;
    }

    @Override
    public String toString() {
        if(value==-1){
            return "PriceMsg{" +
                    "value=" + "No price available for given product" +
                    '}';
        }else{
            return "PriceMsg{" +
                    "value=" + value +
                    '}';
        }

    }
}
