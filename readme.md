   docker run -p 3307:3306 --name mysql \
         -v /dockerdata/mysql/log:/var/log/mysql \
         -v /dockerdata/mysql/conf/my.cnf:/etc/mysql/my.cnf \
         -v /dockerdata/mysql/data:/var/lib/mysql \
         -v /dockerdata/mysql/backup:/home
         -e MYSQL_ROOT_PASSWORD=root \
         --restart=always \ 
         -d mysql:8.0.19 
 
         
 docker run -p 3306:3306 --name mysql  -v F:/docker/mysql/log:/var/log/mysql  -v F:/docker/mysql/conf/my.cnf:/etc/mysql/my.cnf   -v F:/docker/mysql/data:/var/lib/mysql     -v F:/docker/mysql/backup:/home  -e MYSQL_ROOT_PASSWORD=root    --restart=always   -d mysql:8.0.19