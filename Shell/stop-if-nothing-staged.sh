staged_count=$(git diff --cached --numstat | wc -l)
echo $staged_count

zero="0"

if [ $staged_count = $zero ]; then
  echo "nothing is staged. exit."
  exit 1
fi

one="1"

if [ $staged_count = $one ]; then
  echo "文字列は同じです"
else
  echo "文字列は違います"
fi
