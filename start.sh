#!/bin/sh

mvn clean install
if [[ "$?" -ne 0 ]] ; then
  echo "Ошибка при сборке проекта. Смотрите логи выше";
  read -p"Нажмите enter для выхода"
  exit $rc
fi
cd target
java -jar -Dfile.encoding=UTF-8 stock-counter.jar