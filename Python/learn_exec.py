

def main():
    exec("import time", globals())  # globals() is required to make import outside
    print(time.time())


# print(time.time())
main()
print(time.time())
