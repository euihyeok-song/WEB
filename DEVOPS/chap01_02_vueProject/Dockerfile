FROM node:lts-alpine

# curl 설치(client-url): 필수는 아님(postman 같은 것)
RUN apk add --no-cache curl

WORKDIR /app
COPY . ./

RUN npm install

# --host 0.0.0.0은 방화벽과 같은 역할(래플리카에서 bind-address를 0.0.0.0으로 설정해주는 것과 동일 => 외부에서의 접근을 전부 허용)
# --는 구분자 (vite -- npm)
CMD [ "npm", "run", "dev", "--", "--host", "0.0.0.0" ]