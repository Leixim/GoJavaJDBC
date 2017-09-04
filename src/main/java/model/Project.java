package model;

public class Project {
    private int projectID;
    private String projectName;
    private int projectCost;
    private int companyID;
    private int customerID;

    public Project() {
    }

    public Project(int projectID, String projectName, int projectCost, int companyID, int customerID) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectCost = projectCost;
        this.companyID = companyID;
        this.customerID = customerID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(int projectCost) {
        this.projectCost = projectCost;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return projectID + " " + projectName + " " + projectCost + " " + companyID + " " + customerID;
    }
}
