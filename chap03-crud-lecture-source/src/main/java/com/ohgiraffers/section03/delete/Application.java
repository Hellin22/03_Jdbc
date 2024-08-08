package com.ohgiraffers.section03.delete;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("없앨 번호를 입력해라: ");
        int menuCode = sc.nextInt();
        MenuService service = new MenuService();
        service.deleteMenu(new Menu(menuCode));
    }
}
