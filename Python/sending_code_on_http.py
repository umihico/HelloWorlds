import types
import marshal
import pickle


def load_code(pickled_code):
    funcname, marshaled_code = pickle.loads(pickled_code)
    code = marshal.loads(marshaled_code)
    func = types.FunctionType(code, globals(), funcname)
    return func


def dump_code(func):
    byte_func = marshal.dumps(func.__code__)
    funcname = func.__name__
    pickled_code = pickle.dumps((funcname, byte_func))
    return pickled_code


import marshal


def foo(x):
    return x*x


def main():
    print(foo)
    import inspect
    code = inspect.getsource(foo)
    print(code)
    print(foo.__code__)
    code_string = marshal.dumps(foo.__code__)
    print(code_string)
    import types

    code = marshal.loads(code_string)
    print(code)
    print(type(code))
    globals()['f'] = code
    func = types.FunctionType(code, globals(), "some_func_name")
    print(func(10))
    print(type(func))
    print(type(foo))
# print(f(10))
#
# func(10)  # gives 100
# file = io.BytesIO()
# pickle.dump(method, file)
# file.seek(0)
# print(file.getvalue())
# raise
# pickled_method = pickle.loads(method_string)
# Chrome.pickled_method = pickled_method
# chrome = Chrome()
# print(chrome.pickled_method())


def test_sending_via_http():
    import pickle
    pickled = dump_code(main)
    print(type(pickled))
    int_list = list(pickled)
    byts = bytes(int_list)
    print(byts == pickled)
    raise
    data = pickled
    # print(type(byte_func))
    name2, func2 = pickle.loads(pickled)
    # print(name2, func2)

    # print(byte_func)
    import urllib
    req = urllib.request.Request('http://localhost/', data=data,  method="POST")
    with urllib.request.urlopen(req, timeout=29) as res:
        body = res.read()


test_sending_via_http()
