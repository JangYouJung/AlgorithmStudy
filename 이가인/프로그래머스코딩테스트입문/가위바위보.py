def solution(rsp):
    answer = ''
    for x in rsp:
        if (x == '0'):
            answer += '5'
        elif (x == '2'):
            answer += '0'
        else:
            answer += '2'
    
    return answer