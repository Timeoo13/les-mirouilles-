import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class SheepJumpingRobot extends Robot {
    private Random random;

    // Dimensions du canvas (en nombre de cases)
    private final int canvasWidth = 12;   // Nombre de cases en largeur
    private final int canvasHeight = 12;  // Nombre de cases en hauteur

    // Liste de tous les robots sur le canvas
    private static List<SheepJumpingRobot> sheepRobots = new ArrayList<>();

    // Attributs pour dessiner le robot
    private CanvasRobot canvasRobot;

    // Constructeur
    public SheepJumpingRobot(int x, int y, String name, String bodyColor) {
        super(x, y, name, bodyColor); // Appel du constructeur de la classe mère
        random = new Random();

        // Créer et dessiner le robot sur le canvas
        this.canvasRobot = new CanvasRobot(bodyColor);
        this.canvasRobot.drawRobot(x, y);

        // Ajouter ce robot à la liste des robots sheep
        sheepRobots.add(this);
    }

    // Méthode pour déplacer le robot normalement (Nord, Sud, Est, Ouest)
    public void deplacer() {
        // Enregistrer l'ancienne position
        int oldX = getX();
        int oldY = getY();

        // Lancer un dé pour choisir la direction : 1=Nord, 2=Est, 3=Sud, 4=Ouest
        int direction = random.nextInt(4) + 1;

        // Lancer un autre dé pour la distance (1 à 6 cases)
        int distance = random.nextInt(6) + 1;

        // Variables pour les nouvelles coordonnées
        int newX = oldX;
        int newY = oldY;

        // Calculer les nouvelles coordonnées selon la direction
        switch (direction) {
            case 1: // Nord
                newY -= distance;
                break;
            case 2: // Est
                newX += distance;
                break;
            case 3: // Sud
                newY += distance;
                break;
            case 4: // Ouest
                newX -= distance;
                break;
        }

        // Vérifier si les nouvelles coordonnées sont hors limites du canvas
        if (newX < 0 || newX >= canvasWidth || newY < 0 || newY >= canvasHeight) {
            System.out.println(getName() + " ne peut pas se déplacer car il sort des limites du canvas.");
            return; // Annuler le déplacement
        }

        // Contrairement aux autres classes, ici, le robot peut passer à travers d'autres robots.
        // Il ne vérifie donc pas si la destination ou le chemin est occupé par un autre robot.

        // Effacer le robot de l'ancienne position
        canvasRobot.drawRobot(oldX, oldY); // Efface le robot de l'ancienne position

        // Mettre à jour la position du robot
        setX(newX);
        setY(newY);

        // Dessiner le robot à la nouvelle position
        canvasRobot.drawRobot(newX, newY);

        System.out.println(getName() + " se déplace vers " + getDirection(direction) + " de " + distance + " cases.");
    }

    // Méthode pour obtenir la direction sous forme de chaîne de caractères
    private String getDirection(int direction) {
        switch (direction) {
            case 1: return "Nord";
            case 2: return "Est";
            case 3: return "Sud";
            case 4: return "Ouest";
            default: return "";
        }
    }
}
