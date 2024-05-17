def solution(n):
    answer = 0
    for i in range(2, n+1, 2):
        answer += i
    return answer