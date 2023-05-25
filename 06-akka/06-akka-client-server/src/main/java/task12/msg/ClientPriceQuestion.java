package task12.msg;

import com.fasterxml.jackson.annotation.JsonCreator;


public class ClientPriceQuestion implements MySerializable{
    public String productName;

    @JsonCreator
    public ClientPriceQuestion(String productName) {
        this.productName =productName;
    }
}
