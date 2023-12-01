FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=cinema
ENV MYSQL_DATABASE=cinemaRL
ENV MYSQL_USER=cinema
ENV MYSQL_PASSWORD=cinema

ADD sql_scripts/AAScriptTFGCine_Grupo.sql /docker-entrypoint-initdb.d/
ADD sql_scripts/cinemarl_cards.sql /docker-entrypoint-initdb.d/
ADD sql_scripts/cinemarl_films.sql /docker-entrypoint-initdb.d/
ADD sql_scripts/cinemarl_items_in_order.sql /docker-entrypoint-initdb.d/
ADD sql_scripts/cinemarl_orders.sql /docker-entrypoint-initdb.d/
ADD sql_scripts/cinemarl_product_type.sql /docker-entrypoint-initdb.d/
ADD sql_scripts/cinemarl_projections.sql /docker-entrypoint-initdb.d/
ADD sql_scripts/cinemarl_screens.sql /docker-entrypoint-initdb.d/
# ADD sql_scripts/cinemarl_user_cards.sql /docker-entrypoint-initdb.d/
# ADD sql_scripts/cinemarl_users.sql /docker-entrypoint-initdb.d/

EXPOSE 3306