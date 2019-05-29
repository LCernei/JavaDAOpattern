public class Student {

    private Integer nrMatricol;
    private String nume;
    private Float medie;

    public Student(Integer nrMatricol, String nume, Float medie) {
        this.nrMatricol = nrMatricol;
        this.nume = nume;
        this.medie = medie;
    }

    public Integer getNrMatricol() {
        return nrMatricol;
    }

    public void setNrMatricol(Integer nrMatricol) {
        this.nrMatricol = nrMatricol;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Float getMedie() {
        return medie;
    }

    public void setMedie(Float medie) {
        this.medie = medie;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nrMatricol=" + nrMatricol +
                ", nume='" + nume + '\'' +
                ", medie=" + medie +
                '}';
    }
}
