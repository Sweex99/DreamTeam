package applicationfolder.utils;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

public class DataBaseConnect {

    private Connection connection;
    private String url = "jdbc:mysql://91.210.248.146/gs4415";
    private String login = "gs4415";
    private String passwword = "6OFhY5S4Ua";
    public Object[] user = new Object[7];

    public DataBaseConnect() {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(url, login, passwword);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.print("Не вдалося підлюкчитись");
        }
    }

    public boolean authorization(String login, String password) {
        if (!searchPerson(login, password)) {
            System.out.print("lalka");
            return false;
        }
        String authorization = "SELECT * FROM users WHERE login='" + login + "' AND password='" + password + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(authorization);
            if (resultSet.equals(0)) {
                System.out.print(false);
                return false;
            } else {
                while (resultSet.next()) {
                    user[0] = resultSet.getInt(1);
                    user[1] = resultSet.getString(2);
                    user[2] = resultSet.getString(3);
                    user[3] = resultSet.getString(4);
                    user[4] = resultSet.getString(5);
                    user[5] = resultSet.getInt(6);
                    user[6] = resultSet.getInt(7);
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.print("Не вдалося авторизуватись");
        }
        return true;
    }

    public void registration(String login, String password, String name, String lastname) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO users(login, password, name, lastname) VALUES ('" + login + "', '" + password + "', '" + name + "', '" + lastname + "')");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.print("Не вдалося авторизуватись");
        }
    }

    public void changePassword(String password, int id) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("UPDATE users SET password = '" + password + "' WHERE id = '" + id + "'");
        } catch (SQLException e) {
            System.err.print("");
        }
    }

    public void changeInfo(String changedName, String changedLastName, int id) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("UPDATE users SET name = '" + changedName + "', lastname = '" + changedLastName + "' WHERE id = '" + id + "'");
        } catch (SQLException e) {
            System.err.print("");
        }
    }

    public boolean searchPerson(String login, String password) {
        try {
            Statement statement = connection.createStatement();
            if (statement.execute("SELECT * FROM users WHERE login = '" + login + "'")) {
                System.out.print("sd");
                return true;
            } else {
                System.out.print("false ++");
                return false;
            }
        } catch (SQLException e) {
            System.err.print("");
        }
        return false;
    }

    public void updateStatistic(int testings, int percent, int id) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("UPDATE users SET testings= '" + testings + "', percent = '" + percent + "' WHERE id = '" + id + "'");
        } catch (SQLException e) {
            System.err.print("");
        }
    }
}

