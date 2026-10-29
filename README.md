# Pokédex - API Restful (FIAP)

## Rodando localmente
1. Sync Maven Changes no IntelliJ.
2. Rode o script `create_table.sql` no Oracle SQL Developer.
3. Configure as variáveis de ambiente DB_URL, DB_USER e DB_PASSWORD
   (no IntelliJ: Run → Edit Configurations → Environment variables).
   Exemplo de DB_URL: jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
4. Execute PokemonApplication.

## Gerar o JAR
    mvn clean -DskipTests package
    java -jar target/pokemon-0.0.1-SNAPSHOT.jar

## Docker
    docker build -t pokemon-api .
    docker run -p 8080:8080 -e DB_URL=... -e DB_USER=... -e DB_PASSWORD=... pokemon-api

## Endpoints
- GET    /pokemon
- GET    /pokemon/{codigo}
- POST   /pokemon
- PUT    /pokemon/{codigo}
- DELETE /pokemon/{codigo}
