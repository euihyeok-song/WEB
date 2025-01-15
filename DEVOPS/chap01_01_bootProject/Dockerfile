## 1. 자동으로 빌드를 해주는 부분
## gradle 이미지 가져옴(빌드 스테이지를 build라는 이름으로 설정)
FROM gradle:7.6.1-jdk17-alpine AS build

# FROM --platform=linux/amd64 python:3.7-alpine

## 컨테이너 내부(alpine 리눅스 환경)의 app폴더로 설정
WORKDIR /app

## .(현재 디렉토리 내의 모든 파일과 폴더) 를 컨테이너의 /app 디렉토리로 복사
COPY . .

# 직접 build해주지 않아도 자동으로 build를 해줌
## gradle을 사용하여 프로젝트를 빌드(daemon 프로세스 사용 안함(필수 X))
RUN gradle clean build --no-daemon

# ---------------------------------------------------------------------------

## 2. 실행 스테이지 시작 - 맨마지막 파일만 이미지가 만들어짐 ( 위에는 전부 build를 위한 세팅 => multi stage build )
## openjdk 17 버전의 이미지를 가져와 JVM 환경 구축
# FROM은 다른 사람이 만든 image를 가져올 떄 사용
FROM openjdk:17-alpine

## 2-1. 빌드를 미리 수동으로 프로젝트에서 하고 이미지를 구축할 시
# 올리려는 프로젝트의 jar파일을 복사해서 app.jar파일에 저장
# COPY build/libs/*.jar app.jar

## 2-2. 빌드를 따로 수동으로 하지 않고(자동) 이미지를 구축할 시(docker build)
# 위작업으로 도출된 jar 파일을 현재 위치에 복사
COPY --from=build /app/build/libs/*.jar ./

## *.jar 파일을 나열하고 grep을 사용해 'plain'이라는 단어가 포함되지 않은 줄을 선택해 app.jar로 변경
# plain이 달려있는 것이 아닌 .jar파일만 app.jar로 이동(복사)한다.
RUN mv $(ls *.jar | grep -v plain) app.jar

# app.jar를 리눅스 환경에서 실행(스프링 부트 서버 실행(7777포트))
# 명령어 입력 ( 리눅스에서 동작할 shell 명령어 )
# java -jar app.jar : java를 openjdk17-alpine 환경에서 실행하여라
ENTRYPOINT ["java", "-jar", "app.jar"]

