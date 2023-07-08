#!/usr/bin/env bash
docker run \
  --rm \
  --name=oracle \
  --env="ORACLE_PASSWORD=oracle" \
  --env="APP_USER=hibernate" \
  --env="APP_USER_PASSWORD=hibernate" \
  -p "1521:1521" \
  gvenzl/oracle-xe:21-slim