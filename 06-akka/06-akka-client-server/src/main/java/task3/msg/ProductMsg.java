package task3.msg;


import task3.Database;

public class ProductMsg {

        public int value;
        public int nQuestions;
        public String productName;

        public ProductMsg(String productName) {
            this.productName =productName;
        }

        public ProductMsg(int value, String productName) {
            this.value = value;
            this.productName =productName;
            this.nQuestions = Database.get(productName);
        }

        @Override
        public String toString() {
            String valueString = value+"";
            if(value==-1) valueString = "No price available for given product";

            return "ProductMsg{" +
                    "productName= "+productName +
                    ", value=" + valueString +
                    ", nQuestions= "+ nQuestions+
                    '}';

        }
    }

