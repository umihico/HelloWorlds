# inputpath=$1
MY_PATH="`dirname \"$0\"`"              # relative
echo $0
gitpath=$(pwd)
echo $gitpath
git -C $gitpath remote -v
echo $MY_PATH
MY_PATH="`( cd \"$MY_PATH\" && pwd )`"  # absolutized and normalized
echo $MY_PATH
git -C $MY_PATH remote -v
git remote -v
git remote get-url origin
git remote get-url origin|echo
git remote get-url origin
giturl=$(git remote get-url origin)
# echo $giturl|tr ".git" ""
echo ${giturl##*:}
echo ${giturl##*:}|cut -d "/" -f 1
echo ${giturl##*:}|cut -d "/" -f 2|cut -d "." -f 1
url1="git@github.com:umihico/git-powered-philosophy.git"
url2="https://github.com/umihico/kindle-highlights"
# echo $url1|cut -d "/"|
# echo $url2|cut -d "/"|
a=$(echo $url1|tr ":" "/"| awk -F"/" '{print $(NF - 0)}')
echo ${a%.git}
a=$(echo $url1|tr ":" "/"| awk -F"/" '{print $(NF - 1)}')
echo ${a%.git}
a=$(echo $url2|tr ":" "/"| awk -F"/" '{print $(NF - 0)}')
echo ${a%.git}
a=$(echo $url2|tr ":" "/"| awk -F"/" '{print $(NF - 1)}')
echo ${a%.git}
