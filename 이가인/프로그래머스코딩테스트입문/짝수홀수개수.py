def solution(num_list):
    answer = []
    even, odd = 0, 0
    for i in num_list:
        if (i % 2 == 0):
            even += 1
        else:
            odd += 1
    answer.append(even)
    answer.append(odd)
    return answer