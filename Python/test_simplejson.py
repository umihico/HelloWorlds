from simplejson import dump

data = {'テスト': "aaaあああ"}
from umihico.io_ import save_as_txt
# dump(d, 'dumped.txt')
import pickle


with open('data.pickle', 'wb') as handle:
    pickle.dump(data, handle, protocol=0)


save_as_txt('data.ppicle', data, mode='w')

import json
with open('data.json', 'w') as outfile:
    json.dump(data, outfile)
with open('data.json2', 'w') as outfile:
    json.dump(data, outfile, ensure_ascii=False, indent=2)
