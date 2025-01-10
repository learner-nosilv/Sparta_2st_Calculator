package com.example.calculatorLv2;

import java.util.Scanner;

public class App {

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


    // 정상적인 입력이 들어올 때까지 사용자 입력을 받고, 정상 입력값을 출력하는 함수
    private static char getSymbols(Scanner sc) {
        // 정상적인 입력이 들어올 때까지 사용자 입력을 받는 루프
        while (true) {
            System.out.print("\t① +  ② -  ③ *  ④/  선택: ");
            String input = sc.nextLine();

            // 대소문자 구분없이 exit 입력시 프로그램 종료
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("계산기를 종료합니다.");
                System.exit(0);
            }

            // 정상적인 입력값을 출력하거나 아닌 경우 오류를 알리고 다시 입력받음
            switch (input) {
                case "+", "1":
                    return '+';
                case "-", "2":
                    return '-';
                case "*", "3":
                    return '*';
                case "/", "4":
                    return '/';
                default:
                    System.out.println("\t\t[ERR] 잘못된 입력: 선택지 속 기호나 선지번호만 입력하세요.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);    // Scanner 객체 생성
        Calculator myCalculator = new Calculator();
        String input;           // 입력값 저장
        int a, b;               // 예외처리를 넘긴 유효한 숫자 저장
        char symbol;             // 예외처리를 넘긴 유효한 사칙연산 선지번호 저장

        while (true) {
            System.out.println("---[Calculator]---\t* 'exit' 입력시 즉시 종료됩니다.");

            // 1. 2개의 값을 입력받는 단계
            System.out.println("Stage 1. 0 혹은 자연수 2개 입력 받기");
            // 첫번째 값 입력받기
            while (true) {
                System.out.print("\t[1] 첫번째 수를 입력하세요: "); // 개행하지 않을 것이므로 print 사용
                input = myScanner.nextLine(); // 한 줄 입력받기

                // 대소문자 구분없이 exit 입력시 종료
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("계산기를 종료합니다.");
                    myScanner.close();
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
                input = myScanner.nextLine();  // 한 줄 입력받기

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
            symbol = getSymbols(myScanner);

            // 3. 연산 수행 : Calcuator클래스의 객체myCalculator 활용
            System.out.println("Stage 3. 사칙연산 수행");
            System.out.print("\t" + a + ' ' + symbol + ' ' + b + " = ");
            try {
                System.out.println(myCalculator.calculate(a, b, symbol));
            } catch (ArithmeticException e) {
                System.out.println("[ERR] 잘못된 결과: " + e.getMessage());
            }
            // 4. 이후 할 일 선택 : 재시작할지/가장 오래된 결과값을 확인/수정/제거할지 확인
            while (true) {
                System.out.println("Stage 4. 다음 할 일 선택");
                System.out.println("\t① '확인' : 가장 오래된 결과값 확인\t② '수정': 수정\t③ '제거': 제거\t④ 'exit' : 계산기 종료\t⑤ 그 외 모든 값 : 계산기 재시작");
                System.out.print("\t다음 할 일을 번호나 단어로 입력하세요 >> ");
                input = myScanner.nextLine();  // 한 줄 입력받기

                if (input.equalsIgnoreCase("exit") || input.equals("4")) {
                    System.out.println("계산기를 종료합니다.");
                    return;
                } else if (input.equals("1") || input.equals("확인")) {
                    System.out.print("\t가장 오래된 결과값: ");
                    try {
                        double temp = myCalculator.getResultOldest();
                        System.out.println(temp);
                    } catch (Exception e) {
                        System.out.println("비어있습니다.");
                    }
                } else if (input.equals("2") || input.equals("수정")) {
                    while (true) {
                        System.out.print("\t가장 오래된 결과값을 어떤 값으로 수정할까요 >> ");
                        if (myScanner.hasNextDouble()) {
                            double temp = myScanner.nextDouble();
                            myScanner.nextLine();   // if 문에서 읽었던 버퍼 비우기
                            if (myCalculator.setResultOldest(temp))
                                System.out.println("\t수정에 성공하였습니다.");
                            else System.out.println("\t비어있습니다. 수정에 실패하였습니다.");
                            break;
                        } else {
                            myScanner.nextLine();   // if 문에서 읽었던 버퍼 비우기
                            System.out.println("\t\t[Err] 정상적인 숫자값을 입력해주세요");
                        }
                    }
                } else if (input.equals("3") || input.equals("제거")) {
                    System.out.print("\t가장 오래된 결과값을 제거합니다 : ");
                    if (myCalculator.deleteResultOldest()) {
                        System.out.println("\t성공하였습니다.");
                    } else
                        System.out.println("\t비어있습니다. 제거에 실패하였습니다.");
                } else break;
            }
        }
    }
}