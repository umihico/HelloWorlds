echo pwd
dirname $0
echo $0
script_dir=$(cd $(dirname $0); pwd)
parent_dirname=$(basename $script_dir)
project_name=$parent_dirname
echo ${project_name}-*.*.*
echo $SCRIPT_DIR
echo ${0##*/}
echo ${0%/*}
dir=/home/smith/Desktop/Test
parentdir="$(dirname "$dir")"
