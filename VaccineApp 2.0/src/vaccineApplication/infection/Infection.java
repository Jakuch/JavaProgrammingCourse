package vaccineApplication.infection;

import vaccineApplication.population.Population;

import java.util.Random;

public class Infection {
    private Population population;
    private int numberOfInfected;
    private int daysPassed;
    private int dead;
    private Random random = new Random();

    public Infection(Population population) {
        this.population = population;

    }

    public void start() {
        daysPassed++;
        for (int i = 0; i < population.getPeople().length; i++) {
            for (int j = 0; j < population.getPeople().length; j++) {
                if (population.getPeople()[i][j].isSick() &&
                        population.getPeople()[i][j].getDaysInfecting() % daysPassed == population.getPeople()[i][j].getDaysInfecting() &&
                        population.getPeople()[i][j].getDaysInfecting() == population.getPeople()[i][j].getActualInfectionDays()) {

                    population.getPeople()[i][j].setDead(true);
                    dead++;
                    population.setDead(dead);
                }
                if (population.getPeople()[i][j].isSick() && !population.getPeople()[i][j].isDead()) {
                    lowerNeighbour(i, j);
                    upperNeighbour(i, j);
                    rightNeighbour(i, j);
                    leftNeighbour(i, j);
                    population.getPeople()[i][j].setActualInfectionDays(population.getPeople()[i][j].getActualInfectionDays() + 1);
                }
            }
        }
        daysPassed = countDaysPassed();
    }

    private void lowerNeighbour(int x, int y) {
        if (y < Math.sqrt(population.getPopulationSize()) - 1 && !population.getPeople()[x][y + 1].isSick()) {
            int sicknesProbability = random.nextInt(100);
            if (population.getPeople()[x][y + 1].isVaccinated()) {
                population.getPeople()[x][y + 1].setSick(population.getInfectionProbabilityWithVaccine() * 100 > sicknesProbability);
            } else {
                population.getPeople()[x][y + 1].setSick(population.getSickProbability() * 100 > sicknesProbability);
            }
        }
    }

    private void rightNeighbour(int x, int y) {
        if (x < Math.sqrt(population.getPopulationSize()) - 1 && !population.getPeople()[x + 1][y].isSick()) {
            int sicknesProbability = random.nextInt(100);
            if (population.getPeople()[x + 1][y].isVaccinated()) {
                population.getPeople()[x + 1][y].setSick(population.getInfectionProbabilityWithVaccine() * 100 > sicknesProbability);
            } else {
                population.getPeople()[x + 1][y].setSick(population.getSickProbability() * 100 > sicknesProbability);
            }
        }
    }

    private void leftNeighbour(int x, int y) {
        if (y > 0 && !population.getPeople()[x][y - 1].isSick()) {
            int sicknesProbability = random.nextInt(100);
            if (population.getPeople()[x][y - 1].isVaccinated()) {
                population.getPeople()[x][y - 1].setSick(population.getInfectionProbabilityWithVaccine() * 100 > sicknesProbability);
            } else {
                population.getPeople()[x][y - 1].setSick(population.getSickProbability() * 100 > sicknesProbability);
            }
        }
    }

    private void upperNeighbour(int x, int y) {
        if (x > 0 && !population.getPeople()[x - 1][y].isSick()) {
            int sicknesProbability = random.nextInt(100);
            if (population.getPeople()[x - 1][y].isVaccinated()) {
                population.getPeople()[x - 1][y].setSick(population.getInfectionProbabilityWithVaccine() * 100 > sicknesProbability);
            } else {
                population.getPeople()[x - 1][y].setSick(population.getSickProbability() * 100 > sicknesProbability);
            }
        }
    }

    private int countDead() {
        return population.getDead();
    }

    private int countDaysPassed() {
        return daysPassed;
    }

    private int countInfected(Population population) {
        numberOfInfected = 0;
        for (int i = 0; i < population.getPeople().length; i++) {
            for (int j = 0; j < population.getPeople().length; j++) {
                if (population.getPeople()[i][j].isSick()) {
                    numberOfInfected++;
                }
            }
        }
        return numberOfInfected;
    }

    public int getNumberOfInfected() {
        return countInfected(this.population);
    }

    public Population getPopulation() {
        return population;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }

    public void setNumberOfInfected(int numberOfInfected) {
        this.numberOfInfected = numberOfInfected;
    }

    public int getDaysPassed() {
        return daysPassed;
    }

    public void setDaysPassed(int daysPassed) {
        this.daysPassed = daysPassed;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}
