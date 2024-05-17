from itertools import zip_longest as zip

def tolist(l):
    n=[]
    for i,d in enumerate(l):
        for _ in range(d):
            n.append(i+1)
    return n

def solution(cap, n, deliveries, pickups):
    d=tolist(deliveries)
    print(d)
    p=tolist(pickups)
    print(p)
    d.reverse()
    p.reverse()
    d=d[::cap]
    p=p[::cap]
    return 2*sum([max(x,y) for x,y in zip(d,p,fillvalue=0)])
