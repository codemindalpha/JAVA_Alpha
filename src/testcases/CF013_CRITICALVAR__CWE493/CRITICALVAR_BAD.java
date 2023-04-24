package CF013_CRITICALVAR__CWE493;

public class CRITICALVAR_BAD {
    public final class Data extends Phone {
        public float price;
        //...
        public Data() {
            this.price = LookupPrice("MyDataType");
        }

        private float LookupPrice(String myDataType) {
            return 0;
        }
    }

    private class Phone {
    }
}
