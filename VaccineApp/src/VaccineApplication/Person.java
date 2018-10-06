package VaccineApplication;


public class Person {
    private boolean isSick;
    private boolean isVaccinated;

    public Person(boolean isSick, boolean isVaccinated) {
        this.isSick = isSick;
        this.isVaccinated = isVaccinated;
    }

    public boolean isSick() {
        return isSick;
    }

    public void setSick(boolean sick) {
        isSick = sick;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
    }

    @Override
    public String toString() {
        String s;
        String v;
        if (isSick) {
            s = "1";
        } else {
            s = "0";
        }
        if (isVaccinated) {
            v = "1";
        } else {
            v = "0";
        }
        return "[ " + s + "; " + v + " ]";
    }
}
