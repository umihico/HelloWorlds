from credentials import yahoo_password, yahoo_userid
test_url = "https://page.auctions.yahoo.co.jp/jp/auction/e330219271"
action = "https://auctions.yahoo.co.jp/jp/show/bid_preview"
from lxml.html import fromstring
from umihico.scraping.requests_ import get, _base_headers
from requests import Session
from selenium.webdriver import Chrome, ChromeOptions
Chrome.xpath = Chrome.find_element_by_xpath
Chrome.xpaths = Chrome.find_elements_by_xpath
from time import sleep
#
# s = Session()
# s.post(url, data=None, json=None)


class AutoLxmlSession(Session):
    def set_parser(self, response, url):
        self.response = response
        self.lxml = fromstring(self.response.text)
        self.last_url = url
        return response

    def get(self, url):
        return self.set_parser(super().get(url), url)

    def post(self, url, data=None, json=None):
        response = super().post(url, data=data, json=json)
        return self.set_parser(response, url)


class FormEasyPostableSession(AutoLxmlSession):
    def post_form(self, optional_dict, form_xpath):
        default_inputs = self.lxml.xpath(form_xpath + "//input")
        default_inputs = [
            inp for inp in default_inputs if 'name' in inp.attrib]
        payload = {input_.attrib['name']: input_.attrib.get("value", "")
                   for input_ in default_inputs}
        print(payload)
        print(optional_dict)
        payload.update(optional_dict)
        print(payload)
        return self.set_parser(self.post(self.last_url, data=payload), self.last_url)


def gen_headless_options():
    chrome_options = ChromeOptions()
    chrome_options.add_argument('--headless')
    chrome_options.add_argument('--no-sandbox')
    chrome_options.add_argument('--disable-gpu')
    return chrome_options


def gen_logined_chrome(username, password):
    # chrome = Chrome(chrome_options=gen_headless_options())
    "headless will cause error"
    chrome = Chrome()
    chrome.get("https://login.yahoo.co.jp/")
    chrome.xpath("//input[@id='username']").send_keys(username)
    if chrome.xpaths("//button[@id='btnNext']"):
        chrome.xpath("//button[@id='btnNext']").click()
        sleep(1)
    chrome.xpath("//input[@id='passwd']").send_keys(password)
    chrome.xpath("//button[@id='btnSubmit']").click()
    chrome.get("https://auctions.yahoo.co.jp")
    return chrome


def gen_logined_session2(username, password):
    chrome = gen_logined_chrome(username, password)
    cookies = chrome.get_cookies()
    # sessiAon = Session()
    session = FormEasyPostableSession()
    for cookie in cookies:
        session.cookies.set(cookie['name'], cookie['value'])
    chrome.quit()
    return session


def test_gen_logined_session():
    """success!!!"""
    session = gen_logined_session2(
        username=yahoo_userid, password=yahoo_password)
    response = session.get("https://auctions.yahoo.co.jp")
    print(response.text)


# test_gen_logined_session()
# raise


def gen_cookie_copied_chrome(cookies):
    while True:
        try:
            chrome = gen_chrome()
            chrome.get("https://www.wgsn.com/library/results")
            for cookie in cookies:
                dict_cookie = dict(cookie)
                dict_cookie = {k: v for k, v in dict_cookie.items() if k in [
                    'name', 'value', 'domain']}
                chrome.add_cookie(dict_cookie)
        except Exception as e:
            print('---', threading.current_thread(), '---')
            traceback.print_exc()
            print('---', threading.current_thread(), '---')
        else:
            print('chrome generated', threading.current_thread())
            return chrome


# gen_logined_session2(username=yahoo_userid, password=yahoo_password)
# raise


def gen_logined_session(username=None, password=None):
    session = Session()
    session.headers = _base_headers.copy()
    response = session.get("https://login.yahoo.co.jp/")
    lxml = fromstring(response.text)
    inputs = lxml.xpath(
        "//form[@method='post' and @action='/config/login' and @name='login_form']//input")
    payload = {input_.attrib['name']: input_.attrib['value']
               for input_ in inputs}
    payload["username"] = yahoo_userid
    response = session.post("https://login.yahoo.co.jp/", data=payload)
    lxml = fromstring(response.text)
    inputs = lxml.xpath(
        "//form[@method='post' and @action='/config/login' and @name='login_form']//input")
    payload = {input_.attrib['name']: input_.attrib['value']
               for input_ in inputs}
    payload["username"] = yahoo_password
    response = session.post("https://login.yahoo.co.jp/", data=payload)
    print(response.text)
    """
    conclusion:
    <p><span class="warnIco">JavaScriptが無効です。ブラウザの設定でJavaScriptを有効にしてください。</span></p>
    use selenium instead of requests
    """
    # lxml = fromstring(response.text)


# gen_logined_session()
# raise


def bid(url, price):
    pass


class SessionFormSender():
    def __init__(self, session):
        self.session = session

    def get(self, url):
        self.response = self.session.get(url)
        self.lxml = fromstring(self.response.text)
        return self.response

    def post(self, url, data=None, headers=None):
        self.response = self.session.post(url, data=data, headers=headers)
        self.lxml = fromstring(self.response.text)
        return self.response

    def form_post(self, optional_input_dict,):
        self.response = self.session.post(data=optional_input_dict)
        self.lxml = fromstring(self.response.text)
        return self.response

        response = self.post(url, data=optional_input_dict)
        lxml = fromstring(response.text)


def test_bid2():
    global test_url
    session = gen_logined_session2(yahoo_userid, yahoo_password)
    session.get(test_url)
    optional_dict = {"lastquantity": "1",
                     "setPrice": "100",
                     "Quantity": "1", }
    response = session.post_form(
        optional_dict, "//form[@action='https://auctions.yahoo.co.jp/jp/show/bid_preview']")
    lxml = fromstring(response.text)
    print(lxml.xpath("//html")[0].text_content())


test_bid2()
raise


def test_bid():
    global test_url
    response = get(test_url)
    lxml = fromstring(response.text)
    inputs = lxml.xpath(
        "//form[@method='post' and @action='https://auctions.yahoo.co.jp/jp/show/bid_preview']//input")
    for input_ in inputs:
        print(input_.attrib['name'], input_.attrib['value'])
    print(len(inputs))
    {
        "ItemID": "g322595802",
        "login": yahoo_userid,
        "cc": "jp",
        "bidType": "1000",
        "lastquantity": "1",
        "setPrice": "100",
        "Quantity": "1",
        "Bid": "確認する",
    }


if __name__ == '__main__':
    test_bid()
