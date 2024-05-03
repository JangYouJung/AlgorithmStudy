from datetime import datetime

def solution(age):
    year = datetime.today().year
    answer = year - age - 1
    return answer