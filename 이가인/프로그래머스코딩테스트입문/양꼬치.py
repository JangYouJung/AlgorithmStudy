def solution(n, k):
    answer = 0
    service = n // 10
    answer = 12000 * n + 2000 * k - 2000 * service
    
    return answer