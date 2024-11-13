import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class RobotBishop extends Robot {
    private Random random;

    // Dimensions du canvas (en nombre de cases)
    private final int canvasWidth = 12;   // Nombre de cases en largeur
    private final int canvasHeight = 12;  // Nombre de cases en hauteur

    // Liste de tous les robots sur le canvas
    private static List<RobotBishop> bishops = new ArrayList<>();

    // Attributs pour dessiner le robot
    private CanvasRobot canvasRobot;

    // Constructeur
    public RobotBishop(int x, int y, String name, String bodyColor) {
        super(x, y, name, bodyColor); // Appel du constructeur de la classe mère
        random = new Random();

        // Créer et dessiner le robot sur le canvas
        this.canvasRobot = new CanvasRobot(bodyColor);
        this.canvasRobot.drawRobot(x, y);

        // Ajouter ce robot à la liste des bishops
        bishops.add(this);
    }

    // Méthode pour déplacer le robot uniquement en diagonale
    public void deplacer() {
        // Enregistrer l'ancienne position
        int oldX = getX();
        int oldY = getY();

        // Lancer un dé pour choisir la direction diagonale : 
        // 1 = Nord-Est, 2 = Sud-Est, 3 = Sud-Ouest, 4 = Nord-Ouest
        int direction = random.nextInt(4) + 1;

        // Lancer un autre dé pour la distance (1 à 6 cases)
        int distance = random.nextInt(6) + 1;

        // Variables pour les nouvelles coordonnées
        int newX = oldX;
        int newY = oldY;

        // Calculer les nouvelles coordonnées selon la direction diagonale
        switch (direction) {
            case 1: // Nord-Est
                newX += distance;
                newY -= distance;
                break;
            case 2: // Sud-Est
                newX += distance;
                newY += distance;
                break;
            case 3: // Sud-Ouest
                newX -= distance;
                newY += distance;
                break;
            case 4: // Nord-Ouest
                newX -= distance;
                newY -= distance;
                break;
        }

        // Vérifier si les nouvelles coordonnées sont hors limites du canvas
        if (newX < 0 || newX >= canvasWidth || newY < 0 || newY >= canvasHeight) {
            System.out.println(getName() + " ne peut pas se déplacer car il sort des limites du canvas.");
            return; // Annuler le déplacement
        }

        // Vérifier si la destination est occupée par un autre robot
        for (RobotBishop bishop : bishops) {
            if (bishop != this && bishop.getX() == newX && bishop.getY() == newY) {
                System.out.println(getName() + " ne peut pas se déplacer car la destination est déjà occupée par un autre robot.");
                return; // Annuler le déplacement
            }
        }

        // Vérifier le chemin diagonal pour s'assurer qu'il n'y a pas d'autres robots
        for (int i = 1; i <= distance; i++) {
            int checkX = oldX;
            int checkY = oldY;

            switch (direction) {
                case 1: // Nord-Est
                    checkX += i;
                    checkY -= i;
                    break;
                case 2: // Sud-Est
                    checkX += i;
                    checkY += i;
                    break;
                case 3: // Sud-Ouest
                    checkX -= i;
                    checkY += i;
                    break;
                case 4: // Nord-Ouest
                    checkX -= i;
                    checkY -= i;
                    break;
            }

            // Vérifier si la position sur le chemin est occupée par un autre robot
            for (RobotBishop bishop : bishops) {
                if (bishop != this && bishop.getX() == checkX && bishop.getY() == checkY) {
                    System.out.println(getName() + " ne peut pas se déplacer car un autre robot bloque le chemin.");
                    return; // Annuler le déplacement
                }
            }
        }

        // Effacer le robot de l'ancienne position
        canvasRobot.drawRobot(oldX, oldY); // Efface le robot de l'ancienne position

        // Mettre à jour la position du robot
        setX(newX);
        setY(newY);

        // Dessiner le robot à la nouvelle position
        canvasRobot.drawRobot(newX, newY);

        System.out.println(getName() + " se déplace en diagonale vers " + getDirection(direction) + " de " + distance + " cases.");
    }

    // Méthode pour obtenir la direction sous forme de chaîne de caractères
    private String getDirection(int direction) {
        switch (direction) {
            case 1: return "Nord-Est";
            case 2: return "Sud-Est";
            case 3: return "Sud-Ouest";
            case 4: return "Nord-Ouest";
            default: return "";
        }
    }
}
