package main.app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import main.java.dao.UserDao;
import main.java.model.Users;

public class DeleteUser {
    public static void main(String[] args){
        Scanner scan  = new Scanner(System.in);

        UserDao userDao = new UserDao();
        List<Users> usersList = userDao.getAllUsers();

        // Verifica se a lista de usuários está vazia
        if(usersList.isEmpty()){
            System.out.println("Não há usuários para excluir. Encerrando o programa");
            scan.close();
            return;
        }

        System.out.println("Lista de usuários disponíveis para exclusão: ");

        // Exibe a lista de usuários com seus respectivos IDs
        for (Users users : usersList){
            System.out.println("ID: " + users.getId() + 
            "| username: " + users.getUsername() + 
            "| email: " + users.getEmail());
        }

        // Loop para solicitar O ID do usuario ate que um ID válido seja fornecido
        int userId = -1;
        Users users = null;

        while(true) {
            System.out.print("Digite o ID do usuário que deseja excluir (ou digite -1 para sair): ");
            try {
                userId = scan.nextInt();
                scan.nextLine();  // Limpa o buffer

                // Sai do programa se o usuário digitar -1
                if(userId == -1){
                    System.out.println("Operação cancelada pelo usuário.");
                    break;
                }

                users = userDao.getUserById(userId);

                // Exclui o usuário se ele existir
                if(users != null){
                    userDao.deleteUser(userId);
                    System.out.println("Usuário deletado com sucesso.");
                    System.out.println("Id: " + users.getId() + ", Nome: " + users.getUsername() + ", Email: " + users.getEmail());
                    break;
                } else {
                    System.out.println("Usuário não encontrado. Tente novamente.");
                }
            } catch(InputMismatchException e) {
                System.out.println("ID inválido, insira um número inteiro.");
                scan.nextLine(); // Limpa o buffer
            }
        }
        scan.close();
    }
}
