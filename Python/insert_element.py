header_script0 = """<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>"""
header_script1 = """<script type=text/javascript>
$(function() {
$('a#test').bind('click', function() {
$.getJSON('/background_process_test',
function(data) {
//do nothing
});
return false;
});
});
</script>"""

script = """    <script language="javascript" type="text/javascript">
        function OnButtonClick() {
              alert('JavaScriptのアラート');
        }
    </script>"""

from selenium.webdriver import Chrome

from flask import Flask, request, render_template, Markup
app = Flask(__name__)


def insert_element(chrome, base_element, new_element, position='afterend'):
    chrome.execute_script(
        f"arguments[0].insertAdjacentHTML('{position}', arguments[1]);", base_element, new_element)


@app.route('/')
def index():
    all_checked_bid_javascript = "var data_arr = [];var chkboxes = document.getElementsByName('kesukecheck');for (let i = 0; i < chkboxes.length; i++){if(chkboxes[i].checked){data_arr.push(chkboxes[i].id+'='+document.getElementById(chkboxes[i].id.replace('checkof','priceof')).value);}};var postdata=data_arr.join('&');var xhr = new XMLHttpRequest();xhr.open('POST', 'http://localhost:5555/bid/', true);xhr.send(postdata);"
    allbid_button = f"""<button name="kesukebutton" onclick="{all_checked_bid_javascript}">入札</button>"""
    # button = """<button name="kesukebutton"  onclick="var xhr = new XMLHttpRequest();xhr.open('GET', 'http://localhost:5555/', true);xhr.send();">Test</button>"""  # works
    return Markup(allbid_button)
    # return 'name'


@app.route('/bid/', methods=["GET", "POST"])
def bid():
    if request.method == "GET":
        pass
        # return render_template('index.html')
    else:
        print(request.headers)
        print(request.data)
        return request.data
        #
        # request_url = gen_request_url(request.form)
        # products = get_products(request_url)
        # return render_template('result.html', products=products)


def main():
    chrome = Chrome()
    chrome.get("https://auctions.yahoo.co.jp/search/search?auccat=&tab_ex=commerce&ei=utf-8&aq=-1&oq=&sc_i=&fr=auc_top&p=macbook+air&x=0&y=0&fixed=0")
    head = chrome.find_element_by_xpath("//head")
    # chrome.execute_script(f"arguments[0].insertAdjacentHTML('beforeend','<div>AfterEnd</div>');", head)
    # chrome.execute_script(f"arguments[0].insertAdjacentHTML('beforeend','{header_script0}');", head)
    # chrome.execute_script(f"arguments[0].insertAdjacentHTML('beforeend','{header_script1}');", head)
    # chrome.execute_script(f"arguments[0].insertAdjacentHTML('beforeend','<div>AfterEnd</div>');", head)
    from time import sleep
    h3s = chrome.find_elements_by_xpath(
        "//div[@id='list01']//td[@class='a1']//h3")
    chrome.execute_script(f"arguments[0].insertAdjacentHTML('beforeend', arguments[1]);",
                          chrome.find_element_by_xpath('//head'), script)
    button = """<button name="kesukebutton"  onclick="alert('JavaScriptのアラート');">Test</button>"""  # works
    # button = """<button name="kesukebutton"  onclick="var xhr = new XMLHttpRequest();xhr.open('GET', 'http://localhost:5555/', true);">Test</button>"""  # works
    hrefs = []
    # chrome.execute_script("document.getElementById('list01').setAttribute('name', 'kesukeform');")
    for h3 in h3s:
        href = h3.find_element_by_xpath('.//a').get_attribute('href').split('/')[-1]
        print(href)
        checkbox = f"""<input id='checkof{href}' type="checkbox" name='kesukecheck'>"""
        pricebox = f"""<input id='priceof{href}' type="number" value="0" name='kesukeprice'>"""
        # button = f"""<button name="kesukebutton"  onclick="var price=document.getElementById('priceof{href}').value;var xhr = new XMLHttpRequest();xhr.open('GET', 'http://localhost:5555/bid/?aucid={href}&price='+price, true);xhr.send();">入札</button>"""  # works
        button = f"""<button name="kesukebutton"  onclick="var price=document.getElementById('priceof{href}').value;var xhr = new XMLHttpRequest();xhr.open('POST', 'http://localhost:5555/bid/', true);xhr.send('checkof{href}='+price);">入札</button>"""  # works
        chrome.execute_script(
            f"arguments[0].insertAdjacentHTML('afterend', arguments[1]);", h3, checkbox)
        chrome.execute_script(
            f"arguments[0].insertAdjacentHTML('afterend', arguments[1]);", h3, pricebox)
        chrome.execute_script(
            f"arguments[0].insertAdjacentHTML('afterend', arguments[1]);", h3, button)
        hrefs.append(href)
    # button = f"""<button name="kesukebutton"  onclick="var price=document.getElementById('priceof{href}').value;var xhr = new XMLHttpRequest();xhr.open('GET', 'http://localhost:5555/bid/?aucid={href}&price='+price, true);xhr.send();">入札</button>"""  # works
    all_checked_bid_javascript = "var data_arr = [];var chkboxes = document.getElementsByName('kesukecheck');for (let i = 0; i < chkboxes.length; i++){if(chkboxes[i].checked){data_arr.push(chkboxes[i].id+'='+document.getElementById(chkboxes[i].id.replace('checkof','priceof')).value);}};var postdata=data_arr.join('&');var xhr = new XMLHttpRequest();xhr.open('POST', 'http://localhost:5555/bid/', true);xhr.send(postdata);"
    all_forcebly_bid_javascript = "var data_arr = [];var chkboxes = document.getElementsByName('kesukecheck');for (let i = 0; i < chkboxes.length; i++){data_arr.push(chkboxes[i].id+'='+document.getElementById(chkboxes[i].id.replace('checkof','priceof')).value);};var postdata=data_arr.join('&');var xhr = new XMLHttpRequest();xhr.open('POST', 'http://localhost:5555/bid/', true);xhr.send(postdata);"
    all_checked_bid_button = f"""<button name="kesukebutton" onclick="{all_checked_bid_javascript}">選択された商品に入札</button>"""
    all_forcebly_bid_button = f"""<button name="kesukebutton" onclick="{all_forcebly_bid_javascript}">表示中の全ての商品に入札</button>"""
    kensaku_result = chrome.find_element_by_xpath("//em[text()='検索結果']")
    chrome.execute_script(
        f"arguments[0].insertAdjacentHTML('afterend', arguments[1]);", kensaku_result, all_forcebly_bid_button)
    chrome.execute_script(
        f"arguments[0].insertAdjacentHTML('afterend', arguments[1]);", kensaku_result, all_checked_bid_button)
    app.run(host=None, port=5555, debug=None, load_dotenv=True)


if __name__ == '__main__':
    # app.run(host=None, port=5555, debug=None, load_dotenv=True)
    main()
