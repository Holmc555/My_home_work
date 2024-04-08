package com.homework2;

import ClientGetAndSet.*;
import Connect.Database;
import DatabaseQueryServiceBD.DatabaseQueryService;
import Service.ClientService;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            DatabaseQueryService service = new DatabaseQueryService();
            Database db = Database.getInstance();
            ClientService clientService = new ClientService(db);

            // Пример использования сервиса для получения данных
            System.out.println("Max Projects Count Clients:");
            List<MaxProjectCountClient> maxProjectCountClients = service.findMaxProjectsClient();
            for (MaxProjectCountClient client : maxProjectCountClients) {
                System.out.println(client.getName() + " - " + client.getProjectCount());
            }

            System.out.println("\nCRUD Operations for Client:");

            // Создание нового клиента
            long newClientId = clientService.create("New Client");
            System.out.println("New Client ID: " + newClientId);

            // Получение информации о клиенте
            Client client = clientService.getById(newClientId);
            System.out.println("Client Retrieved: " + client.getName());

            // Обновление имени
            clientService.setName(newClientId, "Updated Client Name");
            System.out.println("Client Name Updated");

            // Удаление клиента
            clientService.deleteById(newClientId);
            System.out.println("Client Deleted");

            // Получение списка всех клиентов
            List<Client> allClients = clientService.listAll();
            for (Client c : allClients) {
                System.out.println("Client: " + c.getId() + " - " + c.getName());
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
