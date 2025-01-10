package com.example.calculatorLv2;

import java.util.LinkedList;

public class Calculator {
    // 연산 결과를 저장하는 컬렉션
    private LinkedList<Double> resultList = new LinkedList<>();

    // 연산을 수행하고 결과값을 컬렉션에 저장 및 반환하는 메서드
    public double calculate(int a, int b, char operator) {
        double result = 0;
        switch (operator) {
            case '+', '1':
                // 유사 Math.addExact(a, b); 과 같음
                if (a > (Integer.MAX_VALUE - b)) {
                    throw new ArithmeticException("오버플로우 발생 가능성");
                }
                result = a + b; // 자동형변환
                break;
            case '-', '2':
                // 유사 Math.subtractExact(a, b); 과 같음
                if (a < (Integer.MIN_VALUE + b)) {
                    throw new ArithmeticException("언더플로우 발생 가능성");
                }
                result = a - b; // 자동형변환
                break;
            case '*', '3':
                // 유사 Math.multiplyExact();
                if (a > Integer.MAX_VALUE / b) {
                    throw new ArithmeticException("오버플로우 발생 가능성");
                }
                result = a * b;

                break;
            case '/', '4':
                if (b == 0) {
                    throw new ArithmeticException("0으로 나눌 수 없습니다!");
                } else {
                    result = (double) a / b;    // 강제형변환
                }
                break;
        }
        resultList.add(result);
        return result;
    }

    // 필드에 간접 접근하여 가장 오래된 결과값을 가져오는 Getter 메서드
    public double getResultOldest(){
        if (resultList.peekFirst() != null)     // 안전한 조회 peekFirst
            return resultList.peekFirst();
        else
            System.out.println("비어있습니다.");
        return 0;
    }

    // 필드에 간접 접근하여 가장 오래된 결과값을 수정하는 Setter 메서드
    public void setResultOldest(double newResult){
        if (resultList.isEmpty())               // 비어있는지 체크 (peekFirst 대신 사용)
            System.out.println("비어있습니다.");
        else
            resultList.set(0, newResult);
    }

    // 필드에 간접 접근하여 가장 오래된 결과값을 제거하는 메서드
    public void deleteResultOldest(){
        if (resultList.pollFirst() == null)      // pollFirst: 안전한 제거(비어있을시 null반환)
            System.out.println("비어있습니다.");
    }
}
