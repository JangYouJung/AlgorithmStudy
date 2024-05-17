def solution(my_string, letter):
    answer = ''
    for word in my_string:
        if word != letter:
            answer += word
    return answer