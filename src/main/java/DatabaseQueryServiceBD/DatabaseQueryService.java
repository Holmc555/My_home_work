package DatabaseQueryServiceBD;

import ClientGetAndSet.LongestProject;
import ClientGetAndSet.MaxProjectCountClient;
import ClientGetAndSet.MaxSalaryWorker;
import ClientGetAndSet.YoungestEldestWorkers;
import Connect.Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    private Connection conn;

    public DatabaseQueryService() throws SQLException {
        this.conn = Database.getInstance().getConnection();
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() throws IOException, SQLException {
        String sqlFilePath = "src/main/sqlscript/find_max_projects_client.sql";
        List<MaxProjectCountClient> result = new ArrayList<>();
        String sql = new String(Files.readAllBytes(Paths.get(sqlFilePath)));
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                int projectCount = rs.getInt("project_count");
                result.add(new MaxProjectCountClient(name, projectCount));
            }
        }
        return result;
    }

    public List<LongestProject> findLongestProject() throws IOException, SQLException {
        String sqlFilePath = "src/main/sqlscript/find_longest_project.sql";
        List<LongestProject> projects = new ArrayList<>();
        String sql = new String(Files.readAllBytes(Paths.get(sqlFilePath)));
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int durationMonths = rs.getInt("month_count"); // Изменено на "month_count"
                projects.add(new LongestProject(durationMonths));
            }
        }
        return projects;
    }

    public MaxSalaryWorker findMaxSalaryWorker() throws IOException, SQLException {
        String sqlFilePath = "src/main/sqlscript/find_max_salary_worker.sql";
        MaxSalaryWorker worker = null;
        String sql = new String(Files.readAllBytes(Paths.get(sqlFilePath)));
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String workerName = rs.getString("name"); // Уже исправлено на "name"
                int salary = rs.getInt("salary"); // Теперь это будет работать, так как запрос возвращает 'SALARY'
                worker = new MaxSalaryWorker(workerName, salary);
            }
        }
        return worker;
    }

    public List<YoungestEldestWorkers> findYoungestEldestWorkers() throws IOException, SQLException {
        String sqlFilePath = "src/main/sqlscript/find_youngest_eldest_workers.sql";
        List<YoungestEldestWorkers> workers = new ArrayList<>();
        String sql = new String(Files.readAllBytes(Paths.get(sqlFilePath)));
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String workerName = rs.getString("name");
                String birthday = rs.getString("birthday");
                workers.add(new YoungestEldestWorkers(workerName, birthday));
            }
        }
        return workers;
    }

    public void closeConnection() throws SQLException {
        if (this.conn != null) {
            this.conn.close();
        }
    }
}
