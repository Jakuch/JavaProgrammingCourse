package vaccineApplication.population;


public class Person {
    private boolean isSick;
    private boolean isVaccinated;
    private int daysInfecting;
    private boolean isDead;
    private int actualInfectionDays;

    public Person(boolean isSick, boolean isVaccinated, int daysInfecting) {
        if (!isVaccinated) {
            this.isSick = isSick;
        }
        this.isVaccinated = isVaccinated;
        this.daysInfecting = daysInfecting;
    }

    public boolean isSick() {
        return isSick;
    }

    public int getActualInfectionDays() {
        return actualInfectionDays;
    }

    public Person setActualInfectionDays(int actualInfectionDays) {
        this.actualInfectionDays = actualInfectionDays;
        return this;
    }

    public void setSick(boolean sick) {
        isSick = sick;
    }

    public void setDaysInfecting(int daysInfecting) {
        this.daysInfecting = daysInfecting;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
    }

    public int getDaysInfecting() {
        return daysInfecting;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        setSick(false);
        setVaccinated(false);
        isDead = dead;
    }

}
