package vaccineApplication.population;


import java.util.Random;

public class Population {
    private Person[][] people;
    private int populationSize;
    private double sickProbability;
    private double vaccinatedProbability;
    private double infectionProbabilityWithVaccine;
    private int daysInfecting;
    private int dead;


    public Population(populationBuilder builder) {
        this.sickProbability = builder.sickProbability;
        this.vaccinatedProbability = builder.vaccinatedProbability;
        this.infectionProbabilityWithVaccine = builder.infectionProbabilityWithVaccine;
        this.daysInfecting = builder.daysInfecting;
        this.dead = builder.dead;
        this.populationSize = builder.populationSize * builder.populationSize;
        people = new Person[builder.populationSize][builder.populationSize];
        generatePopulation(people, sickProbability, vaccinatedProbability);
    }

    private Person[][] generatePopulation(Person[][] population, double sickProb, double vaccineProb) {
        Random random = new Random();
        for (int i = 0; i < population.length; i++) {
            for (int j = 0; j < population.length; j++) {
                int sicknessProbability = random.nextInt(100);
                int vaccinatedProbability = random.nextInt(100);
                people[i][j] = new Person(sickProb * 100 > sicknessProbability, vaccineProb * 100 > vaccinatedProbability, daysInfecting);
            }
        }
        return people;
    }

    public Person[][] getPeople() {
        return people;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public double getSickProbability() {
        return sickProbability;
    }

    public double getVaccinatedProbability() {
        return vaccinatedProbability;
    }

    public double getInfectionProbabilityWithVaccine() {
        return infectionProbabilityWithVaccine;
    }

    public int getDead() {
        return dead;
    }

    public void setDead(int dead) {
        this.dead = dead;
    }

    public static class populationBuilder {
        private int dead;
        private int populationSize;
        private double sickProbability;
        private double vaccinatedProbability;
        private double infectionProbabilityWithVaccine;
        private int daysInfecting;

        public Population build() {
            return new Population(this);
        }

        public populationBuilder(int populationSize) {
            this.populationSize = populationSize;
        }

        public populationBuilder setSickProbability(double sickProbability) {
            this.sickProbability = sickProbability;
            return this;
        }

        public populationBuilder setVaccinatedProbability(double vaccinatedProbability) {
            this.vaccinatedProbability = vaccinatedProbability;
            return this;
        }

        public populationBuilder setInfectionProbabilityWithVaccine(double infectionProbabilityWithVaccine) {
            this.infectionProbabilityWithVaccine = infectionProbabilityWithVaccine;
            return this;
        }

        public populationBuilder setDaysInfecting(int daysInfecting) {
            this.daysInfecting = daysInfecting;
            return this;
        }

        public populationBuilder setDead(int dead) {
            this.dead = dead;
            return this;
        }
    }
}
