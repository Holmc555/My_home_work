package Population;

import Connect.Database;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabasePopulateService {
    public static void main(String[] args) {
        String sqlFilePath = "src/main/sql/populate_db.sql";

        try {
            // Получение нового соединения для каждой операции
            Connection conn = Database.getInstance().getConnection();
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
                    } catch (SQLException e) {
                        conn.rollback();
                        e.printStackTrace();
                        return;
                    }
                }
            }

            conn.commit();
            conn.close(); // Закрыть соединение после завершения операции
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
