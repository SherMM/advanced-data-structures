public class Item {
  private long id;
  private double price;
  private Description desc;

  public Item(long id, double price, long[] desc, int size) {
        this.id = id;
        this.price = price;
        this.desc = new Description(desc, size);
  }
}
