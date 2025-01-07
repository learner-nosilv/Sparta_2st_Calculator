# [Spring 5기] CH 2 계산기 과제

---

## 개발환경

- IDE : IntelliJ IDEA 2024.3.1.1
- JDK : `17` Amazon Corretto 17.0.13
- Version control : `Git`
- Issue Tracking : `Github`
- 개발자 : Spring 5기 김혜민

## Lv1. 클래스 없이 사칙연산 계산기 만들기

- User Interface : Console
- 코드구현에 주로 사용한 관점 : Procedural programming
- 구현일 : 2025-01-06 (화) ~ 2025-01-07 (수)
### 작동 과정
- 사용자에게 숫자 2개 입력받기(String) → 예외처리 → Int 형변환
  - 예외처리(재입력): 오버(언더)플로우 / 문자입력/ 음수
  - 예외처리(종료): exit
- 사용자에게 연산기호 혹은 선지번호 입력받기(String) → 예외처리 → Int 형변환
  - 예외처리(재입력): 1, 2, 3, 4, 사칙연산 기호가 아닌 것
  - 예외처리(종료): exit
- 선택한 사칙연산 진행