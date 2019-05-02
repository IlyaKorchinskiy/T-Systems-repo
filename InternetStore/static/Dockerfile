FROM node

RUN npm install http-server -g

ADD ./img /home/node/app/img

CMD http-server /home/node/app/img

EXPOSE 8080
