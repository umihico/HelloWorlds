class ThreadSafeChrome():
    def __init__(self, chrome):
        self.chrome = chrome
        self.lock = RLock()

    @property
    def current_url(self):
        return self.chrome.current_url

    def __getattr__(self, name):
        with self.lock:
            method = getattr(self.chrome, name)
        method = self.wrap_method_threadsafe(method)
        return method

    def wrap_method_threadsafe(self, method):
        def deco(func):
            def wrapper(*args, **kwargs):
                with self.lock:
                    return func(*args, **kwargs)
            return wrapper
        method = deco(method)
        return method
