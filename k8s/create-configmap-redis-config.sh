SCRIPT_DIR=$(dirname "$0")
kubectl create configmap redis-conf --from-file=$SCRIPT_DIR/redis.conf -n u1