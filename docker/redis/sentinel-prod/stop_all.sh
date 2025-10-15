#!/usr/bin/env bash
# Usage: SSH_USER=me ./stop_all.sh single
set -euo pipefail
SSH_USER="${SSH_USER:-$USER}"
IPS=(192.168.11.1 192.168.11.2 192.168.11.3)
BASE="/home/tdh"

MODE="${1:-single}"
if [ "$MODE" != "single" ] && [ "$MODE" != "split" ]; then
  echo "Usage: $0 [single|split]"
  exit 2
fi

for idx in 0 1 2; do
  IP=${IPS[$idx]}
  NODE=$((idx+1))
  if [ "$MODE" = "single" ]; then
    DIR="$BASE/single-compose/node${NODE}"
    CMD="cd $DIR && docker-compose down"
  else
    DIR="$BASE/split-compose/node${NODE}"
    CMD="cd $DIR && docker-compose -f docker-compose.redis.yml down && docker-compose -f docker-compose.sentinel.yml down"
  fi
  echo "==> Stopping on $IP (node${NODE})"
  ssh -o StrictHostKeyChecking=no $SSH_USER@$IP "$CMD" || { echo 'FAILED on' $IP; exit 3; }
done

echo "All stop commands issued."
