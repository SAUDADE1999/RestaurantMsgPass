#!/usr/bin/env bash
# Parameters
DIRNAME='dirKitchen'
DESCRIBE='kitchen'
MAIN='serverSide.main.KitchenMain'
USER="sd209@l040101-ws03.ua.pt"
PORT='22382'

source NodeDeployAndRun.sh "$DIRNAME" "$DESCRIBE" "$MAIN" "$USER" "$PORT"
