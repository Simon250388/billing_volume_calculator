#!/bin/bash
yc compute instance create-with-container \
			--name my-service-calculator \
			--metadata-from-file user-data=./metadata/cloud-init.yaml \
			--memory 512MB \
			--cores 2 \
			--core-fraction 5 \
			--create-boot-disk size=30GB \
			--service-account-id ajent0knd3flm3l40en8 \
			--docker-compose-file ./docker-compose.yaml \
			--public-ip \
			--zone ru-central1-a
