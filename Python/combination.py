
from itertools import combinations, combinations_with_replacement, permutations, product


for i, (R, G, B) in enumerate(product(range(6), range(4), range(1))):
    pass
all_len = i+1
# print(all_len)

l = ['s' for i in range(4)]
l.extend(["a" for i in range(5)])
count = 0
for i, picked in enumerate(combinations_with_replacement(l, 5)):
    str_picked = "".join(picked)
    if str_picked.count("s") == 3:
        count += 1
    # print(i, picked)
print(count, i+1)
# print(count/(i+1))
