#!/bin/sh -e

psql --variable=ON_ERROR_STOP=1 --username "postgres" <<-EOSQL


EOSQL