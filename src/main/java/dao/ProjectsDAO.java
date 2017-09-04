package dao;

import jdbc.DataBaseConnector;
import model.Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ProjectsDAO implements CRUD {
    private Project project = new Project();
    private DataBaseConnector connector = new DataBaseConnector();
    Scanner scanner = new Scanner(System.in);
    String SQL;

    @Override
    public void getByID() {
        System.out.println("Enter project ID to FIND into DB");
        int ID = scanner.nextInt();
        SQL = "SELECT * FROM projects WHERE project_id =?";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                project.setProjectID(resultSet.getInt("project_id"));
                project.setProjectName(resultSet.getString("project_name"));
                project.setProjectCost(resultSet.getInt("project_cost"));
                project.setCompanyID(resultSet.getInt("company_id"));
                project.setCustomerID(resultSet.getInt("customer_id"));
                System.out.println(project.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void getAll() {
        SQL = "SELECT * FROM projects";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                project.setProjectID(resultSet.getInt("project_id"));
                project.setProjectName(resultSet.getString("project_name"));
                project.setProjectCost(resultSet.getInt("project_cost"));
                project.setCompanyID(resultSet.getInt("company_id"));
                project.setCustomerID(resultSet.getInt("customer_id"));
                System.out.println(project.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void create() {
        System.out.println("Enter project name");
        String projectName = scanner.nextLine();
        System.out.println("Enter project cost");
        int projectCost = scanner.nextInt();
        System.out.println("Enter project company ID");
        int projectCompany = scanner.nextInt();
        System.out.println("Enter project customer ID");
        int projectCustomer = scanner.nextInt();
        SQL = "INSERT  INTO projects(project_name, project_cost, company_id, customer_id ) VALUES(?,?,?,?)";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.setString(1, projectName);
            statement.setInt(2, projectCost);
            statement.setInt(3, projectCompany);
            statement.setInt(4, projectCustomer);
            statement.executeUpdate();
            System.out.println("Project record is created");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void deleteByID() {
        System.out.println("Enter project ID to DELETE from DB");
        int ID = scanner.nextInt();
        SQL = "DELETE FROM projects WHERE project_id =?";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.setInt(1, ID);
            statement.executeUpdate();
            System.out.println("Project record is deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void deleteAll() {
        SQL = "DELETE FROM projects";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.executeUpdate();
            System.out.println("All project records is deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void updateByID() {
        System.out.println("Enter project ID to update");
        int ID = scanner.nextInt();
        System.out.println("Enter project name");
        String projectName = scanner.nextLine();
        System.out.println("Enter project cost");
        int projectCost = scanner.nextInt();
        System.out.println("Enter project company ID");
        int projectCompany = scanner.nextInt();
        System.out.println("Enter project customer ID");
        int projectCustomer = scanner.nextInt();
        SQL = "UPDATE projects SET project_name=?, project_cost=?, company_id=?, customer_id=? WHERE project_id =?";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.setString(1, projectName);
            statement.setInt(2, projectCost);
            statement.setInt(3, projectCompany);
            statement.setInt(4, projectCustomer);
            statement.executeUpdate();
            System.out.println("Project record is updated");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }
}
