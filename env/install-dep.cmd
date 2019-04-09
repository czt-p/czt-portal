call mvn -N  install:install-file -Dfile=%~dp0/libs/cds-common-parent-2.1.pom -Dpackaging=pom -DpomFile=%~dp0/libs/cds-common-parent-2.1.pom
call mvn -N  install:install-file -Dfile=%~dp0/libs/cds-common-base-2.1.jar -DpomFile=%~dp0/libs/cds-common-base-2.1.pom
call mvn -N  install:install-file -Dfile=%~dp0/libs/cds-datastore-2.1.jar -DpomFile=%~dp0/libs/cds-datastore-2.1.pom
call mvn -N  install:install-file -Dfile=%~dp0/libs/cds-starter-actuator-ext-2.1.jar -DpomFile=%~dp0/libs/cds-starter-actuator-ext-2.1.pom
call mvn -N  install:install-file -Dfile=%~dp0/libs/cds-starter-datasource-2.1.jar -DpomFile=%~dp0/libs/cds-starter-datasource-2.1.pom
call mvn -N  install:install-file -Dfile=%~dp0/libs/cds-starter-db-auto-update-2.1.jar -DpomFile=%~dp0/libs/cds-starter-db-auto-update-2.1.pom
call mvn -N  install:install-file -Dfile=%~dp0/libs/cds-starter-dozer-2.1.jar -DpomFile=%~dp0/libs/cds-starter-dozer-2.1.pom
call mvn -N  install:install-file -Dfile=%~dp0/libs/cds-starter-jpa-2.2-SNAPSHOT.jar -DpomFile=%~dp0/libs/cds-starter-jpa-2.2-SNAPSHOT.pom
call mvn -N  install:install-file -Dfile=%~dp0/libs/cds-starter-jsonview-2.1.jar -DpomFile=%~dp0/libs/cds-starter-jsonview-2.1.pom
call mvn -N  install:install-file -Dfile=%~dp0/libs/cds-starter-swagger-2.1.jar -DpomFile=%~dp0/libs/cds-starter-swagger-2.1.pom
call mvn -N  install:install-file -Dfile=%~dp0/libs/cds-starter-syslog-2.1.jar -DpomFile=%~dp0/libs/cds-starter-syslog-2.1.pom
PAUSE
