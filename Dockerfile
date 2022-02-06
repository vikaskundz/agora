FROM node:16

RUN mkdir /apps
RUN mkdir /apps/frontend
RUN mkdir /apps/backend
WORKDIR /apps/frontend
COPY deploy-script.sh .
RUN chmod 777 deploy-script.sh

#backend apps structure
EXPOSE 8080
RUN apt-get update && \
    apt-get install -y openjdk-11-jdk ca-certificates-java && \
    apt-get clean && \
    update-ca-certificates -f
ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64/
RUN export JAVA_HOME
WORKDIR /apps/backend
COPY backend/bot-api/target/bot-api-1.0-SNAPSHOT.jar .


#front end apps structure
EXPOSE 3000
RUN mkdir /apps/frontend/.next
COPY Frontend/ /apps/frontend
WORKDIR /apps/frontend
RUN npm install
RUN npm run build

ENTRYPOINT ["sh", "deploy-script.sh"]
