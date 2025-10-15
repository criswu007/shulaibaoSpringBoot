#!/usr/bin/env bash
# Create required directories on remote hosts under the base host dir.
SSH_USER="${SSH_USER:-$USER}"
IPS=(192.168.11.1 192.168.11.2 192.168.11.3)
BASE="/home/tdh"

for idx in 0 1 2; do
  IP=${IPS[$idx]}
  NODE=$((idx+1))
  echo "Preparing host $IP ..."
  # create single-compose and split-compose node dirs and data/logs
  ssh -o StrictHostKeyChecking=no $SSH_USER@$IP "mkdir -p $BASE/single-compose/node${NODE}/data $BASE/single-compose/node${NODE}/logs $BASE/split-compose/node${NODE}/data $BASE/split-compose/node${NODE}/logs"
done
echo "Remote directories created (if SSH access allowed)."
