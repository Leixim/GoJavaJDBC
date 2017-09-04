package dao;

import jdbc.DataBaseConnector;
import model.Developer;
import model.Skill;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DevelopersDAO implements CRUD {
    private Developer developer = new Developer();
    private DataBaseConnector connector = new DataBaseConnector();
    Scanner scanner = new Scanner(System.in);
    String SQL;

    @Override
    public void getByID() {
        System.out.println("Enter developer ID to FIND into DB");
        int ID = scanner.nextInt();
        SQL = "SELECT * FROM developers WHERE dev_id =?";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                developer.setDeveloperID(resultSet.getInt("dev_id"));
                developer.setDeveloperName(resultSet.getString("dev_name"));
                developer.setDeveloperSalary(resultSet.getInt("dev_salary"));
                developer.setCompanyID(resultSet.getInt("company_id"));
                System.out.println(developer.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void getAll() {
        SQL = "SELECT * FROM developers";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                developer.setDeveloperID(resultSet.getInt("dev_id"));
                developer.setDeveloperName(resultSet.getString("dev_name"));
                developer.setDeveloperSalary(resultSet.getInt("dev_salary"));
                developer.setCompanyID(resultSet.getInt("company_id"));
                System.out.println(developer.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void create() {
        System.out.println("Enter developer name");
        String devName = scanner.nextLine();
        System.out.println("Enter developer salary");
        int devSalary = scanner.nextInt();
        System.out.println("Enter developer company ID");
        int devCompany = scanner.nextInt();
        SQL = "INSERT  INTO developers(dev_name, dev_salary, company_id) VALUES(?,?,?)";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.setString(1, devName);
            statement.setInt(2, devSalary);
            statement.setInt(3, devCompany);
            statement.executeUpdate();
            System.out.println("Developer record is created");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void deleteByID() {
        System.out.println("Enter developer ID to DELETE from DB");
        int ID = scanner.nextInt();
        SQL = "DELETE FROM developers WHERE dev_id =?";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.setInt(1, ID);
            statement.executeUpdate();
            System.out.println("Developer record is deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void deleteAll() {
        SQL = "DELETE FROM developers ";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.executeUpdate();
            System.out.println("All developer records is deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void updateByID() {
        System.out.println("Enter developer ID to update");
        int ID = scanner.nextInt();
        System.out.println("Enter new developer name");
        String devName = scanner.nextLine();
        System.out.println("Enter new developer salary");
        int devSalary = scanner.nextInt();
        System.out.println("Enter new developer company ID");
        int devCompany = scanner.nextInt();
        SQL = "UPDATE developer SET dev_name=?, dev_salary=?, company_id=? WHERE dev_id =?";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.setString(1, devName);
            statement.setInt(2, devSalary);
            statement.setInt(3, devCompany);
            statement.executeUpdate();
            System.out.println("Developer record is updated");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }
}
