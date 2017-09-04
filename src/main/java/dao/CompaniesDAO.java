package dao;

import jdbc.DataBaseConnector;
import model.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CompaniesDAO implements CRUD {
    private Company company = new Company();
    private DataBaseConnector connector = new DataBaseConnector();
    Scanner scanner = new Scanner(System.in);
    String SQL;

    @Override
    public void getByID() {
        System.out.println("Enter company ID to FIND into DB");
        int ID = scanner.nextInt();
        SQL = "SELECT * FROM companies WHERE company_id =?";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                company.setCompanyID(resultSet.getInt("company_id"));
                company.setCompanyName(resultSet.getString("company_name"));
                System.out.println(company.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void getAll() {
        SQL = "SELECT * FROM companies";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                company.setCompanyID(resultSet.getInt("company_id"));
                company.setCompanyName(resultSet.getString("company_name"));
                System.out.println(company.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void create() {
        System.out.println("Enter company name");
        String companyName = scanner.nextLine();
        SQL = "INSERT  INTO companies(company_name) VALUES(?)";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.setString(1, companyName);
            statement.executeUpdate();
            System.out.println("New company record is created");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void deleteByID() {
        System.out.println("Enter company ID to DELETE from DB");
        int ID = scanner.nextInt();
        SQL = "DELETE FROM companies WHERE company_id =?";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.setInt(1, ID);
            statement.executeUpdate();
            System.out.println("Company record is deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void deleteAll() {
        SQL = "DELETE FROM companies ";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.executeUpdate();
            System.out.println("All company records is deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void updateByID() {
        System.out.println("Enter company ID to update");
        int ID = scanner.nextInt();
        System.out.println("Enter new COMPANY NAME");
        scanner.nextLine();
        String companyName = scanner.nextLine();
        SQL = "UPDATE companies SET company_name =? WHERE company_id =?";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.setString(1, companyName);
            statement.setInt(2, ID);
            statement.executeUpdate();
            System.out.println("Company record is updated");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }
}
