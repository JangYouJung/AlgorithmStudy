def solution(num_list):
    answer = []
    for i in range(len(num_list)):
        answer.append(num_list[len(num_list)-i-1])
    return answer