#/bin/bash

for i in $(seq 1 30)
do
	
	qsub run_frrmab_cluster.sh 1 60000
done