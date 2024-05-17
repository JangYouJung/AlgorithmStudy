def solution(emergency):
    arr = sorted(emergency, reverse = True)
    answer = []
    for i in emergency:
        index = arr.index(i) + 1
        answer.append(index)
    return answer