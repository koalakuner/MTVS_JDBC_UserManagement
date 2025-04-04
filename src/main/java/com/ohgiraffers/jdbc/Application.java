package com.ohgiraffers.jdbc;

import com.ohgiraffers.jdbc.config.JDBCConnection;
import com.ohgiraffers.jdbc.view.UserView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws SQLException {
        Connection connection = JDBCConnection.getConnection();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== LMS 관리 시스템 =====");
            System.out.println("1. 사용자(User) 관리");
            System.out.println("0. 종료");
            System.out.print("선택: ");

<<<<<<< HEAD

=======
>>>>>>> c64da463ac8f4a8bbb707a34b7947ed0dfc1c0c3
            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리

            switch (choice) {
<<<<<<< HEAD
                case 1 -> showMenu(connection);
=======
                case 1 -> startUserManagement(connection);
>>>>>>> c64da463ac8f4a8bbb707a34b7947ed0dfc1c0c3
                case 0 -> {
                    connection.close();
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }

<<<<<<< HEAD
    private static void showMenu(Connection connection) {
        new UserView(connection).showMenu();
    }
}


=======
    private static void startLessonManagement(Connection connection) {
    }

    private static void startRoleManagement(Connection connection) {
    }


    /**
     * 사용자(User) 관리 시작
     * - 사용자(User) 관련 기능 실행
     */
    private static void startUserManagement(Connection connection) {

    }

}

>>>>>>> c64da463ac8f4a8bbb707a34b7947ed0dfc1c0c3
