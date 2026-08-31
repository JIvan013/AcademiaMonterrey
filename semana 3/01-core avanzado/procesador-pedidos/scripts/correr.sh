#!/usr/bin/env bash
# Compila y corre el ejercicio de principio a fin.
# Uso:  ./scripts/correr.sh
set -euo pipefail
cd "$(dirname "$0")/.."

MVN="./mvnw"; [ -x "$MVN" ] || MVN="mvn"

$MVN -q compile exec:java
