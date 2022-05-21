#!/usr/bin/env bash
# Parameters
DIRNAME=$1
DESCRIBE=$2
MAIN=$3
PORT=$4
USER=$5

echo "Transferring data to the $DESCRIBE node."
sshpass -f password ssh "$USER" "mkdir -p test/RestaurantMsgPass"
sshpass -f password ssh "$USER" "rm -rf test/RestaurantMsgPass/*"
sshpass -f password scp dirGeneralRepos.zip "$USER":test/RestaurantMsgPass
echo "Decompressing data sent to the $DESCRIBE node."
sshpass -f password ssh "$USER" "cd test/RestaurantMsgPass ; unzip -uq $DIRNAME.zip"
echo "Executing program at the server $DESCRIBE."
sshpass -f password ssh "$USER" "cd test/RestaurantMsgPass/$DIRNAME ; java serverSide.main.$MAIN $PORT"
echo "Server shutdown."
sshpass -f password ssh "$USER" "cd test/RestaurantMsgPass/$DIRNAME ; less stat"