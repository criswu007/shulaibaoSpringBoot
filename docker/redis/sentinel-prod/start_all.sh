#!/usr/bin/env bash
# Usage: set SSH_USER (or export) to the username used to SSH into the three hosts.
# Example: SSH_USER=admin ./start_all.sh single   # starts the single-compose layout
# Example: SSH_USER=admin ./start_all.sh split    # starts the split-compose layout
set -euo pipefail

SSH_USER="${SSH_USER:-$USER}"
IPS=(192.168.11.1 192.168.11.2 192.168.11.3)
BASE="/home/tdh"

if [ "${1:-single}" != "single" ] && [ "${1:-single}" != "split" ]; then
  echo "Usage: $0 [single|split]"
  exit 2
fi
MODE="${1:-single}"

echo "Using SSH user: $SSH_USER"
for idx in 0 1 2; do
  IP=${IPS[$idx]}
  NODE=$((idx+1))
  if [ "$MODE" = "single" ]; then
    DIR="$BASE/single-compose/node${NODE}"
    CMD="cd $DIR && docker-compose up -d"
  else
    DIR="$BASE/split-compose/node${NODE}"
    CMD="cd $DIR && docker-compose -f docker-compose.redis.yml up -d && docker-compose -f docker-compose.sentinel.yml up -d"
  fi
  echo "==> Starting on $IP (node${NODE}) : $CMD"
  ssh -o StrictHostKeyChecking=no $SSH_USER@$IP "$CMD" || { echo 'FAILED on' $IP; exit 3; }
done

echo "All start commands issued."
