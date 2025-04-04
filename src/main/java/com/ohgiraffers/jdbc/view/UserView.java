package com.ohgiraffers.jdbc.view;

import com.ohgiraffers.jdbc.model.User;
import com.ohgiraffers.jdbc.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/* 회원 가입 View
* 회원가입 하기 위해 userID, username, email, password, role 입력 (role 도 선택?) */
public class UserView {
    private final UserService userService; // userService 기능 사용해야 하므로, userService 저장하는 객체를 저장하는 멤버 변수
    private final Scanner scanner; // 사용자 입력받기 위한 Scanner 객체르 저장하는 멤버 변수

    public UserView(Connection connection) {// UserView 클래스의 생성자
        this.userService = new UserService(connection); // UserView 객체 생성하고 멤버 변수에 할당
        this.scanner = new Scanner(System.in);
    }

    //사용자 메뉴 출력
    public void showMenu() {
        while (true) {
            System.out.println("=======사용자 관리 시스템=======");
            System.out.println("1. 전체 사용자 조회");
<<<<<<< HEAD
            System.out.println("2. 사용자 상세 조회 (ID)");
            System.out.println("3. 사용자 등록");
            System.out.println("4. 사용자 수정");
            System.out.println("5. 사용자 삭제");
            System.out.println("0. 종료");
            System.out.print("선택: ");
=======
            System.out.println("3. 사용자 조회 (ID)");
            System.out.println("4. 사용자 수정");
            System.out.println("5. 사용자 삭제");
            System.out.println("0. 종료");
            System.out.print("선택하세요: ");
>>>>>>> c64da463ac8f4a8bbb707a34b7947ed0dfc1c0c3

            int choice = scanner.nextInt(); // 사용자로부터 메뉴 선택을 입력받음
            scanner.nextLine();

            switch (choice) {  // 사용자의 선택에 따라 다른 기능을 수행
                case 1:
<<<<<<< HEAD
                    getAllUsers();//
                    break;
                case 2:
                    System.out.println(("조회할 사용자 ID를 입력하세요."));
                    int userId = scanner.nextInt();
                    scanner.nextLine();
                    getUserById(userId);
                    break;
                case 3:
                    registerUser();
                    break;
                case 4:
                    updateUser();
                    break;
                case 5:
                    deleteUser();
                    break;
=======
                    getAllUsers();// 전체 사용자 조회
                case 2:
                    registerUser();// 사용자 등록
                case 3:
                    getUserById();
                case 4:
                    updateUser();
                case 5:
                    deleteUser();
>>>>>>> c64da463ac8f4a8bbb707a34b7947ed0dfc1c0c3
                case 0: { // 프로그램 종료
                    System.out.println("프로그램을 종료합니다");
                    return;
                }
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }

    // [사용자 등록 (CREAT)]

    private void registerUser() { // 사용자 정보 입력받아 새로운 사용자 등록하는 메서드
        System.out.println("사용자 이름: ");
        String username = scanner.nextLine();

        System.out.println("이메일: ");
        String email = scanner.nextLine();

        System.out.println("비밀번호: ");
        String password = scanner.nextLine();

        System.out.println("역할 ID: ");
        int roleId = scanner.nextInt();
        scanner.nextLine();

        User user = new User(1, username, email, password, roleId);
        try {
            boolean success = userService.registerUser(user);
            if (success) {
                System.out.println("사용자가 성공적으로 등록되었습니다.");
            } else {
                System.out.println("사용자 등록에 실패했습니다.");
            }
        } catch (SQLException e) {
            System.out.println("사용자 등록 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // 예외 메시지를 출력
        }
    }

    // [ 전체 사용자 조회]
    private void getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();  // 인스턴스 매서드로 호출

            if (users.isEmpty()) {
                System.out.println("등록된 사용자가 없습니다.");
            } else {
                System.out.println("\n=== 전체 사용자 목록 ===");
                users.forEach(user -> System.out.println(user));
            }
        } catch (SQLException e) {
            System.out.println("사용자 목록을 조회하는 중 오류가 발생했습니다.");
        }
    }


    // [ 단일(상세) 사용자 조회]
<<<<<<< HEAD
    private void getUserById(int userId) {  // ID 입력받음.
=======
    private void getUserById() {  // ID 입력받음.
>>>>>>> c64da463ac8f4a8bbb707a34b7947ed0dfc1c0c3
        System.out.println("조회할 사용자 ID를 입력하세요: ");
        int useId = scanner.nextInt();
        scanner.nextLine();

        try {
            User user = userService.getUserById(useId);
            System.out.println("\n===== 사용자 정보 =====");
            System.out.println(user);
        } catch (SQLException e) {
            System.out.println("사용자 조회 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // [ 수정(UPDATE) ] //
    private void updateUser() {
        System.out.println("수정할 사용자 ID를 입력하세요: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("새로운 사용자 이름:");
        String username = scanner.nextLine();

        System.out.print("새로운 이메일: ");
        String email = scanner.nextLine();

        System.out.print("새로운 비밀번호: ");
        String password = scanner.nextLine();

        System.out.print("새로운 역할 ID: ");
<<<<<<< HEAD

        int roleId;
        try {
            roleId = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리
        } catch (Exception e) {
            System.out.println("⚠️ 역할 ID는 숫자로 입력해야 합니다.");
            scanner.nextLine(); // 입력 버퍼 비우기
            return;
        }
=======
        int roleId = scanner.nextInt();
        scanner.nextLine();
>>>>>>> c64da463ac8f4a8bbb707a34b7947ed0dfc1c0c3

        User user = new User(userId, username, email, password, roleId);
        try {
            boolean success = userService.updateUser(user);
            if (success) {
                System.out.println("사용자 정보가 성공적으로 수정되었습니다.");
            } else {
                System.out.println("사용자 정보 수정에 실패했습니다. ");
            }
        } catch (SQLException e) {
            System.out.println("사용자 정보 수정 중 오류가 발생했습니다. ");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    // [ 삭제(DELETE) ] //
    private void deleteUser() {
        System.out.println("삭제할 사용자 ID를 입력하세요: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        try {
            boolean success = userService.deleteUser(userId);
            if (success) {
                System.out.println("사용자가 성공적으로 삭제되었습니다.");
            } else {
                System.out.println("사용자가 삭제에 실패했습니다.");
            }
        } catch (SQLException e) {
            System.out.println("사용자가 삭제 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
