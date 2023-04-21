public class Toy {
    private int id;
    private String name;
    private int amount;
    private double weight;

    public Toy(int id, String name, int amount, double weight) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.weight = weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getWeight() {
        return weight;
    }

    public String getInfo() {
        return "id = " + this.id + "\n name = " + this.name + "\n amount = " + this.amount + "\n chance = "
                + this.weight + " %\n";
    }
}