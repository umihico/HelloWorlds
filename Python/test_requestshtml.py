from requests_html import HTMLSession
from umihico.scraping.requests_ import _base_headers
from lxml.html import fromstring
session = HTMLSession()
session.headers = _base_headers
r = session.get('https://auctions.yahoo.co.jp')
r.html.render()
lxml = fromstring(r.text)
print(lxml.xpath("//html")[0].text_content())
