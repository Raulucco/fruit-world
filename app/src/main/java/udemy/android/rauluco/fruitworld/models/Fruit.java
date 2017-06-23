package udemy.android.rauluco.fruitworld.models;


public class Fruit {
    private String origin;
    private int icon;
    private String name;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fruit(String origin, String name, int icon) {
        this.origin = origin;
        this.icon = icon;
        this.name = name;
    }

    public Fruit() {

    }
}
