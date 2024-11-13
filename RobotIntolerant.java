import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class RobotIntolerant extends Robot {
    private Random random;
    // Dimensions du canvas (en nombre de cases)
    private final int canvasWidth = 12;   // Nombre de cases en largeur
    private final int canvasHeight = 12;  // Nombre de cases en hauteur

    // Liste de tous les robots sur le canvas
    private static List<RobotIntolerant> robots = new ArrayList<>();

    // Attributs pour dessiner le robot
    private CanvasRobot canvasRobot;

    // Constructeur
    public RobotIntolerant(int x, int y, String name, String bodyColor) {
        super(x, y, name, bodyColor); // Appel du constructeur de la classe mère
        random = new Random();

        // Créer et dessiner le robot sur le canvas
        this.canvasRobot = new CanvasRobot(bodyColor);
        this.canvasRobot.drawRobot(x, y);

        // Ajouter ce robot à la liste des robots
        robots.add(this);
    }

    // Méthode pour déplacer le robot
    public void deplacer() {
        // Enregistrer l'ancienne position
        int oldX = getX();
        int oldY = getY();

        // Direction aléatoire (1=Nord, 2=Est, 3=Sud, 4=Ouest)
        int direction = random.nextInt(4) + 1;

        // Calculer les nouvelles coordonnées selon la direction choisie
        int newX = oldX;
        int newY = oldY;

        switch (direction) {
            case 1: // Nord
                newY -= 1;
                break;
            case 2: // Est
                newX += 1;
                break;
            case 3: // Sud
                newY += 1;
                break;
            case 4: // Ouest
                newX -= 1;
                break;
        }

        // Vérifier si les nouvelles coordonnées sont hors limites du canvas
        if (newX < 0 || newX >= canvasWidth || newY < 0 || newY >= canvasHeight) {
            System.out.println(getName() + " ne peut pas se déplacer car il sort des limites du canvas.");
            return; // Annuler le déplacement
        }

        // Vérifier si un robot voisin est présent
        for (RobotIntolerant robot : robots) {
            if (robot != this) {
                if (Math.abs(robot.getX() - newX) + Math.abs(robot.getY() - newY) == 1) {
                    // Si le robot voisin est à côté, vérifier sa couleur
                    if ("blue".equals(robot.getBodyColor())) {
                        // Aller dans la direction opposée
                        System.out.println(getName() + " rencontre un robot bleu et va dans la direction opposée.");

                        // Déterminer la direction opposée
                        switch (direction) {
                            case 1: // Nord
                                newY += 2; // Aller au Sud
                                break;
                            case 2: // Est
                                newX -= 2; // Aller à l'Ouest
                                break;
                            case 3: // Sud
                                newY -= 2; // Aller au Nord
                                break;
                            case 4: // Ouest
                                newX += 2; // Aller à l'Est
                                break;
                        }

                        // Vérifier si la nouvelle position est dans le canvas
                        if (newX < 0 || newX >= canvasWidth || newY < 0 || newY >= canvasHeight) {
                            System.out.println(getName() + " ne peut pas se déplacer car il sortirait des limites du canvas après avoir rencontré un robot bleu.");
                            return; // Ne pas se déplacer si hors limites
                        }

                        // Effacer le robot de l'ancienne position
                        canvasRobot.drawRobot(oldX, oldY); // Efface le robot de l'ancienne position

                        // Mettre à jour la position du robot
                        setX(newX);
                        setY(newY);

                        // Dessiner le robot à la nouvelle position
                        canvasRobot.drawRobot(newX, newY);

                        System.out.println(getName() + " se déplace vers " + getDirection(direction) + " à cause d'un robot bleu.");
                        return; // Fin de la méthode car le robot s'est déplacé
                    }
                }
            }
        }

        // Si aucun robot bleu n'est détecté, procéder au mouvement initial
        // Effacer le robot de l'ancienne position
        canvasRobot.drawRobot(oldX, oldY); // Efface le robot de l'ancienne position

        // Mettre à jour la position du robot
        setX(newX);
        setY(newY);

        // Dessiner le robot à la nouvelle position
        canvasRobot.drawRobot(newX, newY);

        System.out.println(getName() + " se déplace vers " + getDirection(direction) + ".");
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
