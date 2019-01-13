

from urllib.parse import urlparse, parse_qs, urlencode
raw_url = "https://auctions.yahoo.co.jp/search/search?va=apple+mac&vo=&ve=&ngrm=0&fixed=0&auccat=0&aucminprice=&aucmaxprice=&aucmin_bidorbuy_price=&aucmax_bidorbuy_price=&l0=0&abatch=0&istatus=0&gift_icon=0&charity=&slider=0&ei=UTF-8&f_adv=1&fr=auc_adv&f=0x2"
parse_result = urlparse(raw_url)
print(parse_result)
query = parse_qs(parse_result.query, keep_blank_values=True)
print(query)
query = {key: '+'.join(values) for key, values in query.items()}
print(str(query))
newurl = "https://auctions.yahoo.co.jp/search/search?"+urlencode(query)
print(raw_url)
print(newurl)
print(newurl == raw_url)
