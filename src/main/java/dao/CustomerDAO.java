package dao;

import jdbc.DataBaseConnector;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerDAO implements CRUD {
    private Customer customer = new Customer();
    private DataBaseConnector connector = new DataBaseConnector();
    Scanner scanner = new Scanner(System.in);
    String SQL;

    @Override
    public void getByID() {
        System.out.println("Enter customer ID to FIND into DB");
        int ID = scanner.nextInt();
        SQL = "SELECT * FROM customers WHERE customer_id =?";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customer.setCustomerID(resultSet.getInt("customer_id"));
                customer.setCustomerName(resultSet.getString("customer_name"));
                System.out.println(customer.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void getAll() {
        SQL = "SELECT * FROM customers";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customer.setCustomerID(resultSet.getInt("customer_id"));
                customer.setCustomerName(resultSet.getString("customer_name"));
                System.out.println(customer.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void create() {
        System.out.println("Enter customer name");
        String customerName = scanner.nextLine();
        SQL = "INSERT  INTO customers(customer_name) VALUES(?)";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.setString(1, customerName);
            statement.executeUpdate();
            System.out.println("New cutomer record is created");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void deleteByID() {
        System.out.println("Enter customer ID to DELETE from DB");
        int ID = scanner.nextInt();
        SQL = "DELETE FROM customers WHERE customer_id =?";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.setInt(1, ID);
            statement.executeUpdate();
            System.out.println("Customer record is deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void deleteAll() {
        SQL = "DELETE FROM customers ";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.executeUpdate();
            System.out.println("All customer records is deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void updateByID() {
        System.out.println("Enter customer ID to update");
        int ID = scanner.nextInt();
        System.out.println("Enter new CUSTOMER NAME");
        scanner.nextLine();
        String customerName = scanner.nextLine();
        SQL = "UPDATE customers SET customer_name =? WHERE customer_id =?";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.setString(1, customerName);
            statement.setInt(2, ID);
            statement.executeUpdate();
            System.out.println("Customer record is updated");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }
}
