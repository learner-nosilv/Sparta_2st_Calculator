package com.example.calculator;

import java.util.Scanner;       // 입력을 위한 Scanner 클래스 호출

public class Calculator {

    // 입력(string) → 숫자로 변환&음의정수인지검사(예외처리) → 출력(int): 0 혹은 자연수
    private static int checkNumAndChangeToInt(String input) throws NumberFormatException {
        int num;
        try {
            num = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            if (input.matches(".*[a-zA-Z].*")) {
                throw new NumberFormatException("입력값 '" + input + "'은(는) 숫자가 아닙니다.");
            } else {
                throw new NumberFormatException("Overflow 되었습니다.");
            }
        }
        if (num < 0) {
                throw new NumberFormatException("입력값 '" + input + "'은(는) 음의 정수 입니다.");

        }
        return num;
    }

    // 입력(string) → 숫자로 변환/1~4인지검사(예외처리) → 출력(int): 1, 2, 3, 4
    private static int checkSymbols(String input) throws NumberFormatException {
        // 올바른 입력값이 정해져있으므로, if문보다 원하는 구역을 바로 찾아갈 수 있는 case문 선택
        return switch (input) {
            case "+", "1" -> 1;
            case "-", "2" -> 2;
            case "*", "3" -> 3;
            case "/", "4" -> 4;
            // 예외처리
            default -> throw new NumberFormatException("입력값 '" + input + "'은(는) 올바른 입력값이 아닙니다.");
        };
    }

    public static void main(String[] args) {
        Scanner my_scanner = new Scanner(System.in);    // Scanner 객체 생성
        String input;           // 입력값 저장
        int a, b;               // 예외처리를 넘긴 유효한 숫자 저장
        int symbol;             // 예외처리를 넘긴 유효한 사칙연산 선지번호 저장

        while (true) {
            System.out.println("---[Calculator]---\t* 'exit' 입력시 즉시 종료됩니다.");

            // 1. 2개의 값을 입력받는 단계
            System.out.println("Stage 1. 0 혹은 자연수 2개 입력 받기");
            // 첫번째 값 입력받기
            while (true) {
                System.out.print("\t[1] 첫번째 수를 입력하세요: "); // 개행하지 않을 것이므로 print 사용
                input = my_scanner.nextLine(); // 한 줄 입력받기

                // 대소문자 구분없이 exit 입력시 종료
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("계산기를 종료합니다.");
                    return;
                }

                // 입력값을 정수로 전환 후 루프탈출 - 아닐시 예외던짐
                try {
                    a = checkNumAndChangeToInt(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("\t\t[ERR] 잘못된 입력: " + e.getMessage());
                }
            }

            // 두번째 값 입력받기
            while (true) {
                System.out.print("\t[2] 두번째 수를 입력하세요: "); // 개행하지 않을 것이므로 print 사용
                input = my_scanner.nextLine();  // 한 줄 입력받기

                // 대소문자 구분없이 exit 입력시 종료
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("계산기를 종료합니다.");
                    return;
                }

                // 입력값을 정수로 전환 후 루프탈출 - 아닐시 예외던짐
                try {
                    b = checkNumAndChangeToInt(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("\t\t[ERR] 잘못된 입력: " + e.getMessage());
                }
            }

            // 입력한 숫자 다시 확인해주기
            System.out.println("\t입력값: " + a + ", " + b);

            // 2. 사칙연산 기호를 입력받는 단계
            System.out.println("Stage 2. 사칙연산 기호 입력받기: 아래의 기호 혹은 선지번호를 입력하세요.");
            while (true) {
                System.out.print("\t① +  ② -  ③ *  ④/  기호 선택: ");

                input = my_scanner.nextLine();

                // 대소문자 구분없이 exit 입력시 종료
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("계산기를 종료합니다.");
                    return;
                }

                // 입력값을 정수로 전환 후 루프탈출 - 아닐시 예외던짐
                try {
                    symbol = checkSymbols(input);
                    break;  // 올바른 입력시에만 탈출
                } catch (NumberFormatException e) {
                    System.out.println("\t\t[ERR] 잘못된 입력: " + e.getMessage());
                }
            }

            // 3. 연산 수행
            System.out.println("Stage 3. 사칙연산 수행");
            switch (symbol) {
                case 1:
                    System.out.print("\t"+a + " + " + b + " = ");
                    try {
                        // 유사 Math.addExact(a, b); 과 같음
                        if (a > (Integer.MAX_VALUE - b)) {
                            throw new ArithmeticException("오버플로우 발생 가능성");
                        }
                        System.out.println(a + b);
                    } catch (ArithmeticException e) {
                        System.out.println("\t\t[ERR] 잘못된 결과: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("\t"+a + " - " + b + " = ");
                    try {
                        // 유사 Math.subtractExact(a, b); 과 같음
                        if (a < (Integer.MIN_VALUE + b)) {
                            throw new ArithmeticException("언더플로우 발생 가능성");
                        }
                        System.out.println(a - b);
                    } catch (ArithmeticException e) {
                        System.out.println("\t\t[ERR] 잘못된 결과: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("\t"+a + " × " + b + " = ");
                    try {
                        // 유사 Math.multiplyExact();
                        if (a > Integer.MAX_VALUE / b) {
                            throw new ArithmeticException("오버플로우 발생 가능성");
                        }
                        System.out.println(a * b);
                    } catch (ArithmeticException e) {
                        System.out.println("\t\t[ERR] 잘못된 결과: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("\t"+a + " ÷ " + b + " = ");
                    try {
                        System.out.println((double) a / b);
                    } catch (ArithmeticException e) {
                        System.out.println("\t\t[ERR] 잘못된 결과: 0으로 나눌 수 없습니다!");
                    }
                    break;
            }
            System.out.print("이어하시려면 값을 아무거나 입력하세요.('exit' 입력시 즉시 종료): ");
            input = my_scanner.nextLine();  // 한 줄 입력받기
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("계산기를 종료합니다.");
                return;
            }
            System.out.println();
        }
    }
}