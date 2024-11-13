public class Robot {
    // Attributs
    private int x;
    private int y;
    private String name;
    private String bodyColor;

    // Constructeur
    public Robot(int x, int y, String name, String bodyColor) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.bodyColor = bodyColor;
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public String getBodyColor() {
        return bodyColor;
    }

    // Setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBodyColor(String bodyColor) {
        this.bodyColor = bodyColor;
    }

    // Méthode pour afficher les informations du robot
    public void displayInfo() {
        System.out.println("Robot Name: " + name);
        System.out.println("Position: (" + x + ", " + y + ")");
        System.out.println("Body Color: " + bodyColor);
    }
}
