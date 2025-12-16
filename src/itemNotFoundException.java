public class itemNotFoundException extends Exception {
    public itemNotFoundException(String itemName) {
        super(itemName);
    }
}
