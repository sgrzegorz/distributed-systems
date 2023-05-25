package task12.msg;

import com.fasterxml.jackson.annotation.JsonCreator;
import task12.database.Database;

public class PriceResponseMsg implements MySerializable {
    public int value;
    public int nQuestions =-2;
    public String productName;

    @JsonCreator
    public PriceResponseMsg(int value, String productName) {
        this.value = value;
        this.productName =productName;
        this.nQuestions = Database.get(productName);
    }

    @Override
    public String toString() {
        String valueString = value+"";
        if(value==-1) valueString = "No price available for given product";

        return "PriceResponseMsg{" +
                "productName= "+productName +
                ", value=" + valueString +
                ", nQuestions= "+ nQuestions+
                '}';



    }

}
