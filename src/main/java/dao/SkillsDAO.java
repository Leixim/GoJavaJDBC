package dao;

import jdbc.DataBaseConnector;
import model.Skill;

import java.sql.*;
import java.util.Scanner;

public class SkillsDAO implements CRUD {
    private Skill skill = new Skill();
    private DataBaseConnector connector = new DataBaseConnector();
    Scanner scanner = new Scanner(System.in);
    String SQL;

    @Override
    public void getByID() {
        System.out.println("Enter skill ID to FIND into DB");
        int ID = scanner.nextInt();
        SQL = "SELECT * FROM skills WHERE skill_id =?";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                skill.setSkillID(resultSet.getInt("skill_id"));
                skill.setSkillName(resultSet.getString("skill_name"));
                System.out.println(skill.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void getAll() {
        SQL = "SELECT * FROM skills";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                skill.setSkillID(resultSet.getInt("skill_id"));
                skill.setSkillName(resultSet.getString("skill_name"));
                System.out.println(skill.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void create() {
        System.out.println("Enter skill name");
        String skillName = scanner.nextLine();
        SQL = "INSERT  INTO skills(skill_name) VALUES(?)";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.setString(1, skillName);
            statement.executeUpdate();
            System.out.println("Skill record is created");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void deleteByID() {
        System.out.println("Enter skill ID to DELETE from DB");
        int ID = scanner.nextInt();
        SQL = "DELETE FROM skills WHERE skill_id =?";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.setInt(1, ID);
            statement.executeUpdate();
            System.out.println("Skill record is deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void deleteAll() {
        SQL = "DELETE FROM skills ";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.executeUpdate();
            System.out.println("All skill records is deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }

    @Override
    public void updateByID() {
        System.out.println("Enter skill ID to update");
        int ID = scanner.nextInt();
        System.out.println("Enter new SKILL NAME");
        scanner.nextLine();
        String skillName = scanner.nextLine();
        SQL = "UPDATE skills SET skill_name =? WHERE skill_id =?";
        try (PreparedStatement statement = connector.getConnection().prepareStatement(SQL)) {
            statement.setString(1, skillName);
            statement.setInt(2, ID);
            statement.executeUpdate();
            System.out.println("Skill record is updated");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.closeConnection();
        }
    }
}
