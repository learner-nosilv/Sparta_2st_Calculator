---
name: Improvement suggest
about: Describe this issue template's purpose here.
title: "[개선] 사용자 예외처리가 아닌 내장 예외처리 하기"
labels: ''
assignees: ''

---

## 개선 대상
입력받은 string 값을 검사한 후, int 형으로 변환하여 리턴하는 메소드 `checkNumAndChangeToInt(String input)`

## 개선하고자 하는 것
해당 메소드가 던지는 예외를 상황별로 구분하고자
사용자 정의 예외(`FormatException`)로 따로 클래스까지 만들었으나
클래스를 만들지 않고 내장된 예외(`NumberFormatException`)를 사용하고
대신 발생하는 예외 메세지를 상황별로 달리 하려고 함

## 개선하고자 하는 이유
예외 클래스를 만든 이유가 **상황별로 다른 예외 메세지를 만들기위해서** 였는데
이 목적은 클래스를 만들지 않고 내장된 예외 클래스의 객체를 만들 때
생성자의 매개변수를 달리 하는 것으로도 해결이 가능하다는 것을 오늘 알았음
따라서, 클래스를 만든다는 추가 작업을 거치지 않고 내장된 예외 클래스를 사용하고자 함

## 개선 방향
FormatException 클래스를 제거하고
관련 Exception을 `NumberFormatException` 으로 처리하기
