def check(u):
    stack = []
    for i in u:
        if (i == '('):
            stack.append(i)
        else:
            if len(stack) == 0:
                return False
            stack.pop()
    return True


def divide(w):
    cnt_left = 0
    cnt_right = 0
    for i in range (len(w)):
        if (w[i] == '('):
            cnt_left += 1
        else:
            cnt_right += 1
        if (cnt_left == cnt_right):
            return (w[0:i+1], w[i+1:])
    return (w , '')

def solution(p):
    u, v = divide(p)
    
    if not p:
        return ''
    
    if check(u):
        return u + solution(v)
    else:
        answer = '('
        answer += solution(v)
        answer += ')'
        
        for s in u[1:len(u)-1]:
            if (s == '('):
                answer += ')'
            else:
                answer += '('
    return answer