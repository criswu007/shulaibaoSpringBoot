Redis 8 High-Availability package (master: 192.168.11.1, replicas: 192.168.11.2, 192.168.11.3)
------------------------------------------------------------
This package contains two deployment styles for each host under the requested base host directory /home/tdh:
  1) single-compose/ - each node has a single docker-compose.yml that runs both redis and sentinel
  2) split-compose/ - each node has separate docker-compose files for redis and sentinel

Layout inside the package:
  single-compose/node1/
  single-compose/node2/
  single-compose/node3/
  split-compose/node1/
  split-compose/node2/
  split-compose/node3/

Each node folder contains:
  - redis.conf
  - sentinel.conf
  - docker-compose*.yml

Notes before deploying:
  - The docker-compose files use ABSOLUTE host paths pointing to /home/tdh/... .
    After extracting on each host, ensure the directories exist and are owned by the user running docker.
  - These scripts assume passwordless SSH from the controller host to the target hosts (or SSH keys configured).
  - The Redis password is set to: 123
  - Ensure TCP ports 6379 and 26379 are open between nodes.

Quick steps to deploy (example: using the single-compose layout):
  1) Copy and extract the package on a management/jump host:
     tar -xzf redis_ha_configs.tar.gz -C /tmp
  2) On each target host, create the directories used by the compose (or run the provided scripts):
     mkdir -p /home/tdh/single-compose/node1/data /home/tdh/single-compose/node1/logs
     ... repeat for node2/node3 and for split-compose directories or run the scripts below to prepare.
  3) Start docker-compose on each host (see start_all.sh)

See start_all.sh / stop_all.sh for automation help.
