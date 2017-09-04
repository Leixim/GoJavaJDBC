package model;

public class Developer {
    private int developerID;
    private String developerName;
    private int developerSalary;
    private int companyID;

    public Developer() {
    }

    public Developer(int developerID, String developerName, int developerSalary, int companyID) {
        this.developerID = developerID;
        this.developerName = developerName;
        this.developerSalary = developerSalary;
        this.companyID = companyID;
    }

    public int getDeveloperID() {
        return developerID;
    }

    public void setDeveloperID(int developerID) {
        this.developerID = developerID;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public int getDeveloperSalary() {
        return developerSalary;
    }

    public void setDeveloperSalary(int developerSalary) {
        this.developerSalary = developerSalary;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    @Override
    public String toString() {
        return developerID + " " + developerName + " " + developerSalary + " " + companyID;
    }
}
