echo aaa
success=$?
echo $success
if [ $success -ne 0 ]; then
    echo "Oops..."
    exit 1
fi
cat invalid-filename.txt
success=$?
echo $success
if [ $success -ne 0 ]; then
    echo "Oops..."
    exit 1
fi
echo final
