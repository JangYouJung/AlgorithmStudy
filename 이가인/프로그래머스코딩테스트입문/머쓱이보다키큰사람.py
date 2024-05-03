def solution(array, height):
    answer = 0
    array.sort(reverse=True)
    for i in array:
        if (i > height):
            answer += 1
    
    return answer