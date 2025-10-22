FROM postgres:16.2

WORKDIR /Database

ADD . /app 

ENV POSTGRES_USER=postgres
ENV POSTGRES_DB=alexis

EXPOSE 5432

# docker run -p 5000:5432 --name c-pgadmin -e POSTGRES_PASSWORD="1234567" pgalexis:latest