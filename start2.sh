#!/usr/bin/env bash

NAME=${project.build.finalName}

cd 'target $0'

PWD='pwd'

WORK_PATH=${PWD%/*}

PID="${WORK_PATH}/${NAME}.jar"

STDOUT=${WORK_PATH}/stdout

JAVA_OPT="-Xms512m -Xmx1024m -DWORK_PATH=${WORK_PATH}"

touch ${STDOUT}

(java ${JAVA_OPT} -jar ${PID} > ${STDOUT} 2>&1 &) && tail -f ${STDOUT}
