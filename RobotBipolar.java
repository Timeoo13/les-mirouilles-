import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RobotBipolar extends Robot {
    private Random random;

    // Dimensions du canvas (en nombre de cases)
    private final int canvasWidth = 12;   // Nombre de cases en largeur
    private final int canvasHeight = 12;  // Nombre de cases en hauteur

    // Liste de tous les robots sur le canvas
    private static List<RobotBipolar> robots = new ArrayList<>();

    // Attributs pour dessiner le robot
    private CanvasRobot canvasRobot;

    // Constructeur
    public RobotBipolar(int x, int y, String name, String bodyColor) {
        super(x, y, name, bodyColor); // Appel du constructeur de la classe mère
        random = new Random();

        // Créer et dessiner le robot sur le canvas
        this.canvasRobot = new CanvasRobot(bodyColor);
        this.canvasRobot.drawRobot(x, y);

        // Ajouter ce robot à la liste des robots
        robots.add(this);
    }

    // Méthode pour détecter les robots voisins
    private RobotBipolar detecterRobotAdjacent() {
        for (RobotBipolar robot : robots) {
            if (robot != this) {
                // Vérifier les cases adjacentes
                if (isAdjacent(robot)) {
                    return robot; // Retourner le robot voisin s'il est adjacent
                }
            }
        }
        return null; // Aucun robot voisin trouvé
    }

    // Vérifie si le robot est adjacent
    private boolean isAdjacent(RobotBipolar robot) {
        int dx = Math.abs(robot.getX() - getX());
        int dy = Math.abs(robot.getY() - getY());
        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1); // Vérifier les adjacences horizontales et verticales
    }

    // Méthode pour déplacer le robot dans la direction opposée
    public void deplacer() {
        RobotBipolar voisin = detecterRobotAdjacent();

        if (voisin != null) {
            int direction = calculerDirectionOppose(voisin);
            int newX = getX();
            int newY = getY();

            // Calculer les nouvelles coordonnées en fonction de la direction opposée
            switch (direction) {
                case 1: // Robot à gauche
                    newX += 1; // Se déplacer à droite
                    break;
                case 2: // Robot à droite
                    newX -= 1; // Se déplacer à gauche
                    break;
                case 3: // Robot en haut
                    newY += 1; // Se déplacer vers le bas
                    break;
                case 4: // Robot en bas
                    newY -= 1; // Se déplacer vers le haut
                    break;
            }

            // Vérifier si les nouvelles coordonnées sont hors limites du canvas
            if (newX < 0 || newX >= canvasWidth || newY < 0 || newY >= canvasHeight) {
                System.out.println(getName() + " ne peut pas se déplacer car il sort des limites du canvas.");
                return; // Annuler le déplacement
            }

            // Vérifier si la destination est occupée par un autre robot
            for (RobotBipolar robot : robots) {
                if (robot != this && robot.getX() == newX && robot.getY() == newY) {
                    System.out.println(getName() + " ne peut pas se déplacer car la destination est déjà occupée par un autre robot.");
                    return; // Annuler le déplacement
                }
            }

            // Effacer le robot de l'ancienne position
            canvasRobot.drawRobot(getX(), getY()); // Efface le robot de l'ancienne position

            // Mettre à jour la position du robot
            setX(newX);
            setY(newY);

            // Dessiner le robot à la nouvelle position
            canvasRobot.drawRobot(newX, newY);

            System.out.println(getName() + " se déplace vers la direction opposée du robot adjacent.");
        }
    }

    // Méthode pour calculer la direction opposée à un robot voisin
    private int calculerDirectionOppose(RobotBipolar voisin) {
        int dx = voisin.getX() - getX();
        int dy = voisin.getY() - getY();

        if (dx == 1 && dy == 0) return 2; // Voisin à droite
        if (dx == -1 && dy == 0) return 1; // Voisin à gauche
        if (dx == 0 && dy == 1) return 4; // Voisin en bas
        if (dx == 0 && dy == -1) return 3; // Voisin en haut
        return 0; // Aucun voisin
    }
}
