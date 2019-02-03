
"""
2x+3y≤15
2x+y≤9
x≥0
y≥0

answer: x=3, y=3
"""

import pulp


problem = pulp.LpProblem('simultaneous equations')
a = pulp.LpVariable('a')
b = pulp.LpVariable('b')
c = pulp.LpVariable('c')
problem += a * 90 + b * 50 + c * 0 == 2900
problem += b * 5 + c * 2 == 1800
problem += a * 20 + c * 5 == 2200
problem.solve()
print(problem)
print('a: {a}'.format(a=a.value()))
print('b: {b}'.format(b=b.value()))
print('c: {c}'.format(c=c.value()))
problem = pulp.LpProblem('simultaneous equations')
a = pulp.LpVariable('a')
b = pulp.LpVariable('b')
problem += a * 90 + b * 50 == 2900
problem += b - a*4 == 0
problem.solve()
print(problem)
print('a: {a}'.format(a=a.value()))
print('b: {b}'.format(b=b.value()))

problem = pulp.LpProblem('sample', pulp.LpMinimize)
a = pulp.LpVariable('a', 0, 1)
b = pulp.LpVariable('b', 0, 1)
problem += a + b
problem += a >= 0
problem += b >= 0.1
problem += a + b == 0.5
problem.solve()
print(problem)
print("a", a.value())
print("b", b.value())


problem = pulp.LpProblem('Restaurant', pulp.LpMaximize)
hamburg = pulp.LpVariable('Hamburg')
omelet = pulp.LpVariable('Omelet')
problem += 400 * hamburg + 300 * omelet
problem += 60 * hamburg + 40 * omelet <= 3800
problem += 20 * hamburg + 30 * omelet <= 2100
problem += 20 * hamburg + 10 * omelet <= 1200
problem.solve()
print(problem)
print('ハンバーグの個数: {hamburg}'.format(hamburg=hamburg.value()))
print('オムレツの個数: {omelet}'.format(omelet=omelet.value()))


lp = pulp.LpProblem('lp', pulp.LpMaximize)
x = pulp.LpVariable('x', 0)
y = pulp.LpVariable('y', 0)
lp += 4*x + 3*y
lp += 2*x + 3*y <= 15
lp += 2*x + y <= 9
print(lp)
lp.solve()
print('x=', x.value())
print('y=', y.value())
