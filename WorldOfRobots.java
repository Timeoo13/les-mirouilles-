import java.util.ArrayList;
import java.util.List;

public class WorldOfRobots {
    private List<Robot> robots;  // Liste qui contient tous les robots du monde

    // Constructeur qui initialise la liste de robots
    public WorldOfRobots() {
        robots = new ArrayList<>();
    }

    // Méthode pour ajouter un robot dans le monde
    public void ajouterRobot(Robot robot) {
        robots.add(robot);
        System.out.println(robot.getName() + " a été ajouté au monde.");
    }

    // Méthode pour déplacer tous les robots dans le monde
    public void moveAllRobots() {
        for (Robot robot : robots) {
            if (robot instanceof DrunkRobot) {
                ((DrunkRobot) robot).deplacer();
            } else if (robot instanceof SheepJumpingRobot) {
                ((SheepJumpingRobot) robot).deplacer();
            } else if (robot instanceof RobotBishop) {
                ((RobotBishop) robot).deplacer();
            } else if (robot instanceof RobotIntolerant) {
                ((RobotIntolerant) robot).deplacer();
            }
        }
    }
}
