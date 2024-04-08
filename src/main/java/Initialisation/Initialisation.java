package Initialisation;

import Connect.Database;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Initialisation {
    public static void main(String[] args) {
        String sqlFilePath = "src/main/sqlscript/init_db.sql";

        try (Connection conn = Database.getInstance().getConnection()) {
            List<String> sqlLines = Files.readAllLines(Paths.get(sqlFilePath));
            StringBuilder sqlQuery = new StringBuilder();

            conn.setAutoCommit(false);

            for (String line : sqlLines) {
                if (line.startsWith("--") || line.trim().isEmpty()) {
                    continue;
                }
                sqlQuery.append(line);

                if (line.endsWith(";")) {
                    try (Statement stmt = conn.createStatement()) {
                        stmt.execute(sqlQuery.toString());
                        sqlQuery = new StringBuilder();
                    } catch (Exception e) {
                        conn.rollback();
                        e.printStackTrace();
                        return;
                    }
                }
            }

            conn.commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
