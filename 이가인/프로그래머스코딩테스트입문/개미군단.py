def solution(hp):
    answer = 0
    general = hp // 5
    soldier = (hp % 5) // 3
    worker = (hp % 5) % 3
    answer = general + soldier + worker
    
    return answer