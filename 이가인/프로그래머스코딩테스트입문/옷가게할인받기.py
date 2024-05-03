import math

def solution(price):

    if (price >= 500000):
        price *= 0.8
    elif (300000 <= price < 500000):
        price *= 0.9
    elif (100000 <= price < 300000):
        price *= 0.95
    
    return math.floor(price)