from selenium.webdriver import Chrome

chrome = Chrome()
chrome.get("https://github.com/umihico")
result = chrome.get_screenshot_as_png()
print(len(result), type(result), result[:30])
# 227427 <class 'bytes'> b'\x89PNG\r\n\x1a\n\x00\x00\x00\rIHDR\x00\x00\x06\x12\x00\x00\x04\x91\x08\x06\x00\x00\x00\xd2'
result = chrome.get_screenshot_as_base64()
print(len(result), type(result), result[:30])
# 303236 <class 'str'> iVBORw0KGgoAAAANSUhEUgAABhIAAA
